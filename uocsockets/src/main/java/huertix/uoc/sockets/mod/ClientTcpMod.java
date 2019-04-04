package huertix.uoc.sockets.mod;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;


public class ClientTcpMod {

	private final static int SERVER_PORT = 80;
	private final static String USER_NAME = "dhuertav@uoc.edu";

	/**
	 * Validate host provided by user
	 *
	 * @param  String hostname
	 * @return InetAddress object
	 * @throws Exception
	 */
	private static InetAddress validateHostname(String hostname) throws Exception {
		try {
			hostname = hostname.trim();
			InetAddress address = InetAddress.getByName(hostname);
			return address;
		} catch (UnknownHostException e) {
			System.err.println(String.format("Hostname provided: %s is not correct", hostname));
			throw e;
		}
	}

	/**
	 * Prepare request headers
	 *
	 * @param String hostname
	 * @param String path or route in host
	 * @return Arraylist<String> with all headers
	 */
	private static ArrayList<String> makeRequestHeader(String hostname, String path) {
		ArrayList<String> requestHeaders = new ArrayList<>();
		requestHeaders.add(String.format("GET /%s HTTP/1.1\r\n", path));
		requestHeaders.add(String.format("Host: %s\r\n", hostname));
		requestHeaders.add("Accept: */*\r\n");
		requestHeaders.add(String.format("User-agent: %s\r\n", USER_NAME));
		requestHeaders.add("\r\n");
		return requestHeaders;
	}

	public static void main(String argv[]) {
		try {

			// Get the hostname by user
			System.out.print("Please enter hostname: http://");
			Scanner userInput = new Scanner(System.in);

			// split hostname if user provides a path within the hostname
			String[] hostnameURI = userInput.nextLine().split("/", 2);

			// validate hostname and get an InetAddress object
			InetAddress address = validateHostname(hostnameURI[0]);

			// get url path or root path by default
			String urlPath = (hostnameURI.length > 1) ? hostnameURI[1] : "";

			// make a list of request headers
			ArrayList<String> requestHeaders = makeRequestHeader(address.getHostName(), urlPath);

			// print all set headers
			System.out.println("Request Headers:");
			requestHeaders.forEach((header) -> System.out.print(header));

			// open a communication socket with host
			Socket socket;
			socket = new Socket(address, SERVER_PORT);

			// object DataOutputStream to handle communication with host
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());

			// loop to send all request headers to host
			for (String header : requestHeaders) {
				out.writeBytes(header);
			}

			// Get host response
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String str;
			while ((str=input.readLine()) != null) {
				System.out.println(str);
			}

			// close connections and helper objects
			// In some cases connection from server is still open until a timeout from server close it
			out.close();
			input.close();
			socket.close();
		}

		catch (Exception e) {
			System.err.println(e);
			System.exit(1);
		}
	}

}
