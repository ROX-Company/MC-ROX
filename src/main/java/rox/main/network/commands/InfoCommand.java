package rox.main.network.commands;

import rox.main.Main;
import rox.main.network.CommandExecutor;
import org.bukkit.Bukkit;

public class InfoCommand implements CommandExecutor {

    /*
     *   Args:
     *   [0]
     *   playerSize
     *   maxPlayers
     *   whitelistSize
     *   motd
     *
     *
     *
     * info§<command>
     *
     *
     *
     *
     *
     */

    @Override
    public void command(String command, String[] args) {

        switch (args[0]) {
            case "playerSize":
                Main.getInstance().getNetwork().writeMessage("§info§playerSize§" + Bukkit.getOnlinePlayers().size());
                break;
            case "maxPlayers":
                Main.getInstance().getNetwork().writeMessage("§info§maxPlayers§" + Bukkit.getMaxPlayers());
                break;
            case "whitelistSize":
                Main.getInstance().getNetwork().writeMessage("§info§whitelistSize§" + Bukkit.getWhitelistedPlayers().size());
                break;
            case "motd":
                Main.getInstance().getNetwork().writeMessage("§info§motd§" + Bukkit.getMotd());
                break;
        }
    }
}
