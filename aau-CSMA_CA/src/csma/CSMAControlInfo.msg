cplusplus {{
#include "../3rdParty/MACAddress.h"
using namespace inet;
}}

class noncobject MACAddress;

packet CSMAControlInfo {
    MACAddress src;
    MACAddress dest;
}
