package huertix.uoc.sockets;

import huertix.uoc.sockets.mod.ClientTcpMod;
import huertix.uoc.sockets.mod.ClientUdpMod;
import huertix.uoc.sockets.mod.ServerUdpMod;

import java.util.Scanner;


public class App {

    UserOptionEnum userOptionEnum;

    public App() {}

    private void printWelcomeMessage() {
        System.out.println("Welcome to the UOC sockets App.");
    }

    private void checkAppMode(int appMode) {
        if (appMode < UserOptionEnum.TCP_CLIENT.getValue() ||
                appMode > UserOptionEnum.UDP_SERVER.getValue()) {
            System.err.println("Please provide a valid option");
            System.exit(1);
        }
    }

    private int getUserOption() {
        System.out.println("1: TCP client app");
        System.out.println("2: UDP client app");
        System.out.println("3: UDP server app");
        System.out.print("Please select: ");

        Scanner userInput = new Scanner(System.in);
        int userOption = userInput.nextInt();

        checkAppMode(userOption);
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

        // To get the app mode, we check for an environment variable, if does not exit,
        // we ask to the user
        int userOption = System.getenv("APP_MODE") == null ?
                app.getUserOption() : Integer.valueOf(System.getenv("APP_MODE"));


        System.out.println(String.format("APP MODE: %d ", userOption));

        app.runUserOption(userOption);
        app.messageEnd();

    }
}
