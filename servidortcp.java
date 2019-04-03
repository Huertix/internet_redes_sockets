import java.net.*;
import java.io.*;

public class servidortcp {

	public static void main(String argv[]) {
		// program entry point

		// Declaration of a ServerSocket variable
		ServerSocket socket;


		try {

			// assignation to variable of a new TCP ServerSocket object with port 6001
			socket = new ServerSocket(6001);

			// preparing socket to listen and accept connection in defined port
			Socket socket_cli = socket.accept();

			// process and assign client message received to a new DataInputStream object
			DataInputStream in = new DataInputStream(socket_cli.getInputStream());

			// forever loop do-while
			do {
				// Declaration and initialization of a String variable
				String mensaje = "";
				// it reads the user message in UTF format
				mensaje = in.readUTF();
				// print user's message via standard output
				System.out.println(mensaje);

				// it is an always True condition
			} while (1 > 0);
		}
		// Checking whatever exception may happend
		catch (Exception e) {
			// print the exception message via console and exit the program
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}
}
