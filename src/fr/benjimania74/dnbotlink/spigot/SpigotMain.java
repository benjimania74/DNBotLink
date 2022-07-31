package fr.benjimania74.dnbotlink.spigot;

import be.alexandre01.dnplugin.plugins.spigot.api.DNSpigotAPI;
import be.alexandre01.dnplugin.utils.messages.Message;
import fr.benjimania74.dnbotlink.spigot.listeners.Events;
import org.bukkit.plugin.java.JavaPlugin;

public class SpigotMain extends JavaPlugin {
    public static SpigotMain instance;
    public static DNSpigotAPI dnSpigotAPI;

    @Override
    public void onEnable() {
        instance = this;
        System.out.println(getName() + " is started");
        getServer().getPluginManager().registerEvents(new Events(), this);
    }

    public void sendMsg(String msg, String playerName){
        Message m = new Message();
        m.setHeader("DNBotLink-Chat");
        m.set("Chat", dnSpigotAPI.getServerName() + " [" + playerName + "] " + msg);
        dnSpigotAPI.getClientHandler().writeAndFlush(m);
    }
}
