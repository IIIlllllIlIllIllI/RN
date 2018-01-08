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
#include "../app/HTTPServerMsg_m.h"

Define_Module(TCP);

void TCP::initialize() {
    // TODO Initialise seqn and ackn.
    seqNr = 100;
    ackNr = 300;
    timeout = 100;
    timeoutEvent = new cMessage("timeoutEvent");
    // 0 ... closed, 1 ... Syn-sent, 2 ... Syn-received, 3 ... open, 4 ... fin-wait-1, 5 ... fin-wait-2, 6 ... time-wait, 7 close-wait, 8 ... Last-ack
    status = 0;
}

void TCP::handleMessage(cMessage *msg) {

    if (msg == timeoutEvent) {
        //resend message
        delete msg;
        msg = curMsg;
        try {
            //stop old timer
            cancelEvent(timeoutEvent);
        } catch (...) {

        }
        //start timer

        scheduleAt(simTime() + timeout, timeoutEvent);
    } else {
        fromUpper = false;
        //set current message pointer
        curMsg = msg;
        try {
            //stop old timer
            cancelEvent(timeoutEvent);
        } catch (...) {

        }
        //start timer
        scheduleAt(simTime() + timeout, timeoutEvent);
    }
    //simulate packet loss
    if (uniform(0, 10) < 0) {
        EV << "\"Losing\" message.\n";
                bubble("message lost");  // making animation more informative...
        if (fromUpper || msg->arrivedOn("fromUpperLayer")) {
            fromUpper = true;
        } else {
            fromUpper = false;
        }
        return;
    }
    if (fromUpper || msg->arrivedOn("fromUpperLayer")) {
        // Comes from appliction.

        this->handleAppMessage((cPacket*) msg);
    }

    else if (!fromUpper || msg->arrivedOn("fromLowerLayer")) {
        // Comes from lower layer.
        this->handleTCPSegment((cPacket*) msg);
    }
}

void TCP::handleAppMessage(cPacket *msg) {
    // 1. remove controlinfo

    TCPControlInfo* cntl = check_and_cast<TCPControlInfo *>(
            msg->removeControlInfo());
    // 2. create tcp segment and use controlinfo to set TCP fields
    TCPSegment* tcpsegment = new TCPSegment();
    tcpsegment->setSrcPort(cntl->getSrcPort());
    tcpsegment->setDestPort(cntl->getDestPort());
    //building connection...
    int tcpCommand = cntl->getTcpCommand(); // 0 ... do nothing, 1 ... open connection, 2 ... close connection
    int tcpStatus = cntl->getTcpStatus(); // 1 ... connection is open, 2 ... connection is closed
    if (tcpCommand == 1 && tcpStatus == 2 && status == 0) {
        //open new tcpconnection
        EV << "open new tcpconnection\n";
        tcpsegment->setSyn(true);
        tcpsegment->setSeqNr(seqNr);
        EV << "SEQNR = " << seqNr << "\n";
        status = 1;      // syn-sent
    } else if (tcpCommand == 2 && tcpStatus == 1 && status == 3) {
        //close connection
        EV << "close connection\n";
        tcpsegment->setFin(true);
        tcpsegment->setAck(true);
        tcpsegment->setSeqNr(seqNr);
        tcpsegment->setAckNr(ackNr);
        status = 4;
    } else if (tcpCommand == 0 && tcpStatus == 1 && status == 3) {
        //send message
//        ackNr = tcpsegment->getSeqNr() + 1;
//        seqNr++;
        tcpsegment->setAck(true);
        tcpsegment->setSeqNr(seqNr);
        tcpsegment->setAckNr(ackNr);
    } else {
        throw std::invalid_argument("can't send message from current state");
    }
    // 3. encapsulate http msg and send to lower layer
    tcpsegment->encapsulate(msg);
    delete (cntl);
    send(tcpsegment, "toLowerLayer");

}

void TCP::handleTCPSegment(cPacket *msg) {
    // 1. cast to tcp segment
    TCPSegment* tcpsegment = check_and_cast<TCPSegment *>(msg);
    if (status == 0 && tcpsegment->getSyn()) { // Server
            //syn received, send ack
        tcpsegment->setAck(true);
        tcpsegment->setSyn(true);
        seqNr = ackNr;
        ackNr = tcpsegment->getSeqNr() + 1;
        tcpsegment->setSeqNr(seqNr);
        tcpsegment->setAckNr(ackNr);
        status = 2; // syn-received
        EV << "SERVER: SEQNR = " << seqNr << " ACKNR = " << ackNr << "\n";
        send(tcpsegment, "toLowerLayer");
    } else if (status == 1 && tcpsegment->getAck() && tcpsegment->getSyn()) { //Client
            //connection established, send ack
        tcpsegment->setAck(true);
        ackNr = tcpsegment->getSeqNr() + 1;
        seqNr++;
        tcpsegment->setSeqNr(seqNr);
        tcpsegment->setAckNr(ackNr);
        status = 3; //established
        EV << "ClientEstablished\n";

        send_toup(tcpsegment);

        EV << "SEQNR = " << seqNr << " ACKNR = " << ackNr << "\n";
        send(tcpsegment, "toLowerLayer");
    } else if (status == 2 && tcpsegment->getAck()) {
        //connection established
//        seqNr++;
        status = 3; //established
        EV << "ServerEstablished\n";
        delete (tcpsegment);
        return; // until here we finished 3-Way Handshake
    } else if (status == 3 && tcpsegment->getFin() && tcpsegment->getAck()) {
        // go to close-wait
        ackNr = tcpsegment->getSeqNr() + 1; //Server
        seqNr++;
        tcpsegment->setSeqNr(seqNr);
        tcpsegment->setAckNr(ackNr);
        tcpsegment->setAck(true);
        status = 7;
        send(tcpsegment, "toLowerLayer");
        // sleep?

        //go to last-ack
        tcpsegment = new TCPSegment("Close"); //Server
        tcpsegment->setSeqNr(seqNr);
        tcpsegment->setAckNr(ackNr);
        tcpsegment->setAck(true);
        tcpsegment->setFin(true);
        status = 8;
        send(tcpsegment, "toLowerLayer");
    } else if (status == 5 && tcpsegment->getAck() && tcpsegment->getFin()) { //Client
            // go to time-wait

        ackNr = tcpsegment->getSeqNr() + 1;
        seqNr++;
        tcpsegment->setSeqNr(seqNr);
        tcpsegment->setAckNr(ackNr);
        tcpsegment->setAck(true);
        status = 6;
        send(tcpsegment, "toLowerLayer");

        //wait 2 msl
        //close connection
        cancelEvent(timeoutEvent);
        status = 0;
        EV << "ClientClosed\n";
    } else if (status == 8 && tcpsegment->getAck()) {
        //close connection
        cancelEvent(timeoutEvent);
        status = 0;
        EV << "ServerClosed\n";
        delete (tcpsegment);
        return;
    } else if (status == 4 && tcpsegment->getAck()) { //Client
            // go to fin-wait 2

        ackNr = tcpsegment->getSeqNr() + 1;
        status = 5;
        delete (tcpsegment);
        return;
    } else if (status == 3) {
        ackNr = tcpsegment->getSeqNr() + 1;
        seqNr++;
        tcpsegment->setAck(true);
        tcpsegment->setSeqNr(seqNr);
        tcpsegment->setAckNr(ackNr);
        EV << "send message -- SEQNR = " << seqNr << " ACKNR = " << ackNr
                  << "\n";
        send_toup(tcpsegment);
    } else {
        throw std::invalid_argument("can't send message from current state");
    }
}
void TCP::send_toup(TCPSegment* tcpsegment) {

        // 2. create controlinfo and use TCP fields to set values
        TCPControlInfo* cntl = new TCPControlInfo();
        cntl->setDestPort(tcpsegment->getDestPort());
        cntl->setSrcPort(tcpsegment->getSrcPort());
        cntl->setTcpCommand(0);
        cntl->setTcpStatus(1);
        // 3. decapsulate http msg
        cMessage *cp = (cMessage *) tcpsegment->decapsulate();
        // 4. attach controlinfo and sent to upper layer
        cp->setControlInfo(cntl);
        send(cp, "toUpperLayer");


}
