package nl.goowen.playervanish;

import nl.goowen.playervanish.commands.VanishCommand;
import nl.goowen.playervanish.listeners.PlayerJoinListener;
import nl.goowen.playervanish.listeners.PlayerQuitListener;
import nl.goowen.playervanish.methodes.VanishMethodes;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Playervanish extends JavaPlugin
{
    private long loadMS;
    private static Playervanish playervanish;
    private final String prefix = ChatColor.DARK_AQUA + "[VANISH] ";

    @Override
    public void onEnable()
    {
        //Start een timer, die uitrekend in hoeveel milliseconden de plugin is opgestart
        this.loadMS = System.currentTimeMillis();
        System.out.println(prefix + "Gestart met het laden van Playervanish V" + this.getDescription().getVersion() + " by " + this.getDescription().getAuthors().toString().replace("[", "").replace("]", ""));
        //Hierna volgt de opstart logistiek van de plugin
        playervanish = this;
        registerCommands();
        registerEvents();
        //Wanneer deze message verschijnt is de plugin volledig geladen, zo niet? Then I screwed up...
        System.out.println(prefix + "Playervanish succesvol geladen! Dit prosess duurde " + (System.currentTimeMillis() - loadMS) + " ms!");
    }

    @Override
    public void onDisable()
    {
        System.out.println(prefix + "Plugin succesvol uitgezet, good bye!");
        new VanishMethodes().restartvanishcheck();
    }

    /**
     * Resisters all listener classes that referance events.
     */
    private void registerEvents()
    {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerJoinListener(), this);
        pm.registerEvents(new PlayerQuitListener(), this);
    }

    /**
     * Resiters all commandexecutor classes that referance commands.
     */
    private void registerCommands()
    {
        this.getCommand("vanish").setExecutor(new VanishCommand());
    }

    /**
     * Returns the give instance of this main class, of the plugin
     * @return
     */
    public static Playervanish getInstance()
    {
        return playervanish;
    }

    /**
     * Reutrns the prefix for this plugin definde in this class
     * @return
     */
    public String getMessagePrefix()
    {
        return prefix;
    }
}
