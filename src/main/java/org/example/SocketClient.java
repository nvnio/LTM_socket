/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import java.net.*;
import java.io.*;
import java.util.Scanner;

/**
 *
 * @author 503
 */

public class SocketClient {
    Socket socket = null;

    DataInputStream in;
    DataOutputStream out;
    ObjectInputStream inObj;
    ObjectOutputStream outObj;
    String ip = "localhost";
    int port = 10745;

    private void send(String msg) {
        try {
            out.writeUTF(msg);
//            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String receive() {
        try {
            String received = in.readUTF();
            return received;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public SocketClient() {
        try {
            Scanner scn = new Scanner(System.in);
            socket = new Socket(ip, port);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            inObj = new ObjectInputStream(socket.getInputStream());
            outObj = new ObjectOutputStream(socket.getOutputStream());
            
            while (true) {
//                Chờ server gửi về khi nào gửi thì chayyj tiếp
//                System.out.println("SERVER: " + ">>>>" + this.receive());
                System.out.println("CLIENT: " + ">>>>");
                this.send(scn.nextLine());
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public static void main(String[] args) {


        SocketClient demo = new SocketClient();


    }
}

