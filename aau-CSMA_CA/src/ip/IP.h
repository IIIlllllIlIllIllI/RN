#ifndef IP_H_
#define IP_H_

#include <omnetpp.h>
#include <string>
#include <map>

#include "../3rdParty/IPv4Address.h"
#include "IP.h"

#include "IPDatagram_m.h"
#include "IPControlInfo_m.h"

#include "../csma/CSMAControlInfo_m.h"

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
	// forwardingtable staores the target network and the index of the gate that should be used.
	std::map<std::string, int> forwardingtable;

protected:
	virtual void initialize();
	virtual void handleMessage(cMessage *msg);
};

#endif /* IP_H_ */
