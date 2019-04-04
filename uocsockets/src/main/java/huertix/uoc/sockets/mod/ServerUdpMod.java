package huertix.uoc.sockets.mod;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class ServerUdpMod implements SocketAppInt{
    private int serverPort;

    public ServerUdpMod() {

        serverPort = System.getenv("UDP_SERVER_PORT") == null ?
                8000 : Integer.valueOf(System.getenv("UDP_SERVER_PORT"));

        this.run();
    }

    public void run() {

        try {

            //Declaration and assignation of an object type DatagramSocket,
            // which will handle the packet to be received

            DatagramSocket serverSocket = new DatagramSocket(serverPort);

            while (true) {

                // Declaration and initialization of an array which could handle data
                byte[] buffer = new byte[512];

                System.out.println("Waiting client communication...");
                // Declaration and assignation of a new DatagramPacket object to a variable
                // passing parameters of buffer, buffer_length
                DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
                // receive the DatagramPacket from client
                serverSocket.receive(receivePacket);

                // extract address and port from client packet request
                InetAddress clientAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();

                System.out.println(String.format("Received request from: %s ", clientAddress.getHostAddress()));

                // get current datetime
                String datetime = new Date().toString();

                // transform String to bytes
                buffer = datetime.getBytes();

                // Prepare packer to be sent
                DatagramPacket sendPacket =
                        new DatagramPacket(buffer, buffer.length, clientAddress, port);

                // send packet back to client
                serverSocket.send(sendPacket);
                System.out.println(String.format("Response: %s sent to: %s ", datetime, clientAddress.getHostAddress()));
            }

        } catch (Exception e) {
            // print the exception message via console and exit the program
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
