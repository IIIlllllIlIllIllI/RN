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

#include "TCP.h"
#include <stdio.h>
#include "../3rdParty/IPv4Address.h"
#include "../3rdParty/IPv6Address.h"
#include "TCPControlInfo_m.h"
#include "TCPSegment_m.h"

Define_Module(TCP);

void TCP::initialize(){
    // TODO Initialise seqn and ackn.
    seqNr=100;
    ackNr=300;
    status=0;
}


void TCP::handleMessage(cMessage *msg){

    // TODO Handle timeouts.
    // TODO Simulate packet loss.

    if (msg->arrivedOn("fromUpperLayer")) {
        // Comes from appliction.
        this->handleAppMessage((cPacket*)msg);
    }

    else if (msg->arrivedOn("fromLowerLayer")) {
        // Comes from lower layer.
        this->handleTCPSegment((cPacket*) msg);
    }
}

void TCP::handleAppMessage(cPacket *msg) {
    // 1. remove controlinfo
    TCPControlInfo* cntl=check_and_cast<TCPControlInfo *>(msg->removeControlInfo());
    // 2. create tcp segment and use controlinfo to set TCP fields
    TCPSegment* tcpsegment=new TCPSegment();
    tcpsegment->setSrcPort(cntl->getSrcPort());
    tcpsegment->setDestPort(cntl->getDestPort());
    //
    int tcpCommand = cntl->getTcpCommand();     // 0 ... do nothing, 1 ... open connection, 2 ... close connection
    int tcpStatus = cntl->getTcpStatus();      // 1 ... connection is open, 2 ... connection is closed
    if(tcpCommand==1&&tcpStatus==2){
        //open new tcpconnection
        tcpsegment->setSyn(true);
        tcpsegment->setSeqNr(seqNr);
    }else if(tcpCommand==1&&tcpStatus==3){
        //syn received, send ack
        tcpsegment->setAck(true);
        tcpsegment->setSyn(true);
        swapSeqAndAck();
        tcpsegment->setSeqNr(seqNr);
        tcpsegment->setAckNr(ackNr);
    }else if(tcpCommand==1&&tcpStatus==1){
        //connection established, send ack
        tcpsegment->setAck(true);
        swapSeqAndAck();
        tcpsegment->setSeqNr(seqNr);
        tcpsegment->setAckNr(ackNr);
    }else if(tcpCommand==0&&tcpStatus==1){
        //send message
    }
    // 3. encapsulate http msg and send to lower layer
    tcpsegment->encapsulate(msg);
    delete(cntl);
    send(tcpsegment,"toLowerLayer");

}

void TCP::handleTCPSegment(cPacket *msg) {

    // 1. cast to tcp segment
    TCPSegment* tcpsegment=check_and_cast<TCPSegment *>(msg);
    // 2. create controlinfo and use TCP fields to set values
    TCPControlInfo* cntl=new TCPControlInfo();
    cntl->setDestPort(tcpsegment->getDestPort());
    cntl->setSrcPort(tcpsegment->getSrcPort());
    cntl->setTcpCommand(0);
    cntl->setTcpStatus(0);
    // 3. decapsulate http msg
    cMessage *cp=(cMessage *)tcpsegment->decapsulate();
    // 4. attach controlinfo and sent to upper layer
    cp->setControlInfo(cntl);
    send(cp,"toUpperLayer");
}
void TCP::swapSeqAndAck(){
    int tmp=ackNr;
    ackNr=seqNr+1;
    seqNr=tmp;
}
