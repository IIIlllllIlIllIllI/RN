#include "CSMA.h"

Define_Module(CSMA);

void CSMA::initialize() {
    this->backoffTimeout = NULL;
    this->rtsTimeout = NULL;
    this->colTimeout = NULL;
    this->maxBackoff = 6.0;
    this->SIFS = 1.0;
    this->DIFS = 3.0;
    this->transmitDuration = 10.0;
    this->numOfConcurrentMsgs = 0;
    this->srcMAC = new MACAddress(
            getParentModule()->par("macAddress").stringValue());
    this->destMAC = new MACAddress(
            getParentModule()->par("receiverMac").stringValue());

    findAndSetReachableDevices();
}

void CSMA::handleMessage(cMessage *msg) {
    if (msg->isSelfMessage()) {
        // Msg is a timeout.
        handleSelfMessage(msg);

    } else if (msg->arrivedOn("inUpperLayer")) {
        // Comes from above.
        handleUpperLayerMessage(msg);

    } else if (msg->arrivedOn("inLowerLayer")) {
        // Comes from net.
        handleLowerLayerMessage(msg);
    }
}

void CSMA::handleSelfMessage(cMessage *msg) {

    if (msg == backoffTimeout) {
        // Retransmit RTS after backoff.
        CSMAFrame* rtsFrame = new CSMAFrame("RTS");
        rtsFrame->setType(RTS);
        rtsFrame->setSrc(*this->srcMAC);
        rtsFrame->setDest(*this->destMAC);
        sendToAllReachableDevices(rtsFrame);
        // Schedule another timeout for expected response to RTS.
        if (rtsTimeout != NULL) {
            EV << "ERROR\n";
        } else {
            rtsTimeout = new cMessage("rtsTimeout");
            scheduleAt(simTime() + this->DIFS, rtsTimeout);
        }

        // Reset handled timeout
        delete msg;
        backoffTimeout = NULL;

    } else if (msg == rtsTimeout) {
        // Create random backoff before trying to re-send an RTS.
        if (backoffTimeout != NULL) {
            EV << "ERROR\n";
        } else {
            backoffTimeout = new cMessage("backoffTimeout");
            scheduleAt(simTime() + this->SIFS * uniform(0, 1), backoffTimeout);
        }
        // Reset handled timeout
        delete msg;
        rtsTimeout = NULL;

    } else if (msg == colTimeout) {
        // TODO:
        // Check if more than one msg arrived during the timeout.
        // If so, it's a collision -> Delete messages and reset counter.
        // Reset handled timeout

        if (numOfConcurrentMsgs > 1) {
            //collision detected

            numOfConcurrentMsgs = 0;

        } else {
            //send CTS
            CSMAFrame* ctsFrame = new CSMAFrame("CTS");
            ctsFrame->setType(CTS);
            ctsFrame->setSrc(*this->srcMAC);
            ctsFrame->setDest(*this->destMAC);
            sendToAllReachableDevices(ctsFrame);

        }
        //reset timeout
        delete (msg);
        colTimeout = NULL;

    }
}

void CSMA::handleUpperLayerMessage(cMessage *msg) {
    // TODO
    CSMAFrame *csmaframe = new CSMAFrame("CSMAF");
    CSMAControlInfo *CSMAInfo = check_and_cast<CSMAControlInfo *>(
            msg->removeControlInfo());
    csmaframe->setSrc(CSMAInfo->getSrc());
    csmaframe->setDest(CSMAInfo->getDest());
    sendToAllReachableDevices(csmaframe);

}

void CSMA::handleLowerLayerMessage(cMessage *msg) {
    // Msg is a CSMA frame.
    CSMAFrame *frame = check_and_cast<CSMAFrame *>(msg);

    if (frame->getDest().equals(*srcMAC)) {
        // It is for us
        handleMessageForMe(frame);

    } else {
        // It is for someone else
        handleMessageForOthers(frame);
    }
}

void CSMA::handleMessageForMe(CSMAFrame *frame) {
    switch (frame->getType()) {
    case RTS: {
        if (strcmp(this->getParentModule()->getName(), "accessPointServer")
                == 0) {
            if (colTimeout == NULL) {
                //start new colTimeout
                colTimeout = new cMessage("CollisionTimeout");
                scheduleAt(simTime() + this->SIFS, colTimeout);
                //set number of concurrent Messages to one
                numOfConcurrentMsgs = 1;
            } else {
                //found collision
                numOfConcurrentMsgs++;
            }
        }
        //TODO
        //add to msg queue
        delete frame;
        break;
    }
    case CTS: {
        if (rtsTimeout != NULL) {
            cancelAndDelete(rtsTimeout);
            rtsTimeout = NULL;
        }
        //TODO
        //send data
        break;
    }
    case DATA: {
        // TODO

        break;
    }
    case ACK: {
        // TODO

        break;
    }
    default:
        EV << "Message type not recognised.\n";
        break;
    }
}

void CSMA::handleMessageForOthers(CSMAFrame *frame) {
    switch (frame->getType()) {
    case RTS: {
        //drop frame
        delete frame;
        break;
    }
    case CTS: {
        //restart backoffTimeout
        if (backoffTimeout != NULL) {
            cancelAndDelete(backoffTimeout);
        }
        backoffTimeout = new cMessage("backoffTimeout");
        scheduleAt(simTime() + frame->getResDuration(), backoffTimeout);
        delete frame;
        break;
    }
    case DATA: {
        // TODO?
        break;
    }
    case ACK: {
        //drop frame
        delete frame;
        break;
    }
    default:
        EV << "Message type not recognised.\n";
        break;
    }
}

void CSMA::sendToAllReachableDevices(cMessage *msg) {
    for (uint i = 0; i < reachableDevices.size(); ++i) {
        sendDirect(msg->dup(), reachableDevices.at(i), "WLAN");
    }
}

/**
 * Allows to set which devices are reachable by which.
 * Individually customisable for each device by commenting out the ones
 * that shoud not be reached.
 */
void CSMA::findAndSetReachableDevices() {
    std::string name = this->getParentModule()->getName();
    reachableDevices.reserve(5);

    if (name == "accessPointServer") {
        reachableDevices.push_back(
                getParentModule()->getParentModule()->getSubmodule("client1"));
        reachableDevices.push_back(
                getParentModule()->getParentModule()->getSubmodule("client2"));
        reachableDevices.push_back(
                getParentModule()->getParentModule()->getSubmodule("client3"));
        reachableDevices.push_back(
                getParentModule()->getParentModule()->getSubmodule("client4"));
//        reachableDevices.push_back(getParentModule()->getParentModule()->getSubmodule("accessPointServer"));

    } else if (name == "client1") {
//        reachableDevices.push_back(getParentModule()->getParentModule()->getSubmodule("client1"));
        reachableDevices.push_back(
                getParentModule()->getParentModule()->getSubmodule("client2"));
        reachableDevices.push_back(
                getParentModule()->getParentModule()->getSubmodule("client3"));
//        reachableDevices.push_back(getParentModule()->getParentModule()->getSubmodule("client4"));
        reachableDevices.push_back(
                getParentModule()->getParentModule()->getSubmodule(
                        "accessPointServer"));

    } else if (name == "client2") {
        reachableDevices.push_back(
                getParentModule()->getParentModule()->getSubmodule("client1"));
//        reachableDevices.push_back(getParentModule()->getParentModule()->getSubmodule("client2"));
        reachableDevices.push_back(
                getParentModule()->getParentModule()->getSubmodule("client3"));
//        reachableDevices.push_back(getParentModule()->getParentModule()->getSubmodule("client4"));
        reachableDevices.push_back(
                getParentModule()->getParentModule()->getSubmodule(
                        "accessPointServer"));

    } else if (name == "client3") {
        reachableDevices.push_back(
                getParentModule()->getParentModule()->getSubmodule("client1"));
        reachableDevices.push_back(
                getParentModule()->getParentModule()->getSubmodule("client2"));
//        reachableDevices.push_back(getParentModule()->getParentModule()->getSubmodule("client3"));
//        reachableDevices.push_back(getParentModule()->getParentModule()->getSubmodule("client4"));
        reachableDevices.push_back(
                getParentModule()->getParentModule()->getSubmodule(
                        "accessPointServer"));

    } else if (name == "client4") {
//        reachableDevices.push_back(getParentModule()->getParentModule()->getSubmodule("client1"));
//        reachableDevices.push_back(getParentModule()->getParentModule()->getSubmodule("client2"));
//        reachableDevices.push_back(getParentModule()->getParentModule()->getSubmodule("client3"));
//        reachableDevices.push_back(getParentModule()->getParentModule()->getSubmodule("client4"));
        reachableDevices.push_back(
                getParentModule()->getParentModule()->getSubmodule(
                        "accessPointServer"));
    }
}
