package huertix.uoc.sockets.mod;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientUdpMod implements SocketAppInt{

    private String serverHostname;
    private int serverPort;

    public ClientUdpMod() {

        serverHostname = System.getenv("UDP_SERVER_HOSTNAME") == null ?
                "localhost": System.getenv("UDP_SERVER_HOSTNAME");

        serverPort = System.getenv("UDP_SERVER_PORT") == null ?
                8000 : Integer.valueOf(System.getenv("UDP_SERVER_PORT"));

        this.run();
    }

    public void run() {

        try {
            //Declaration and assignation to variable address with an object type InetAddress
            // with parameter server hostname
            InetAddress address = InetAddress.getByName(serverHostname);
            //Declaration and assignation of an object type DatagramSocket, which will handle the packet to be sent
            DatagramSocket socket = new DatagramSocket();

            // loop to keep sending/receiving to/from server
            while (true) {

                // Declaration and initialization of an array which could handle data
                byte[] buffer = new byte[512];

                System.out.println(String.format("Requested datetime to -> %s:%s ", serverHostname, serverPort));
                System.out.print("Response from server: ");

                // Declaration and assignation of a new DatagramPacket object to a variable
                // passing parameters of buffer, buffer_length, destination server address and destination server port
                DatagramPacket request = new DatagramPacket(buffer, buffer.length, address, serverPort);
                // send the DatagramPacket to the server
                socket.send(request);

                // Declaration and assignation of a new DatagramPacket object to a variable
                // passing parameters of buffer, buffer_length
                DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);
                // receive the DatagramPacket from the server
                socket.receive(responsePacket);

                String response = new String(buffer, 0, responsePacket.getLength());

                System.out.println(response);
                System.out.println();

                // wait x millisecond before to start the loop again
                Thread.sleep(3000);
            }

        } catch (Exception e) {
            // print the exception message via console and exit the program
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
