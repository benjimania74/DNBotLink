package fr.benjimania74.dnapitest.bot;

import be.alexandre01.dreamnetwork.client.console.Console;
import be.alexandre01.dreamnetwork.client.console.colors.Colors;
import fr.benjimania74.dnapitest.utils.FilesManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class BotMain {
    public static BotMain instance;
    public static JDA jda;

    public boolean create(){
        try{
            String token = FilesManager.getInstance().read("token");
            JDABuilder builder = JDABuilder.createDefault(token);
            jda = builder.build();
            instance = this;
            Console.print(Colors.GREEN_BACKGROUND + "The Bot is started");
            return true;
        }catch (Exception e){
            Console.print(Colors.RED_BACKGROUND + "The Bot can't be started");
            return false;
        }
    }
}
