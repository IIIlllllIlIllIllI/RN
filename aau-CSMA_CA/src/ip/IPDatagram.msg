cplusplus {{
#include "../3rdParty/IPv4Address.h"
}};

class noncobject inet::IPv4Address;

packet IPDatagram
{
		inet::IPv4Address destIP;
		inet::IPv4Address srcIP;
		short protocol;
}
