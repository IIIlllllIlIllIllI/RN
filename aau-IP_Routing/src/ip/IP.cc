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

#include "IP.h"

Define_Module(IP);

void IP::initialize() {
	// Check if this IP impl is a router or not:
	isRouter = 0;
	if (strcmp(string(getParentModule()->getName()).substr(0, 7).c_str(), "router0") == 0) {
		isRouter = 1;
		EV << "[Router] " << getParentModule()->getName()
				<< " is initializing, configuring routing table ...\n";
		int i = 0;
		for (i = 0; i < routingTableSize; i++) {
			if (strcmp((routingtable[i][0]).c_str(),
					this->getParentModule()->getName()) == 0) {
				// this is an entry for this router, we add it to our table:
				forwardingtable[routingtable[i][1]] = atoi(
						(routingtable[i][4]).c_str());
				// you'll find the whole forwarding table in IP.h
			}
		}
	}
}


void IP::handleMessage(cMessage *msg) {
	if (msg->arrivedOn("inUpperLayer")) { //comes from transport layer (instance cannot be a router)
		// TODO:
		// * Use IPControlInfo to create IPDatagram
		// * send to network
	}

	else if (msg->arrivedOn("inLowerLayer")) {
		//message comes from the network:
		if (isRouter == 1) {
			// This is a router and we have to forward the datagram

			// TODO:
			// * Find out the destination network
			//   (IP address which ends with '.0', use IPAddress->getNetwork().str())
			// * Find out which gate is the right one
			//   use the forwarding table 'forwardingtable' initialized above
			// * Send the datagram to the appropriate gate.

		} else {
			// we are a host and not a router, so we have to hand it over to the next higher level.

			// TODO
			// * Create ControlInfo for upper layer ... application layer needs the data.
			// * Decapsulate message
			// * send to upper layer
		}
	}

}
