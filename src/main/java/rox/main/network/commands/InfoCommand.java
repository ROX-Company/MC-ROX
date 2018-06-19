package rox.main.network.commands;

import rox.main.Main;
import rox.main.network.CommandExecutor;
import org.bukkit.Bukkit;

public class InfoCommand implements CommandExecutor {

    /*
     *   Args:
     *   [0]
     *   playerSize Y
     *   maxPlayers Y
     *   whitelistSize Y
     *   motd Y
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
            case "allowEnd":
                Main.getInstance().getNetwork().writeMessage("§info§allowEnd§" + Bukkit.getAllowEnd());
                break;
            case "allowNether":
                Main.getInstance().getNetwork().writeMessage("§info§allowNether§" + Bukkit.getAllowNether());
                break;
            case "allowFlight":
                Main.getInstance().getNetwork().writeMessage("§info§allowFlight§" + Bukkit.getAllowFlight());
                break;
            case "ip":
                Main.getInstance().getNetwork().writeMessage("§info§ip§" + Bukkit.getIp());
                break;
            case "port":
                Main.getInstance().getNetwork().writeMessage("§info§port§" + Bukkit.getPort());
                break;
            case "serverName":
                Main.getInstance().getNetwork().writeMessage("§info§serverName§" + Bukkit.getServerName());
                break;
            case "serverId":
                Main.getInstance().getNetwork().writeMessage("§info§serverId§" + Bukkit.getServerId());
                break;
            case "shutdownMessage":
                Main.getInstance().getNetwork().writeMessage("§info§shutdownMessage§" + Bukkit.getShutdownMessage());
                break;
        }
    }
}
