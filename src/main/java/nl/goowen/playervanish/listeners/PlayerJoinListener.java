package nl.goowen.playervanish.listeners;

import nl.goowen.playervanish.Playervanish;
import nl.goowen.playervanish.methodes.VanishMethodes;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class PlayerJoinListener implements Listener
{
    //Callback naar de main class
    private Playervanish plugin = Playervanish.getInstance();

    /**
     * A eventlistener for the join action of a player, here we do the following
     * make the currently vanished players invisable for the joined player.
     * @param event the current event
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();
        for (UUID vanished : VanishMethodes.vanishedplayers)
        {
            player.hidePlayer(plugin, Bukkit.getPlayer(vanished));
        }
    }
}
