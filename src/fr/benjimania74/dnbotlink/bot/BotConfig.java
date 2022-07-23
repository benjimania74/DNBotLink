package fr.benjimania74.dnbotlink.bot;

import be.alexandre01.dreamnetwork.client.console.Console;
import be.alexandre01.dreamnetwork.client.console.colors.Colors;
import fr.benjimania74.dnbotlink.utils.FilesManager;
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

            JSONObject connectionsList = (JSONObject) object.get("link");
            HashMap<String, String> list = new HashMap<>();
            connectionsList.forEach((key, value) -> {
                list.put((String) key, (String) value);
            });

            setLinks(list);
        }catch (Exception e){
            object = new JSONObject();
            save();
            new BotConfig();
        }

        instance = this;
    }

    public void save(){
        try {
            object.put("activity", getActivity());
            object.put("status", getStatus());
            object.put("prefix", getPrefix());

            JSONObject list = new JSONObject();
            getLinks().forEach((key, value) -> {
                list.put(key, value);
            });

            object.put("link", list);

            FilesManager.getInstance().write("config", Base64.getEncoder().encodeToString(object.toJSONString().getBytes()));

            Console.print(Colors.GREEN + "The Configuration File has been saved");
            new BotConfig();
        }catch (Exception e){
            Console.print(Colors.RED + "Can't save the Configuration File");
        }
    }

    public void reload(){new BotConfig();}
}
