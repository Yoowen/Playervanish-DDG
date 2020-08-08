package nl.goowen.playervanish.methodes;

import nl.goowen.playervanish.Playervanish;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class VanishMethodes
{
    //Lijst met spelers die op dit moment onzichtbaar zijn.
    public final static ArrayList<UUID> vanishedplayers = new ArrayList<>();
    public final static BossBar vanishbar = Bukkit.getServer().createBossBar(ChatColor.WHITE + "" + ChatColor.BOLD + "Vanished", BarColor.BLUE, BarStyle.SOLID);

    //Callback naar de main class
    private Playervanish plugin = Playervanish.getInstance();

    /**
     * Reaveal all online vanished players before a restart or reload, and removed them from the arraylist.
     */
    public void restartvanishcheck()
    {
        for (Player onlineplayers : Bukkit.getOnlinePlayers())
        {
            if (VanishMethodes.vanishedplayers.contains(onlineplayers.getUniqueId()))
            {
                VanishMethodes.vanishedplayers.remove(onlineplayers.getUniqueId());
                vanishbar.removePlayer(onlineplayers);
                onlineplayers.playSound(onlineplayers.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1F, 1F);
                onlineplayers.sendMessage(plugin.getMessagePrefix() + ChatColor.WHITE + "Een reload of restart zorgt er voor dat je weer zichtbaar bent geworden!");
                for (Player others : Bukkit.getOnlinePlayers())
                {
                    others.showPlayer(plugin, onlineplayers);
                }
            }
        }
    }
}
