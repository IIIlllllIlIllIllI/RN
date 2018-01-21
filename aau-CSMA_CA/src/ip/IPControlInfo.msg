
cplusplus {{
#include "../3rdParty/IPv4Address.h"
}}

class noncobject inet::IPv4Address;

enum ProtocolTypes
{

    ip = 4;
    tcp = 6;
    udp = 17;
}

packet IPControlInfo
{
    inet::IPv4Address destIP;
    inet::IPv4Address srcIP;
    short protocol;
}
