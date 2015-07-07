#!/usr/bin/env python3

import os
import asyncio
from time import sleep

from tornado.websocket import WebSocketHandler
from tornado.web import RequestHandler, Application, url, asynchronous
from tornado.httpclient import AsyncHTTPClient
from tornado.httputil import url_concat
from tornado.platform.asyncio import AsyncIOMainLoop

class MainHandler(RequestHandler):
    def handle_response(self, response):
        self.write(response.body)
        self.finish()

    def async_client_call(self, action, address):
        params = {
            "action": action,
            'address': address
        }
        url = url_concat(address, params)
        http_client = AsyncHTTPClient()
        http_client.fetch(address, callback=self.handle_response)

    @asynchronous
    def get(self):
        self.write("Hello! here is a response from MR_async_server\n")
        action = self.get_argument('action', 'sync')
        address = self.get_argument('address', 'http://localhost:8080/testData')
        self.async_client_call(action, address)

class TestHandler(RequestHandler):
    def get(self):
        self.write("END NOW! here is a response from MR_async_server\n")

def main():
    print("im the mr async! im running!")
    AsyncIOMainLoop().install()

    port = os.environ.get("PORT", 8080)
    app = Application([
        url(r"/getData", MainHandler),
        url(r"/testData", TestHandler)
        ])
    print("Starting server at port: %s" % port)
    app.listen(port)
    asyncio.get_event_loop().run_forever()

if __name__ == '__main__':
    main()
