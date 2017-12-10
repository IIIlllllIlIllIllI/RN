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

#include <omnetpp.h>
#include "UDP.h"
#include "UDPSegment_m.h"
#include "UDPControlInfo_m.h"
#include "../3rdParty/IPv4Address.h"
#include "../3rdParty/IPv6Address.h"
#include "../app/HTTPClientMsg_m.h"
#include "../app/HTTPServerMsg_m.h"

Define_Module(UDP);

void UDP::initialize()
{
    // TODO implement as needed (or leave empty)
}

void UDP::handleMessage(cMessage *msg)
{
	if (msg->arrivedOn("fromUpperLayer")) {
		// comes from application
		this->handleAppMessage((cPacket*)msg);
	}

	else if (msg->arrivedOn("fromLowerLayer")) {
		//comes from lower layer
		this->handleUDPSegment((cPacket*)msg);
	}
}

void UDP::handleAppMessage(cPacket *msg)
{
	// TODO implement handleAppMessage
    // 1. cast to http msg
    UDPControlInfo* cntl=check_and_cast<UDPControlInfo *>(msg->removeControlInfo());
    UDPSegment* useg=new UDPSegment();
    cPacket *cp=(cPacket*)useg;
    useg->setSrcPort(cntl->getSrcPort());
    useg->setDestPort(cntl->getDestPort());
    cp->encapsulate(msg);
    // 2. remove controlinfo

    // 3. create udp segment and use controlinfo to set UDP fields
    delete(cntl);
    // 4. encapsulate http msg and send to lower layer
    send(cp,"toLowerLayer");
}

void UDP::handleUDPSegment(cPacket *msg) {
	// TODO implement handleUDPSegment
    // 1. cast to udp segment
    UDPSegment* useg=check_and_cast<UDPSegment *>(msg);
    // 2. create controlinfo and use UDP fields to set values
    UDPControlInfo* cntl=new UDPControlInfo();
    cntl->setDestPort(useg->getDestPort());
    cntl->setSrcPort(useg->getSrcPort());
    // 3. decapsulate http msg
    cMessage *cp=(cMessage *)useg->decapsulate();
    // 4. attach controlinfo and sent to upper layer
    cp->setControlInfo(cntl);
    send(cp,"toUpperLayer");
}

