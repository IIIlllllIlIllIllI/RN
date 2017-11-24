#include <string.h>
#include <omnetpp.h>
using namespace omnetpp;

class Host : public cSimpleModule{
    virtual void initialize() override;
    virtual void handleMessage(cMessage *msg) override;
};
Define_Module(Host);
void Host::initialize()
{
    if (strcmp("client", getName()) == 0) {
        cMessage *msg = new cMessage("testMsg");
        send(msg, "out");
    }
    else{

    }
}
void Host::handleMessage(cMessage *msg)
{
    if (strcmp("client", getName()) == 0) {
        send(msg, "out");
    }
    else{
        send(msg, "out");
    }
}
