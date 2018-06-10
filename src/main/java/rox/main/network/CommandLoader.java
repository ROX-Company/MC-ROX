package rox.main.network;

import java.util.concurrent.ConcurrentHashMap;

public class CommandLoader {

    private ConcurrentHashMap<String, CommandExecutor> commands = new ConcurrentHashMap<>();

    public void addCommand(String command, CommandExecutor executor){
        commands.put(command, executor);
    }

    public CommandExecutor getCommand(String command){
        return commands.get(command);
    }

}
