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
