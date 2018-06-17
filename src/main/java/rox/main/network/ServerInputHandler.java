package rox.main.network;

import rox.main.Main;

public class ServerInputHandler extends Thread {

    /*
    *   Command Construct:
    *
    *   <command>§<args[ ]>
    *
    *
    *
     */


    public void run(){
        String input;
        try{
            while((input = Main.getInstance().getNetwork().getReader().readLine()) != null){
                String[] strings = input.split("§"), args = strings[1].split(" ");

                if(strings[0].startsWith("§")){
                    switch (strings[0]){
                        case "§INVALID_COMMAND_STRUCTURE":
                            Main.getInstance().getLogger().warning("Error sending informations: " +
                                    new IllegalArgumentException("Server", new Throwable().fillInStackTrace()).getMessage());
                        break;
                        default: Main.getInstance().getLogger().warning(input); break;
                    }
                    return;
                }

                Main.getInstance().getCommandLoader().getCommand(strings[0]).command(strings[0], args);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

}
