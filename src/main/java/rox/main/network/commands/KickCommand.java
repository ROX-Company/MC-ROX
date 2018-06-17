package rox.main.network.commands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import rox.main.network.CommandExecutor;

import java.util.UUID;

public class KickCommand implements CommandExecutor {

    /*

    kick§<uuid/name>

     */

    @Override
    public void command(String command, String[] args) {

        Player player = Bukkit.getPlayer(UUID.fromString(args[0]));

    }
}
