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
}
void Host::handleMessage(cMessage *msg)
{
}
