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
#include "../ip/IPControlInfo_m.h"


Define_Module(UDP);

void UDP::initialize()
{
	// Nothing to do here ...
}

void UDP::handleMessage(cMessage *msg)
{
	if (msg->arrivedOn("inUpperLayer")) {
		// comes from application
		this->handleAppMessage(msg);
	}

	else if (msg->arrivedOn("inLowerLayer")) {
		//comes from lower layer
		this->handleUDPSegment(msg);
	}
}

void UDP::handleAppMessage(cMessage *msg)
{
	UDPSegment *segment = new UDPSegment("data");
	EV << "-- Removing UDPControlInfo\n";
	UDPControlInfo *udpInfo = check_and_cast<UDPControlInfo *>(msg->removeControlInfo());
	EV << " Setting ports";
	segment->setSrcPort(udpInfo->getSrcPort());
	segment->setDestPort(udpInfo->getDestPort());
	segment->encapsulate((cPacket*)msg);

	IPControlInfo *ipInfo = new IPControlInfo();
	ipInfo->setSrcIP(udpInfo->getSrcIP());
	ipInfo->setDestIP(udpInfo->getDestIP());
	ipInfo->setProtocol(17);
	segment->setControlInfo(ipInfo);

	EV << "-- Sending message to lower layer at port " << udpInfo->getDestPort();
	send(segment, "outLowerLayer");

	delete udpInfo;
}

void UDP::handleUDPSegment(cMessage *msg) {
	UDPSegment *udpSeg = check_and_cast<UDPSegment *>(msg);
	IPControlInfo *ipInfo = check_and_cast<IPControlInfo *>(udpSeg->removeControlInfo());

	UDPControlInfo *udpInfo = new UDPControlInfo();
	udpInfo->setDestIP(ipInfo->getDestIP());
	udpInfo->setSrcIP(ipInfo->getSrcIP());
	udpInfo->setSrcPort(udpSeg->getSrcPort());
	EV << "-- Arrival at port " << udpSeg->getDestPort();
	udpInfo->setDestPort(udpSeg->getDestPort());

	cMessage *unpacked = udpSeg->decapsulate();
	unpacked->setControlInfo(udpInfo);

	send(unpacked, "outUpperLayer");
	delete ipInfo;
	delete msg;
}

