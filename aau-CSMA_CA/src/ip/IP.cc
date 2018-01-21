#include "IP.h"

Define_Module(IP);

void IP::initialize() {
	// Check if this IP impl is a router or not:
	isRouter = 0;
}


void IP::handleMessage(cMessage *msg) {

	if (msg->arrivedOn("inUpperLayer")) { //comes from above

		IPDatagram *pkt = new IPDatagram("IPpkt");

		IPControlInfo *ipInfo = check_and_cast<IPControlInfo *>(msg->removeControlInfo());

		pkt->setDestIP(ipInfo->getDestIP());
		pkt->setSrcIP(ipInfo->getSrcIP());
		pkt->setProtocol(ipInfo->getProtocol());

        if (strcmp(this->getParentModule()->par("apptype").stringValue(), "server") == 0) {
            CSMAControlInfo *aInfo = new CSMAControlInfo();
            aInfo->setSrc(*new MACAddress(getParentModule()->par("macAddress").stringValue()));

            if (ipInfo->getDestIP().str() == "192.168.0.0") {
                aInfo->setDest(*new MACAddress("00-08-74-4C-7F-0B"));
            } else if  (ipInfo->getDestIP().str() == "192.168.1.1") {
                aInfo->setDest(*new MACAddress("00-08-74-4C-7F-1B"));
            } else  if (ipInfo->getDestIP().str() == "192.168.2.2") {
                aInfo->setDest(*new MACAddress("00-08-74-4C-7F-2B"));
            } else  if (ipInfo->getDestIP().str() == "192.168.3.3") {
                aInfo->setDest(*new MACAddress("00-08-74-4C-7F-3B"));
            } else  if (ipInfo->getDestIP().str() == "192.168.4.4") {
                aInfo->setDest(*new MACAddress("00-08-74-4C-7F-4B"));
            }
            pkt->setControlInfo(aInfo);

        } else {
            CSMAControlInfo *aInfo = new CSMAControlInfo();
            aInfo->setSrc(*new MACAddress(getParentModule()->par("macAddress").stringValue()));
            aInfo->setDest(*new MACAddress(getParentModule()->par("receiverMac").stringValue()));
            pkt->setControlInfo(aInfo);
        }

		// create link layer control info and attach to IP packet
		pkt->encapsulate((cPacket*) msg);
		// send to lower layer through appropriate gate
		int gate = 0;
		send(pkt, "outLowerLayer", gate);
		delete ipInfo;
	}

	else if (msg->arrivedOn("inLowerLayer")) {
		//message comes from the network:
			IPDatagram *ipDat = check_and_cast<IPDatagram *>(msg);
			// create IP control info
			IPControlInfo *ipInfo = new IPControlInfo();
			ipInfo->setDestIP(ipDat->getDestIP());
			ipInfo->setSrcIP(ipDat->getSrcIP());
			ipInfo->setProtocol(ipDat->getProtocol());

			// decapsulate payload and attach IP control info to segment
			cMessage *unpacked = ipDat->decapsulate();
			unpacked->setControlInfo(ipInfo);
			int gate = 0;
			send(unpacked, "outUpperLayer", gate);
			delete ipDat;		//not needed anymore

	}
}
