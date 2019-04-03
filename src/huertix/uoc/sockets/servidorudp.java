package src.huertix.uoc.sockets;

import java.net.*;
import java.io.*;

public class servidorudp {

	public static void main(String argv[]) {
		// program entry point

		// Declaration of a DatagramSocket variable
		DatagramSocket socket;

		// Declaration and assignation of a boolean variable
		boolean fin = false;

		try {
			// assignation to variable of a new UDP DatagramSocket object with port 6000
			socket = new DatagramSocket(6000);

			// forever loop do-while
			do {
				// Declaration and initialization of an array which could handle data
				byte[] mensaje_bytes = new byte[256];

				// Declaration and initialization of a DatagramPacket object
				// arguments are the array which will be used as a buffer, and the length of this buffer
				DatagramPacket paquete = new DatagramPacket(mensaje_bytes, 256);

				// socker UDP is ready to receive a paket and will use the Datagrampacket object to handle it
				socket.receive(paquete);

				String clientAddress = paquete.getAddress().getHostAddress();
				System.out.println(String.format("Message from: %s", clientAddress));

				// Declaration and initialization of a String variable
				String mensaje = "";

				// Reasignation of an String object providing buffer size
				mensaje = new String(mensaje_bytes);

				// print user's message via standard output
				System.out.println(mensaje);

				// condition to check if message has reached the end
				// if yes, we assing True to the boolean variable
				if (mensaje.startsWith("end"))
					fin = true;

				//if boolean variable "fin" is True, it gets of from the loop
			} while (!fin);
		}
		// Checking whatever exception may happend
		catch (Exception e) {
			// print the exception message via console and exit the program
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}
}
