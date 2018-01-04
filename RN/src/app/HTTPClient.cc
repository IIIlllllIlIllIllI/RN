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
#include "../tcp/TCPControlInfo_m.h"

Define_Module (HTTPClient);

void HTTPClient::initialize() {
    startEvent = new cMessage("Event");
//    cMessage* closeConnection = new cMessage("close");
    scheduleAt(simTime() + 10, startEvent);
//    scheduleAt(simTime() + 100, closeConnection);
}

void HTTPClient::handleMessage(cMessage *msg) {

    HTTPClientMsg* tcpMsg;
    TCPControlInfo* cntl;

    switch (counter) {
    case 0:
        tcpMsg = new HTTPClientMsg("connect");
        tcpMsg = addTCPCntl(tcpMsg,1,2,1);
        send(tcpMsg, "toTcp");
        break;
    case 1:
        EV << "Message get\n";
        tcpMsg = new HTTPClientMsg("get");
        tcpMsg = addTCPCntl(tcpMsg,0,1,1);
        send(tcpMsg, "toTcp");
        break;
    case 2:
        EV << "Message <html>\n";
        tcpMsg = new HTTPClientMsg("<html>");
        tcpMsg = addTCPCntl(tcpMsg,0,1,1);
        send(tcpMsg, "toTcp");
        break;
    case 3:
        EV << "Message picture\n";
        tcpMsg = new HTTPClientMsg("picture");
        tcpMsg = addTCPCntl(tcpMsg,0,1,1);
        send(tcpMsg, "toTcp");
        break;
    case 4 :
        tcpMsg = new HTTPClientMsg("close");
        tcpMsg = addTCPCntl(tcpMsg,2,1,1);
        send(tcpMsg, "toTcp");
    default:
        break;
    }
    counter++;

}
HTTPClientMsg* HTTPClient::addCntl(HTTPClientMsg* msg) {
    UDPControlInfo* cntl = new UDPControlInfo();
    cntl->setSrcPort(this->srcPort);
    cntl->setDestPort(this->destPort);
    msg->setControlInfo(cntl);
    return msg;
}
HTTPClientMsg* HTTPClient::addTCPCntl(HTTPClientMsg* msg, int tcpCommand, int tcpStatus, int BitLength) {
    TCPControlInfo* cntl = new TCPControlInfo();
    cntl->setSrcPort(this->srcPort);
    cntl->setDestPort(this->destPort);
    cntl->setTcpCommand(tcpCommand);
    cntl->setTcpStatus(tcpStatus);
    msg->setControlInfo(cntl);
    msg->setBitLength(BitLength);
    return msg;
}
