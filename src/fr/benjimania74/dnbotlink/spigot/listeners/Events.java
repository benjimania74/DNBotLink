package fr.benjimania74.dnbotlink.spigot.listeners;

import be.alexandre01.dnplugin.plugins.spigot.api.DNSpigotAPI;
import be.alexandre01.dnplugin.plugins.spigot.api.events.server.ServerAttachedEvent;
import fr.benjimania74.dnbotlink.spigot.CustomSpigotResponse;
import fr.benjimania74.dnbotlink.spigot.SpigotMain;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Events implements Listener {
    @EventHandler
    public void onInitialize(ServerAttachedEvent e){
        SpigotMain.dnSpigotAPI = DNSpigotAPI.getInstance();
        SpigotMain.dnSpigotAPI.getResponseManager().addResponse(new CustomSpigotResponse());
    }

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent e){SpigotMain.instance.sendMsg(SpigotMain.dnSpigotAPI.getServerName(), e.getPlayer().getName());}
}
