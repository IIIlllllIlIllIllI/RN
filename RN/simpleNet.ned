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
simple Host
{
    parameters:
        int intReq = default(1);
        int n = default(1);
        @display("i=device/lan");
    gates:
        input in;
        output out;
}

network SimpleNet
{
    @display("bgb=265,231;i=misc/cloud");
    submodules:
        server: Host {
            @display("i=device/server;p=42,60");
        }
        client: Host {
            @display("p=187,158;i=device/pc4");
        }
    connections:
        server.out --> {  datarate = 10Mbps; delay = 5ms; } --> client.in;
        server.in <-- {  datarate = 10Mbps; delay = 5ms; } <-- client.out;
}
