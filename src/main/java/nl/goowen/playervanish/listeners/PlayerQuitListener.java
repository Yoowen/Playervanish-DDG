package nl.goowen.playervanish.listeners;

import nl.goowen.playervanish.methodes.VanishMethodes;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener
{
    /**
     * Event listener for the quit action of a player, here we check the following
     * if a player is currently vanished.
     * @param event the current event
     */
    public void PlayerLeaveEvent (PlayerQuitEvent event)
    {
        Player player = event.getPlayer();
        if (VanishMethodes.vanishedplayers.contains(player))
        {
             VanishMethodes.vanishedplayers.remove(player);
             VanishMethodes.vanishbar.removePlayer(player);
        }
    }
}
