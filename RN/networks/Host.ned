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

package networks;

import src.app.App;
import src.udp.UDP;
import src.ip.IP;

module Host
{
    parameters:
        string appType;
        string clientIP;
        int clientPort;
        string serverIP;
        int serverPort;
        int number;
        int command;
    gates:
        input inNet;
        output outNet;
    submodules:
        app: App {
            parameters:
                appType = appType;
            @display("p=89,74;i=block/user");
        }
        udp: UDP {
            @display("p=89,189;i=block/layer");
        }
        ip: IP {
            @display("p=89,284;i=block/routing");
            gates:
                outLowerLayer[1];
                inLowerLayer[1]; 
                inUpperLayer[1];
                outUpperLayer[1];             
        }
    connections:
        // application layer connections
        app.outLowerLayer --> Channel --> udp.inUpperLayer;
        app.inLowerLayer <-- Channel <-- udp.outUpperLayer;

        // transport layer connections
        udp.outLowerLayer --> Channel --> ip.inUpperLayer[0];
        udp.inLowerLayer <-- Channel <-- ip.outUpperLayer[0];

        // network layer connections
		ip.outLowerLayer[0] --> Channel --> outNet;
		ip.inLowerLayer[0] <-- Channel <-- inNet;
}

channel Channel extends ned.DelayChannel {
    delay = 100ms;
}