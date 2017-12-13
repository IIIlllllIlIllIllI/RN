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

#include "TCP.h"
#include <stdio.h>
#include "../3rdParty/IPv4Address.h"
#include "../3rdParty/IPv6Address.h"

Define_Module(TCP);

void TCP::initialize(){
    // TODO Initialise seqn and ackn.
}


void TCP::handleMessage(cMessage *msg){

    // TODO Handle timeouts.
    // TODO Simulate packet loss.

    if (msg->arrivedOn("fromUpperLayer")) {
        // Comes from appliction.
        this->handleAppMessage((cPacket*)msg);
    }

    else if (msg->arrivedOn("fromLowerLayer")) {
        // Comes from lower layer.
        this->handleTCPSegment((cPacket*) msg);
    }
}

void TCP::handleAppMessage(cPacket *msg) {

    // TODO handle input from upper layer

}

void TCP::handleTCPSegment(cPacket *msg) {

    // TODO handle input from lower layer
}
