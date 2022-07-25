package fr.benjimania74.dnbotlink.spigot.listeners;

import be.alexandre01.dnplugin.plugins.spigot.api.DNSpigotAPI;
import be.alexandre01.dnplugin.plugins.spigot.api.events.server.ServerAttachedEvent;
import fr.benjimania74.dnbotlink.spigot.SpigotMain;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class InitializeEvent implements Listener {
    @EventHandler
    public void onInitialize(ServerAttachedEvent e){
        SpigotMain.dnSpigotAPI = DNSpigotAPI.getInstance();
    }
}
