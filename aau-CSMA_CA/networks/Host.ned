package networks;

import src.app.App;
import src.udp.UDP;
import src.ip.IP;
import src.csma.CSMA;

module Host
{
    parameters:
        string apptype;
        string clientIP;
        int clientPort;
        string serverIP;
        string macAddress;
        string receiverMac;
        int serverPort;
    gates:
       	input WLAN @directIn; // for receiving air frames
          
    submodules:
        application: App {
            parameters:
                appType = apptype;
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
        csma: CSMA {
            parameters:
                macAddress = macAddress;
                @display("p=89,358;i=block/layer");
        }
    connections:
        // application layer connections
        application.outLowerLayer --> udp.inUpperLayer;
        application.inLowerLayer <-- udp.outUpperLayer;

        // transport layer connections
        udp.outLowerLayer --> ip.inUpperLayer[0];
        udp.inLowerLayer <-- ip.outUpperLayer[0];

        // network layer connections
        ip.outLowerLayer[0] --> csma.inUpperLayer;
        ip.inLowerLayer[0] <-- csma.outUpperLayer;
        
		// data link layer connections
        csma.inLowerLayer  <-- WLAN;
}
