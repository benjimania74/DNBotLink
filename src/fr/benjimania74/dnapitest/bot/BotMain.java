package fr.benjimania74.dnapitest.bot;

import be.alexandre01.dreamnetwork.client.console.Console;
import be.alexandre01.dreamnetwork.client.console.colors.Colors;
import fr.benjimania74.dnapitest.utils.FilesManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

public class BotMain {
    public static BotMain instance;
    public static JDA jda;

    public boolean create(){
        try{
            new BotConfig();
            String token = FilesManager.getInstance().read("token");
            JDABuilder builder = JDABuilder.createDefault(token);
            builder.setStatus(OnlineStatus.fromKey(BotConfig.getInstance().getStatus()));
            builder.setActivity(Activity.playing(BotConfig.getInstance().getActivity()));
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
