package org.example;

import java.net.*;
import java.io.*;
import java.util.Scanner;

// ClientHandler class
class ClientHandler extends Thread {

    final DataInputStream in;
    final DataOutputStream out;
    final Socket s;


    // Constructor
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.s = s;
        this.in = dis;
        this.out = dos;
    }

    private void send(String msg) {
        try {
            out.writeUTF(msg);
            out.flush();
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

    public void close() {
        try {
            this.in.close();
            this.out.close();
            this.s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String received;
        String toreturn;
        Scanner scn = new Scanner(System.in);
        while (true) {
            try {
                String clientSendMessage = this.receive();
                // Ask user what he wants
                this.send("SERVER: let chat begin");
//                System.out.println("CLIENT: " + s.getInetAddress() + ">>>>" + clientSendMessage);
//                this.send(scn.nextLine());
                if (clientSendMessage.equals("Exit")) {
                    System.out.println("CLIENT: " + this.s + " sends exit...");
                    System.out.println("Closing this connection.");
                    this.s.close();
                    System.out.println("Connection closed");
                    break;
                }

            } catch (IOException e) {
                e.printStackTrace();
                this.close();

            }
        }
    }
}

class ClientHandler2 extends Thread {

    final DataInputStream in;
    final DataOutputStream out;
    final Socket s;


    // Constructor
    public ClientHandler2(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.s = s;
        this.in = dis;
        this.out = dos;
    }

    private void send(String msg) {
        try {
            out.writeUTF(msg);
            out.flush();
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

    public void close() {
        try {
            this.in.close();
            this.out.close();
            this.s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String received;
        String toreturn;
        Scanner scn = new Scanner(System.in);
        while (true) {

            String clientSendMessage = this.receive();

            this.send("SERVER: let chat begin");
            System.out.println("CLIENT: " + s.getInetAddress() + ">>>>" + clientSendMessage);


        }
    }
}

public class SocketServer {

    public static final int PORT = 10745;
    ServerSocket serverSocket;
    boolean connected = false;
    DataInputStream in;
    DataOutputStream out;

    InetAddress ip = null;

    String request[];


    public SocketServer() {
        try {
            ip = InetAddress.getByName("localhost");
            System.out.println("SERVER IP: " + ip);
            serverSocket = new ServerSocket(PORT);
            this.connected = true;

            this.listening();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        SocketServer demo = new SocketServer();

    }

    public void listening() {
        System.out.println("Server start listening: ...");
        try {
            while (true) {

                Socket socket = serverSocket.accept();
                System.out.println("New client connected");
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
                Thread t = new ClientHandler(socket, in, out);
//                Thread t2 = new ClientHandler2(socket, in, out);
                t.start();
//                t2.start();


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
