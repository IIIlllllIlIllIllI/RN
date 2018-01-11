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

import src.ip.IP;

module Router
{
    parameters:
        // define parameters here
    gates:
        input inNet[];
        output outNet[];
    submodules:
        ip: IP {
            @display("p=76,64;i=block/routing");
            gates:
                inLowerLayer[sizeof(inNet)];
                outLowerLayer[sizeof(outNet)];
                inUpperLayer[0];
                outUpperLayer[0];
        }
    connections:
        for i=0..sizeof(outNet)-1 {
            inNet[i] --> ip.inLowerLayer[i];
            outNet[i] <-- ip.outLowerLayer[i];
        }
}
