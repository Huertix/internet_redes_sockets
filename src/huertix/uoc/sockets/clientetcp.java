package src.huertix.uoc.sockets;

import java.net.*;
import java.io.*;

public class clientetcp {

	public static void main(String argv[]) {
		// program entry point

		if (argv.length == 0) {
			// check if arguments when invoking the program is 0
			// then print error and exit the program
			System.err.println("java src.huertix.uoc.sockets.clientetcp servidor");
			System.exit(1);
		}

		// Wait and get input from user. User has to use the terminal from to introduce the string
		// where the program was ran to introduce the dessired message to be sent
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// Declaration of a Socket variable
		Socket socket;
		// Declaration of a InetAddress variable
		InetAddress address;

		// Declaration and initialization of an array which could handle data
		// not in use in this program
		byte[] mensaje_bytes = new byte[256];

		// Declaration and initialization of a String variable
		String mensaje = "";

		// Try/Catch block, which will allow us to catch possible exceptions
		try {

			// assignation to variable address with an object type InetAddress, based in the IP coming
			// from the arguments supplied in the moment that the program was ran.
			address = InetAddress.getByName(argv[0]);

			// Assignation of an object type Socket with specified address and port 6001
			// it is a TCP socket connection
			socket = new Socket(address, 6001);

			//Java DataOutputStream class allows an application to write primitive Java data types to the
			// output stream in a machine-independent way. Java application generally uses the data output
			// stream to write data that can later be read by a data input stream.
			//
			// In this case we are preparing the client/socket to send the message to the server/socket
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());


			// loop do-while
			do {
				// it gets first the input fromt the user by line
				mensaje = in.readLine();
				// it writes the user message in UTF format, push it to the server/socket
				out.writeUTF(mensaje);

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
