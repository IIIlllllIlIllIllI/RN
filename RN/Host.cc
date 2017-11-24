#include <string.h>
#include <omnetpp.h>
#include "Request_m.h"
#include "Response_m.h"
using namespace omnetpp;

class Host : public cSimpleModule{
    virtual void initialize() override;
    virtual void handleMessage(cMessage *msg) override;
};
Define_Module(Host);
void Host::initialize()
{
    if (strcmp("client", getName()) == 0) {
        Request *req = new Request("req");
        send(req, "out");
    }
    else{

    }
}
void Host::handleMessage(cMessage *msg)
{
    if (strcmp("client", getName()) == 0) {
        Response *resp=check_and_cast<Response *>(msg);
        send(resp, "out");
    }
    else{
        Request *req=check_and_cast<Request *>(msg);
        Response *resp = new Response("resp");
        send(resp, "out");
    }
}
