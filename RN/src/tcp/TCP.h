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

#ifndef TCP_H_
#define TCP_H_

#include <omnetpp.h>
#include "TCPSegment_m.h"
#include "TCPControlInfo_m.h"

class TCP : public cSimpleModule{

protected:
    virtual void initialize();
    virtual void handleMessage(cMessage *msg);
    virtual void handleAppMessage(cPacket *msg);
    virtual void handleTCPSegment(cPacket *msg);
    virtual void send_toup( TCPSegment* tcpsegment);
private:
    int seqNr = 0;      // sequence number 32 bit
    int ackNr = 0;      // acknowledgement number 32 bit
    int status=0;
    simtime_t timeout;  // timeout
    cMessage *timeoutEvent;  // holds pointer to the timeout self-message
    cMessage *curMsg;
    bool fromUpper=false;

};


#endif /* TCP_H_ */
