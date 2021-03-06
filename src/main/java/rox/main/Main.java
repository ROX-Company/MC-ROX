package rox.main;

import rox.main.debug.LocalNetworkTest;
import rox.main.network.CommandLoader;
import rox.main.network.Network;
import rox.main.network.commands.InfoCommand;
import org.bukkit.plugin.java.JavaPlugin;
import rox.main.network.commands.KickCommand;

import java.util.UUID;

public class Main extends JavaPlugin {

    private static Main instance;

    private Network network;

    private CommandLoader commandLoader;

    private boolean debug = true;

    private LocalNetworkTest localNetworkTest;

    @Override
    public void onEnable(){
        instance = this;

        if(debug) localNetworkTest = new LocalNetworkTest(8982);
        commandLoader = new CommandLoader();
        loadCommands();

        network = new Network(UUID.fromString("95e85d85-7e4d-4ec3-a2d1-4ea7c5c37296"), "1234");
        network.connect();

        localNetworkTest.sendCommand("info", "");


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
        commandLoader.addCommand("kick", new KickCommand());
    }

    public CommandLoader getCommandLoader(){
        return commandLoader;
    }

}