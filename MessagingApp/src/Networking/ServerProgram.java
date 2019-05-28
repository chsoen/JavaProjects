package Networking;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;
import java.util.Date;

public class ServerProgram {
    static Server server;

    public static void main(String[] args) throws IOException {
        System.out.println("Creating Server");
        server = new Server();
        server.bind(Network.port);
        server.start();
        Network.register(server);

        server.addListener(new Listener() {
            @Override
            public void disconnected(Connection connection) {

            }

            @Override
            public void received(Connection connection, Object o) {

            }

            @Override
            public void connected(Connection c) {
                System.out.println("Received a connection from " + c.getRemoteAddressTCP().getHostString());
                sendMessage(c);
            }
        });

        System.out.println("Server is operational!");
    }

    private static void sendMessage(Connection c) {
        Network.PacketMessage packetMessage = new Network.PacketMessage();
        packetMessage.message = "Hello client";
        c.sendTCP(packetMessage);
        //c.sendUDP(packetMessage);
    }
}