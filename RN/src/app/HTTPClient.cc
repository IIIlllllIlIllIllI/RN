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
#include "../udp/UDPControlInfo_m.h"

Define_Module(HTTPClient);

void HTTPClient::initialize() {
    startEvent = new cMessage("Event");
    scheduleAt(simTime()+1, startEvent);
}

void HTTPClient::handleMessage(cMessage *msg) {
    HTTPServerMsg* resp;
    HTTPClientMsg* req;
    if(msg->isSelfMessage()){
        req = new HTTPClientMsg("get1");
        req->setMethod("GET");
        req->setResource("/test/\r\n");
        send(encapsulate(req), "toLowerLayer");
    }else{
        resp = check_and_cast<HTTPServerMsg *>(msg);
        switch(counter)
        {
        case 1:
            req = new HTTPClientMsg("get2");
            req->setMethod("GET");
            req->setResource("/test/logo.gif\r\n");
            send(encapsulate(req), "toLowerLayer");
            break;
        case 2:
            req = new HTTPClientMsg("get3");
            req->setMethod("GET");
            req->setResource("/test/TechnikErleben.png\r\n");
            send(encapsulate(req), "toLowerLayer");
            break;
        default:
            break;
        }
        EV<<resp->getResponse()<<"\n";
    }

    counter++;
}
cPacket* HTTPClient::encapsulate(HTTPClientMsg* msg){
    cPacket* cP=new cPacket();
    UDPControlInfo* cntl=new UDPControlInfo();
    cntl->setSrcPort(this->srcPort);
    cntl->setDestPort(this->destPort);
    msg->setControlInfo(*cntl);
    cP->encapsulate((cPacket*)msg);
    return cP;
}
