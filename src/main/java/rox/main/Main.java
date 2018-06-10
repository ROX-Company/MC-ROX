package rox.main;

import rox.main.network.CommandLoader;
import rox.main.network.Network;
import rox.main.network.commands.InfoCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class Main extends JavaPlugin {

    private static Main instance;

    private Network network;

    private CommandLoader commandLoader;

    @Override
    public void onEnable(){
        instance = this;

        commandLoader = new CommandLoader();
        loadCommands();

        network = new Network(UUID.fromString("95e85d85-7e4d-4ec3-a2d1-4ea7c5c37296"), "1234");
        network.connect();
    }

    @Override
    public void onDisable(){

    }

    public static Main getInstance(){
        return instance;
    }

    public Network getNetwork(){
        return network;
    }

    public void loadCommands(){
        commandLoader.addCommand("info", new InfoCommand());
    }

    public CommandLoader getCommandLoader(){
        return commandLoader;
    }

}