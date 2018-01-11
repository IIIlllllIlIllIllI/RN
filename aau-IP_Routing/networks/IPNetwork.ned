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
import networks.Host;
import networks.Router;

channel fastChannel extends ned.DelayChannel {
    delay = 100ms;
}

channel mediumChannel extends ned.DelayChannel {
    delay = 300ms;
}

channel mediumChannel2 extends ned.DelayChannel {
    delay = 200ms;
}

channel slowChannel extends ned.DelayChannel {
    delay = 700ms;
}

network IPNetwork
{
    submodules:
        client1: Host {
            parameters:
                appType    = "client";
                number     = 42;
                command    = 13;
                clientPort = 8080;
                serverPort = 8080;
                clientIP   = "192.168.0.10";
                serverIP   = "192.168.2.30";
            @display("i=device/pc2;p=81,88");
        }
        client2: Host {
            parameters:
                appType    = "client";
                number     = 7;
                command    = 3;
                clientPort = 8080;
                serverPort = 8080;
                clientIP   = "192.168.1.20";
                serverIP   = "192.168.2.30";
            @display("i=device/pc;p=426,95");
        }
        client3: Host {
            parameters:
                appType    = "client";
                number     = 156;
                command    = 35;
                clientPort = 8080;
                serverPort = 8080;
                clientIP   = "192.168.5.50";
                serverIP   = "192.168.2.30";
            @display("p=457,383;i=device/pc3");
        }
        server: Host {
            parameters:
                appType    = "server";
                number     = 11;
                command    = 11;
                clientPort = 8080;
                serverPort = 8080;
                clientIP   = "192.168.2.30";
                serverIP   = "192.168.2.30";
            @display("i=device/server;p=68,393");
        }
        router01: Router {
            @display("p=166,173;i=device/smallrouter");
            gates:
                inNet[3];
                outNet[3];
        }
        router02: Router {
            @display("p=343,173;i=device/smallrouter");
            gates:
                inNet[4];
                outNet[4];
        }
        router03: Router {
            @display("p=166,320;i=device/smallrouter");
            gates:
                inNet[3];
                outNet[3];
        }
        router04: Router {
            @display("p=256,248;i=device/smallrouter");
            gates:
                inNet[4];
                outNet[4];
        }
        router05: Router {
            @display("p=343,320;;i=device/smallrouter");
            gates:
                inNet[4];
                outNet[4];
        }
        connections:
        // Hosts 2 routers ...
        client1.outNet --> fastChannel --> router01.inNet++;
        client1.inNet <-- fastChannel <-- router01.outNet++;

        client2.outNet --> fastChannel --> router02.inNet++;
        client2.inNet <-- fastChannel <-- router02.outNet++;

        client3.outNet --> fastChannel --> router05.inNet++;
        client3.inNet <-- fastChannel <-- router05.outNet++;

        server.outNet --> fastChannel --> router03.inNet++;
        server.inNet <-- fastChannel <-- router03.outNet++;

        // core network:
        router01.outNet++ --> fastChannel --> router02.inNet++;
        router01.inNet++ <-- fastChannel <-- router02.outNet++;

        router04.outNet++ --> slowChannel --> router03.inNet++;
        router04.inNet++ <-- slowChannel <-- router03.outNet++;

        router01.outNet++ --> fastChannel --> router04.inNet++;
        router01.inNet++ <-- fastChannel <-- router04.outNet++;

        router02.outNet++ --> slowChannel --> router04.inNet++;
        router02.inNet++ <-- slowChannel <-- router04.outNet++;

        router02.outNet++ --> fastChannel --> router05.inNet++;
        router02.inNet++ <-- fastChannel <-- router05.outNet++;

        router03.outNet++ --> fastChannel --> router05.inNet++;
        router03.inNet++ <-- fastChannel <-- router05.outNet++;

        router04.outNet++ --> mediumChannel --> router05.inNet++;
        router04.inNet++ <-- mediumChannel <-- router05.outNet++;

}



