//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with this program.  If not, see http://www.gnu.org/licenses/.
//
// (c) 2017 Christian Timmerer, Alpen-Adria-Universität Klagenfurt / Bitmovin Inc.
//          christian.timmerer@itec.aau.at / christian.timmerer@bitmovin.com
//
// 621.800 (17W) Computer Networks and Network Programming

#include "App.h"
#include "AppMessage_m.h"

Define_Module(App);

void App::initialize() {
	EV << "-- App init\n";
	EV << "\n";

	EV << " type: " << (this->par("appType")).stringValue() << "\n";
	EV << " IP: " << getParentModule()->par("clientIP").stringValue() << "\n";

	this->clientIP = new inet::IPv4Address(
			getParentModule()->par("clientIP").stringValue());
	this->serverIP = new inet::IPv4Address(
			getParentModule()->par("serverIP").stringValue());

	number = 1; //getParentModule()->par("number");
	command = 2; //getParentModule()->par("command");
	if (opp_strcmp(par("appType"), "client") == 0) {
		srcPort = getParentModule()->par("clientPort");
		destPort = getParentModule()->par("serverPort");
		// send first message ...
		event = new cMessage("event");
		scheduleAt(simTime() + 4, event);
	} else {
		srcPort = getParentModule()->par("serverPort");
		destPort = getParentModule()->par("clientPort");
	}

	EV << "App initialized\n";

}

void App::handleMessage(cMessage *msg) {
	if (msg->isSelfMessage()) {
		AppMessage *firstMessage = new AppMessage("data 2 server");
		firstMessage->setNumber(this->number);
		firstMessage->setCommand(this->command);
		// adding control info:
		UDPControlInfo *UDPInfo = new UDPControlInfo();
		UDPInfo->setSrcIP(*this->clientIP);
		UDPInfo->setDestIP(*this->serverIP);
		UDPInfo->setSrcPort(srcPort);
		EV << "-- Sending first message to port " << destPort << " at "
				<< getParentModule()->par("serverIP").stringValue() << "\n";
		UDPInfo->setDestPort(destPort);
		firstMessage->setControlInfo(UDPInfo);
		bubble("Sending message to server.");
		send(firstMessage, "outLowerLayer");
	} else {
		// receive and answer ...
		AppMessage *message = check_and_cast<AppMessage *> (msg);
		EV << "-- Message received, host: " << par("appType").stringValue() << "\n";
		EV << "  got msg: " << message->getNumber() << "\n";
		EV << "  command was: " << message->getCommand() << "\n";

		UDPControlInfo *udpInfo = check_and_cast<UDPControlInfo *> (
				msg->removeControlInfo());
		AppMessage *returnMessage = new AppMessage("data");
		returnMessage->setNumber(message->getNumber() + message->getCommand());
		returnMessage->setCommand(this->command);
		// adding control info:
		UDPControlInfo *UDPInfo = new UDPControlInfo();
		UDPInfo->setSrcIP(*this->clientIP);
		UDPInfo->setDestIP(udpInfo->getSrcIP());
		UDPInfo->setSrcPort(srcPort);
		EV << "-- Sending first message to port " << destPort << " at "
				<< getParentModule()->par("serverIP").stringValue() << "\n";
		UDPInfo->setDestPort(udpInfo->getDestPort());
		returnMessage->setControlInfo(UDPInfo);
		bubble("Sending message back.");
		send(returnMessage, "outLowerLayer");
		delete udpInfo;
	}
	// deleting the original message ...
	delete msg;
}

