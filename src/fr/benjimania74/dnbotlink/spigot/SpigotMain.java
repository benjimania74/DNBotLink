package fr.benjimania74.dnbotlink.spigot;

import be.alexandre01.dnplugin.plugins.spigot.api.DNSpigotAPI;
import fr.benjimania74.dnbotlink.spigot.listeners.Events;
import org.bukkit.plugin.java.JavaPlugin;

public class SpigotMain extends JavaPlugin {
    public static DNSpigotAPI dnSpigotAPI;

    @Override
    public void onEnable() {
        System.out.println(getName() + " is started");
        getServer().getPluginManager().registerEvents(new Events(), this);
    }
}
