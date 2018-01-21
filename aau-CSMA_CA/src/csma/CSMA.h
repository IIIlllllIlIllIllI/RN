#ifndef __AAU_CSMA_CA_LSG_CSMA_H_
#define __AAU_CSMA_CA_LSG_CSMA_H_

#include <omnetpp.h>
#include <array>
#include "../3rdParty/IPv4Address.h"
#include "../3rdParty/MACAddress.h"

#include "CSMAControlInfo_m.h"
#include "CSMAFrame_m.h"

using namespace std;
using namespace inet;

class CSMA : public cSimpleModule
{
protected:
    virtual void initialize();
    virtual void handleMessage(cMessage *msg);
    virtual void handleSelfMessage(cMessage *msg);
    virtual void handleUpperLayerMessage(cMessage *msg);
    virtual void handleLowerLayerMessage(cMessage *msg);
    virtual void handleMessageForMe(CSMAFrame *frame);
    virtual void handleMessageForOthers(CSMAFrame *frame);
    virtual void findAndSetReachableDevices();
    virtual void sendToAllReachableDevices(cMessage *msg);

    cMessage *backoffTimeout;
    cMessage *rtsTimeout;
    cMessage *colTimeout;
    double maxBackoff;
    double DIFS;
    double SIFS;
    double transmitDuration;
    int numOfConcurrentMsgs;
    MACAddress *srcMAC;
    MACAddress *destMAC;
    std::list <CSMAFrame*> msgBuffer;
    std::vector<cModule*> reachableDevices;
};

#endif
