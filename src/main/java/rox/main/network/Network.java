package rox.main.network;

import rox.main.Main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.UUID;

public class Network {

    private String hostname = "localhost", password;
    private int port = 8982;
    private UUID uuid;

    private Socket socket;

    private PrintWriter writer;

    private BufferedReader reader;

    private ServerInputHandler serverInputHandler;

    private Thread serverInputHandlerThread;


    public Network(UUID uuid, String password){
        this.uuid = uuid;
        this.password = password;
    }

    public Network(UUID uuid, String password, String hostname, int port){
        this.hostname = hostname;
        this.port = port;
        this.uuid = uuid;
        this.password = password;
    }


    public void connect(){
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(hostname, port));
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            writer.println(uuid.toString() + "§" + password);

            switch (reader.readLine()){
                case "§UUID_NOT_FOUND":
                    Main.getInstance().getLogger().warning("UUID (" + uuid.toString() + ") not found. Plugin stopping...");
                    Main.getInstance().getPluginLoader().disablePlugin(Main.getInstance());
                    break;
                case "§MC_SERVER_CONNECTED":
                    (serverInputHandlerThread = new Thread(() -> (serverInputHandler = new ServerInputHandler()).run())).start();
                        Main.getInstance().getLogger().info("Connected to ROX Server.");
                    break;
                case "§ACCOUNT_NOT_FOUND":
                    Main.getInstance().getLogger().warning("UUID or password is wrong. Plugin stopping...");
                    Main.getInstance().getPluginLoader().disablePlugin(Main.getInstance());
                    break;
            }


        } catch (Exception e) {
            if(e instanceof ConnectException){
                Main.getInstance().getLogger().warning("ROX Server is not enabled. Plugin stopping... ");
                Main.getInstance().getPluginLoader().disablePlugin(Main.getInstance());
            }else{
                Main.getInstance().getLogger().warning("ROX Server is not enabled. Plugin stopping... ");
                Main.getInstance().getPluginLoader().disablePlugin(Main.getInstance());
            }
        }
    }

    public void disconnect(){
        if(socket != null) {
            try { socket.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }

    public Socket getSocket(){
        return socket;
    }

    public PrintWriter getWriter(){
        return writer;
    }

    public BufferedReader getReader(){
        return reader;
    }

    public void writeMessage(String message){
        writer.println(message);
    }
}
