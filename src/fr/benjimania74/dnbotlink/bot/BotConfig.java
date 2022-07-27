package fr.benjimania74.dnbotlink.bot;

import be.alexandre01.dreamnetwork.client.console.Console;
import be.alexandre01.dreamnetwork.client.console.colors.Colors;
import fr.benjimania74.dnbotlink.utils.FilesManager;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.Base64;
import java.util.HashMap;

public class BotConfig {
    private static BotConfig instance;
    public static BotConfig getInstance() {return instance;}

    private JSONObject object;

    private String activity = "DreamNetwork Bot";
    public String getActivity() {return activity;}
    public void setActivity(String activity) {this.activity = activity;}

    private String status = "online";
    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}

    private String prefix = "d!";
    public String getPrefix() {return prefix;}
    public void setPrefix(String prefix) {this.prefix = prefix;}

    private HashMap<String, String> links = new HashMap<>();
    public HashMap<String, String> getLinks() {return links;}
    public void setLinks(HashMap<String, String> links) {this.links = links;}
    public void addLink(String serviceName, String discordChannel){links.put(serviceName, discordChannel); save();}

    public BotConfig(){
        try {
            object = (JSONObject) new JSONParser().parse(new String(Base64.getDecoder().decode(FilesManager.getInstance().read("config"))));
            setActivity((String) object.get("activity"));
            setStatus((String) object.get("status"));
            setPrefix((String) object.get("prefix"));
            setLinks((JSONObject) object.get("link"));

            if(BotMain.instance.jda != null){
                BotMain.instance.jda.getPresence().setStatus(OnlineStatus.fromKey(BotConfig.getInstance().getStatus()));
                BotMain.instance.jda.getPresence().setActivity(Activity.playing(BotConfig.getInstance().getActivity() + " | Type " + BotConfig.getInstance().getPrefix() + "help"));
            }
        }catch (Exception e){
            object = new JSONObject();
            save();
        }
        instance = this;
    }

    public void save(){
        try {
            object.put("activity", getActivity());
            object.put("status", getStatus());
            object.put("prefix", getPrefix());

            JSONObject list = new JSONObject();
            list.putAll(getLinks());

            object.put("link", list);

            FilesManager.getInstance().write("config", Base64.getEncoder().encodeToString(object.toJSONString().getBytes()));

            Console.print(Colors.GREEN_BACKGROUND + "The Configuration File has been saved");
            reload();
        }catch (Exception e){
            Console.print(Colors.RED_BACKGROUND + "Can't save the Configuration File");
        }
    }

    public void reload(){new BotConfig();}
}
