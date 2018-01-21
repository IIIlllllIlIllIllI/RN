package networks;

import src.app.App;
import src.udp.UDP;
import src.ip.IP;
import src.csma.CSMA;
import networks.Host;

network CSMANetwork
{
    @display("bgb=607,348,white;bgl=2;bgi=background/floorplan,f");
    submodules:
        accessPointServer: Host {
            parameters:
                apptype = "server";
                clientIP = "192.168.0.0";
                serverIP = "192.168.0.0";
                clientPort = 23456;
                serverPort = 23456;
                @display("i=device/wifilaptop,,50;p=355,196;is=l;r=250,yellow,,4");
        }
        client1: Host {
            parameters:
                apptype = "client";
                clientIP = "192.168.1.1";
                serverIP = "192.168.0.0";
                clientPort = 23456;
                serverPort = 23456;
                @display("i=device/laptop;p=161,100");
        }
        client2: Host {
            parameters:
                apptype = "client";
                clientIP = "192.168.2.2";
                serverIP = "192.168.0.0";
                clientPort = 23456;
                serverPort = 23456;
                @display("i=device/laptop;p=312,54");
        }
        client3: Host {
            parameters:
                apptype = "client";
                clientIP = "192.168.3.3";
                serverIP = "192.168.0.0";
                clientPort = 23456;
                serverPort = 23456;
                @display("i=device/laptop;p=168,267");
        }
        client4: Host {
            parameters:
                apptype = "client";
                clientIP = "192.168.4.4";
                serverIP = "192.168.0.0";
                clientPort = 23456;
                serverPort = 23456;
                @display("i=device/pocketpc;p=527,294;t=\"Hidden Station\"");
        }
}
