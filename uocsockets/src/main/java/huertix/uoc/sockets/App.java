package huertix.uoc.sockets;

import huertix.uoc.sockets.mod.ClientTcpMod;
import huertix.uoc.sockets.mod.ClientUdpMod;
import huertix.uoc.sockets.mod.ServerUdpMod;

import java.util.Scanner;


public class App {

    UserOptionEnum userOptionEnum;

    public App() {

    }

    private void printWelcomeMessage() {
        System.out.println("Welcome to the UOC sockets App.");

        System.out.println("1: TCP client app");
        System.out.println("2: UDP client app");
        System.out.println("3: UDP server app");
        System.out.print("Please select: ");
    }

    private int getUserOption() {
        Scanner userInput = new Scanner(System.in);
        int userOption = userInput.nextInt();

        if (userOption < UserOptionEnum.TCP_CLIENT.getValue() ||
                userOption > UserOptionEnum.UDP_SERVER.getValue()) {
            System.err.println("Please provide a valid option");
            System.exit(1);
        }

        return userOption;
    }

    private void runUserOption(int userOption) {

        UserOptionEnum optionEnum = UserOptionEnum.valueOf(userOption);

        switch (optionEnum) {
            case TCP_CLIENT:
                new ClientTcpMod();
                break;
            case UDP_CLIENT:
                new ClientUdpMod();
                break;
            case UDP_SERVER:
                new ServerUdpMod();
                break;
            default: break;
        }
    }

    private void messageEnd() {
        System.out.println("End of Program");
    }

    public static void main(String argv[]) {

        App app = new App();
        app.printWelcomeMessage();
        int userOption = app.getUserOption();
        app.runUserOption(userOption);
        app.messageEnd();









    }
}
