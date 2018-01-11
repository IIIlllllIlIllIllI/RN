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

#ifndef IP_H_
#define IP_H_

#include <omnetpp.h>
#include <string>
#include <map>

#include "../3rdParty/IPv4Address.h"
#include "IP.h"

#include "IPDatagram_m.h"
#include "IPControlInfo_m.h"

using namespace std;

int routingTableSize = 18;

// defines the connections for the routing table
// col 0: router name
// col 1: destination network
// col 2: network mask
// col 3: interface IP (in our scenario not required but may be helpful)
// col 4: interface number (gate index according to .ned file)
string routingtable[18][5] =
  {
	{"router01", "192.168.0.0", "255.255.255.0", "192.168.0.1", "0"},
	{"router01", "192.168.1.0", "255.255.255.0", "0.0.0.0", "1"},
	{"router01", "192.168.2.0", "255.255.255.0", "0.0.0.0", "2"},

	{"router02", "192.168.0.0", "255.255.255.0", "0.0.0.0", "1"},
	{"router02", "192.168.1.0", "255.255.255.0", "192.168.1.1", "0"},
	{"router02", "192.168.2.0", "255.255.255.0", "0.0.0.0", "2"},

	{"router03", "192.168.0.0", "255.255.255.0", "0.0.0.0", "2"},
	{"router03", "192.168.1.0", "255.255.255.0", "0.0.0.0", "1"},
	{"router03", "192.168.2.0", "255.255.255.0", "192.168.2.1", "0"},
	{"router03", "192.168.5.0", "255.255.255.0", "0.0.0.0", "2"},

	{"router04", "192.168.0.0", "255.255.255.0", "0.0.0.0", "1"},
	{"router04", "192.168.1.0", "255.255.255.0", "0.0.0.0", "2"},
	{"router04", "192.168.2.0", "255.255.255.0", "0.0.0.0", "0"},
	{"router04", "192.168.5.0", "255.255.255.0", "0.0.0.0", "3"},

	{"router05", "192.168.0.0", "255.255.255.0", "0.0.0.0", "1"},
	{"router05", "192.168.1.0", "255.255.255.0", "0.0.0.0", "1"},
	{"router05", "192.168.2.0", "255.255.255.0", "0.0.0.0", "1"},
	{"router05", "192.168.5.0", "255.255.255.0", "192.168.5.0", "0"}
};

class IP : public cSimpleModule
{
private:
	int isRouter;
	// forwardingtable stores the target network and the index of the gate that should be used.
	std::map<std::string, int> forwardingtable;

protected:
	virtual void initialize();
	virtual void handleMessage(cMessage *msg);
};

#endif /* IP_H_ */
