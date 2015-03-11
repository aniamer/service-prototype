#!/usr/bin/env python3

import os
import asyncio

from tornado.websocket import WebSocketHandler
from tornado.web import RequestHandler, StaticFileHandler, Application, url
from tornado.httpclient import AsyncHTTPClient
from tornado.httputil import url_concat
from tornado.escape import json_encode, json_decode
from tornado.platform.asyncio import AsyncIOMainLoop

from rx.subjects import Subject
from rx.concurrency import AsyncIOScheduler




class MainHandler(RequestHandler):
    def get(self):
        self.render("index.html")

class WSHandler(WebSocketHandler):
    """docstring for WSHandler"""
    def __init__(self, arg):
        super(WSHandler, self).__init__()
        self.arg = arg

    def open(self):
        print("WebSocket opened")

    def on_message(self, message):
        self.write_message(u"You said: " + message)

    def on_close(self):
        print("WebSocket closed")

def main():
    AsyncIOMainLoop().install()

    port = os.environ.get("PORT", 8080)
    app = Application([
        url(r"/", MainHandler),
        (r'/ws', WSHandler),
        (r'/static/(.*)', StaticFileHandler, {'path': "."})
    ])
    print("Starting server at port: %s" % port)
    app.listen(port)
    asyncio.get_event_loop().run_forever()

if __name__ == '__main__':
    main()
