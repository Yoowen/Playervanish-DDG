package nl.goowen.playervanish.commands;

import nl.goowen.playervanish.Playervanish;
import nl.goowen.playervanish.methodes.VanishMethodes;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class VanishCommand implements CommandExecutor
{

    //Callback naar de main class
    private Playervanish plugin = Playervanish.getInstance();

    /**
     * @param sender the player who uses the command
     * @param command the command
     * @param label unused
     * @param args unused
     * @return returns true if the command is executed by a player, else it does nothing
     * a methetode in which we set up an command via where a player can become invisable or visable
     * depending on if they are already invisable or not.
     * If not we make then vissable or else we  make them invassable.
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        //checks if the sender is a player and if it has the right permission note for  the use of this command.
        if (sender instanceof Player)
        {
            Player player = (Player) sender;
            UUID playeruuid = player.getUniqueId();
            if (player.hasPermission("PlayerVanish.vanish.use"))
            {
                if (VanishMethodes.vanishedplayers.contains(playeruuid))
                {
                    //Maakt de speler weer zichtbaar voor alle spelers die online zijn, en stuurt de speler een berichtje!
                    for (Player onlineplayers : Bukkit.getOnlinePlayers())
                    {
                        onlineplayers.showPlayer(plugin, player);
                    }
                    player.sendMessage(plugin.getMessagePrefix() + ChatColor.WHITE + "Je bent weer zichtbaar geworden!");
                    VanishMethodes.vanishedplayers.remove(playeruuid);
                    VanishMethodes.vanishbar.removePlayer(player);
                }
                else
                {
                    //Maakt de speler ontzichtbaar voor alle spelers die online zijn, en stuurt de speler een berichtje.
                    for (Player onlineplayers : Bukkit.getOnlinePlayers())
                    {
                        onlineplayers.hidePlayer(plugin, player);
                    }
                    player.sendMessage(plugin.getMessagePrefix() + ChatColor.WHITE + "Je bent ontzichtbaar geworden!");
                    VanishMethodes.vanishedplayers.add(playeruuid);
                    VanishMethodes.vanishbar.addPlayer(player);
                }
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1F, 1F);

            }
            else
            {
                player.sendMessage(plugin.getMessagePrefix() + ChatColor.RED + "Je hebt geen permission om deze command te gebruiken!");
            }
            return true;
        }
        else
        {
            sender.sendMessage(plugin.getMessagePrefix() + ChatColor.RED + "Alleen een speler kan dit command gebruiken!");
        }
        return false;
    }
}
