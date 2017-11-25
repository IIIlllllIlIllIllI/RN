#include <string.h>
#include <omnetpp.h>
#include "Request_m.h"
#include "Response_m.h"
using namespace omnetpp;
class Host: public cSimpleModule {
    virtual void initialize() override;
    virtual void handleMessage(cMessage *msg) override;
    virtual char* getRandomString();
    virtual uint32_t getRandomAddress();
};
Define_Module(Host);
void Host::initialize() {
    if (strcmp("client", getName()) == 0) {
        int intReq = par("intReq");
        int n = par("n");
        SimTime z = 0;
        for (int var = 0; var < n; var++) {
            cMessage* event = new cMessage("event");
            SimTime x = exponential(intReq);
            z = z + x;
            scheduleAt(simTime()+z, event);
        }
    } else {

    }
}
void Host::handleMessage(cMessage *msg) {
    if (strcmp("client", getName()) == 0) {
        if (msg->isSelfMessage()) {
            Request *req = new Request("req");
            char* s = getRandomString();
            for (int i = 0; i < 51; i++) {
                req->setHostname(i, s[i]);
            }
            send(req, "out");
        } else {
            Response *resp = check_and_cast<Response *>(msg);
        }
    } else {
        if (msg->isSelfMessage()) {
            Response *resp = new Response("resp");
            if (uniform(0, 1) > 0.9) {
                bubble("blubb");
                resp->setStatuscode(0, 'E');
                resp->setStatuscode(1, 'R');
                resp->setStatuscode(2, 'R');
                resp->setStatuscode(3, 0);
                resp->setAddress(0xFFFFFFFF);
            } else {
                resp->setStatuscode(0, 'O');
                resp->setStatuscode(1, 'K');
                resp->setStatuscode(2, 0);
                resp->setAddress(getRandomAddress());
            }
            send(resp, "out");
        } else {
            //Request *req=check_and_cast<Request *>(msg);
            cMessage* event = new cMessage("event");
            SimTime x = normal(1, 0.2);
            scheduleAt(simTime()+x, event);
        }

    }
}
char* Host::getRandomString() {
    char* s = new char[51];
    char alphanum[] =
            "0123456789" "ABCDEFGHIJKLMNOPQRSTUVWXYZ" "abcdefghijklmnopqrstuvwxyz";
    for (int i = 0; i < 51; i++) {
        s[i] = alphanum[rand() % (sizeof(alphanum) - 1)];
    }
    s[51] = 0;
    return s;
}
uint32_t Host::getRandomAddress() {
    double d = uniform(0, pow(2, 32));
    return (uint32_t) round(d);
}
