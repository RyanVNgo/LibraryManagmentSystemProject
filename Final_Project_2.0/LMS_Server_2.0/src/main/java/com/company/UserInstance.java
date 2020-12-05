package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class UserInstance {

    private static Socket thisSocket;
    private static DataInputStream in;
    private static DataOutputStream out;

    // Establish socket
    public UserInstance(Socket socket) throws Exception{
        thisSocket = socket;
        ValidateLoginController();
    }

    // Controls login state
    private static void ValidateLoginController() throws Exception{
        int opt;
        opt = ValidateLogin();
        switch (opt) {
            case(0):
                System.out.println(" - - - Success");
                ActionCenter();
                break;
            case(1):
                System.out.println(" - - - Failed");
                ValidateLoginController();
                break;
            case(2):
                System.out.println(" - - - Closing Connection");
                thisSocket.close();
                break;
        }
    }

    // Compare login info with Database info
    private static int ValidateLogin() throws Exception {
        in = new DataInputStream(thisSocket.getInputStream());
        out = new DataOutputStream(thisSocket.getOutputStream());
        System.out.println(" - - Client Attempting Login");
        String checkType, checkName, checkPass;
        String typeinput, nameInput, passInput;
        boolean valid = false;
        int opt = 1;

        DatabaseHandler dbHandler = new DatabaseHandler();
        ArrayList<User> userList = dbHandler.ObtainAllUsersData();

        typeinput = in.readUTF();
        nameInput = in.readUTF();
        passInput = in.readUTF();

        for (User user : userList) {
            checkType = user.getUserType();
            checkName = user.getUsername();
            checkPass = user.getPassword();
            if (checkType.equals(typeinput) && checkName.equals(nameInput) && checkPass.equals(passInput)) {
                valid = true;
                opt = 0;
            }
        }
        if (typeinput.equals("q")) { opt = 2; }
        out.writeBoolean(valid);
        return opt;
    }

    // Main action controller
    public static void ActionCenter() throws Exception{
        in = new DataInputStream(thisSocket.getInputStream());
        System.out.println(" - - - Client Reached ActionCenter");
        System.out.println(" - - - - Closing Connection");

    }
}