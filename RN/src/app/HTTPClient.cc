//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with this program.  If not, see http://www.gnu.org/licenses/.
//
// (c) 2017 Christian Timmerer, Alpen-Adria-Universität Klagenfurt / Bitmovin Inc.
//          christian.timmerer@itec.aau.at / christian.timmerer@bitmovin.com
//
// 621.800 (17W) Computer Networks and Network Programming

#include "HTTPClient.h"
#include "HTTPClientMsg_m.h"
#include "HTTPServerMsg_m.h"

Define_Module (HTTPClient);

void HTTPClient::initialize() {
    // TODO implement initialize
    HTTPClientMsg * req = new HTTPClientMsg("test1");
    req->setMethod("GET");
    req->setResource("/test/\r\n");
    send(req, "toLowerLayer");
    req = new HTTPClientMsg("test2");
    req->setMethod("GET");
    req->setResource("/test/logo.gif\r\n");
    send(req, "toLowerLayer");
    req = new HTTPClientMsg("test3");
    req->setMethod("GET");
    req->setResource("/test/TechnikErleben.png\r\n");
    send(req, "toLowerLayer");
}

void HTTPClient::handleMessage(cMessage *msg) {
    HTTPServerMsg* resp = check_and_cast<HTTPServerMsg *>(msg);
    EV<<resp->getResponse();
    delete(resp);
}
