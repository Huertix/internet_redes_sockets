package huertix.uoc.sockets.original;

import java.net.*;
import java.io.*;

public class clienteudp {

	public static void main(String argv[]) {
		// program entry point

		if (argv.length == 0) {
			// check if arguments when invoking the program is 0
			// then print error and exit the program
			System.err.println("java huertix.uoc.sockets.original.clienteudp servidor");
			System.exit(1);
		}

		// Wait and get input from user. User has to use the terminal from to introduce the string
		// where the program was ran to introduce the dessired message to be sent
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// Declaration of a Socket variable
		DatagramSocket socket;
		// Declaration of a InetAddress variable
		InetAddress address;

		// Declaration and initialization of an array which could handle data
		byte[] mensaje_bytes = new byte[256];

		// Declaration and initialization of a String variable
		String mensaje = "";

		// Declaration of a DatagramPacket variable
		DatagramPacket paquete;

		// Getting user message size
		mensaje_bytes = mensaje.getBytes();

		try {
			// Assignation of an object type DatagramSocket
			// it is an UDP socket connection
			socket = new DatagramSocket();

			// assignation to variable address with an object type InetAddress, based in the IP coming
			// from the arguments supplied in the moment that the program was ran.
			address = InetAddress.getByName(argv[0]);

			// loop do-while
			do {
				// it gets first the input fromt the user by line
				mensaje = in.readLine();
				// Getting user message size
				mensaje_bytes = mensaje.getBytes();

				// Assignation of a new DatagramPacket object to a variable
				// passing parameters of message object, message_length,
				// destination server address and destination server port
				paquete = new DatagramPacket(mensaje_bytes, mensaje.length(), address, 6000);

				// send the DatagramPacket to the server
				socket.send(paquete);

				// is the message has reached the end string, it gets out from the loop
			} while (!mensaje.startsWith("end"));
		}
		// Checking whatever exception may happend
		catch (Exception e) {
			// print the exception message via console and exit the program
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}
}
