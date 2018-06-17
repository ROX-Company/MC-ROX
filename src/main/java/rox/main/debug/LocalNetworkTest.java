package rox.main.debug;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

public class LocalNetworkTest {

    private ServerSocket serverSocket;

    private Object[] client;

    public LocalNetworkTest(int port){
        try {
            serverSocket = new ServerSocket(port);
            new Thread(this::AcceptThread).start();
            write("Created local server socket. Port: " + port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendCommand(String command, String whatIWant){
        ((PrintWriter) client[2]).println(command + "§" + whatIWant);
    }

    private void AcceptThread() {
        Socket socket;
        try{
            while((socket = serverSocket.accept()) != null){
                write("Connection -> " + socket.getInetAddress());
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
                write("Connection testing.");
                String[] input = reader.readLine().split("§");
                Object[] objects = new Object[8];
                objects[0] = socket;
                objects[1] = reader;
                objects[2] = writer;
                objects[3] = UUID.fromString(input[0]);
                new Thread(this::InputThread).start();
                client = objects;
            }
        }catch (Exception e){
         e.printStackTrace();
        }
    }

    private void InputThread(){
        String input;
        try{
            while((input = ((BufferedReader)client[1]).readLine()) != null){
                write("Input -> " + input);
                String[] args = input.split("§");
                if(args.length > 2){
                    write("Too much arguments. -> String: " + input);
                }
                write("[Answer] Command: " + args[0] + " What i want: " + args[1] + " The value: " + args[2]);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void write(String text){
        System.out.println("[LocalNetworkTest] " + text);
    }

}
