cplusplus {{
#include "../3rdParty/MACAddress.h"
using namespace inet;
}}

class noncobject MACAddress;

enum Type {
    RTS = 1;
    CTS = 2;
	DATA = 3;
    ACK = 4;
	   
}

class noncobject Type;

packet CSMAFrame {
    Type type;
    double resDuration;
    MACAddress src;
    MACAddress dest;
}
