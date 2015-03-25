#!/usr/bin/env python3

import os
import asyncio

from tornado.websocket import WebSocketHandler
from tornado.web import RequestHandler, StaticFileHandler, Application, url
from tornado.httpclient import AsyncHTTPClient
from tornado.platform.asyncio import AsyncIOMainLoop

class MainHandler(RequestHandler):
    def get(self):
        self.write("Hello! i am a async python server")
        call localhost:8090

    def post(self):
        get body
        address


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
