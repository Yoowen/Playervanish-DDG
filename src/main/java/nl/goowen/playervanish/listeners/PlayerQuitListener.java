package nl.goowen.playervanish.listeners;

import nl.goowen.playervanish.methodes.VanishMethodes;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class PlayerQuitListener implements Listener
{
    /**
     * Event listener for the quit action of a player, here we check the following
     * if a player is currently vanished.
     * @param event the current event
     */

    @EventHandler
    public void PlayerLeaveEvent (PlayerQuitEvent event)
    {
        UUID playeruuid = event.getPlayer().getUniqueId();
        Player player = event.getPlayer();
        if (VanishMethodes.vanishedplayers.contains(playeruuid))
        {
             VanishMethodes.vanishedplayers.remove(playeruuid);
             VanishMethodes.vanishbar.removePlayer(player);
        }
    }
}
