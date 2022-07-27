package fr.benjimania74.dnbotlink.cmd;

import be.alexandre01.dreamnetwork.api.commands.Command;
import be.alexandre01.dreamnetwork.client.console.Console;
import be.alexandre01.dreamnetwork.client.console.colors.Colors;
import fr.benjimania74.dnbotlink.Main;
import fr.benjimania74.dnbotlink.utils.FilesManager;
import fr.benjimania74.dnbotlink.utils.Services;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.Base64;

public class AutoStarterCmd extends Command {
    public AutoStarterCmd(String name) {
        super(name);

        addSubCommand("add", args -> {
            JSONObject jobj;
            JSONArray object;
            if(args.length < 2){Console.print(Colors.RED + name + " add <service> [<server|proxy>]");return true;}

            try {
                jobj = (JSONObject) new JSONParser().parse(new String(Base64.getDecoder().decode(FilesManager.getInstance().read("autostartservice"))));
                object = (JSONArray) jobj.get("services");
            }catch (Exception e){
                jobj = new JSONObject();
                object = new JSONArray();
                jobj.put("services", object);
                save(jobj.toJSONString());
            }

            if(Services.getType(args[1]) == null){Console.print(Colors.RED + args[1] + " is not a Service");return true;}

            if(args.length == 2){
                if(Services.isBoth(args[1])){Console.print(Colors.RED + args[1] + " is a Server and a Proxy");return true;}
                object.add(args[1]);
                jobj.put("services", object);
                save(jobj.toJSONString());
                Console.print(Colors.GREEN + args[1] + " will now auto start at the start of the Network");
                return true;
            }

            if(args[2].equalsIgnoreCase("server")){
                if(!Main.clientAPI.getContainer().getJVMExecutorsServers().containsKey(args[1])) {Console.print(Colors.RED + args[1] + " is not a Server");return true;}
                object.add(args[1] + "<&>server");
                jobj.put("services", object);
                save(jobj.toJSONString());
                Console.print(Colors.GREEN + args[1] + "(server) will now auto start at the start of th Network");
                return true;
            }
            if(args[2].equalsIgnoreCase("proxy")){
                if(!Main.clientAPI.getContainer().getJVMExecutorsProxy().containsKey(args[1])) {Console.print(Colors.RED + args[1] + " is not a Proxy");return true;}
                object.add(args[1] + "<&>proxy");
                jobj.put("services", object);
                save(jobj.toJSONString());
                Console.print(Colors.GREEN + args[1] + "(proxy) will now auto start at the start of th Network");
                return true;
            }

            Console.print(Colors.RED + args[1] + " is not a Service");
            return true;
        });

        addSubCommand("remove", args -> {
            JSONObject jobj;
            JSONArray object;
            if(args.length < 2){Console.print(Colors.RED + name + " remove <service> [<server|proxy>]");return true;}

            try {
                jobj = (JSONObject) new JSONParser().parse(new String(Base64.getDecoder().decode(FilesManager.getInstance().read("autostartservice"))));
                object = (JSONArray) jobj.get("services");
            }catch (Exception e){
                jobj = new JSONObject();
                object = new JSONArray();
                jobj.put("services", object);
                save(jobj.toJSONString());
            }

            if(Services.isBoth(args[1])){Console.print(Colors.RED + args[1] + " is a Server and a Proxy");return true;}

            if(args.length == 3){
                if(args[2].equalsIgnoreCase("server")){
                    if(object.contains(args[1] + "<&>server")){
                        object.remove(args[1] + "<&>server");
                        jobj.put("services", object);
                        save(jobj.toJSONString());
                        Console.print(Colors.GREEN + args[1] + " has been removed of the auto-starter");
                        return true;
                    }
                    Console.print(Colors.RED + args[1] + " (server) is not auto-started service");
                    return true;
                }
                if(args[2].equalsIgnoreCase("proxy")){
                    if(object.contains(args[1] + "<&>proxy")){
                        object.remove(args[1] + "<&>proxy");
                        jobj.put("services", object);
                        save(jobj.toJSONString());
                        Console.print(Colors.GREEN + args[1] + " (proxy) has been removed of the auto-starter");
                        return true;
                    }
                    Console.print(Colors.RED + args[1] + " is not auto-started service");
                    return true;
                }
                Console.print(Colors.RED + name + " remove <service> [<server|proxy>]");
                return true;
            }

            if(object.contains(args[1])){
                object.remove(args[1]);
                jobj.put("services", object);
                save(jobj.toJSONString());
                Console.print(Colors.GREEN + args[1] + " has been removed of the auto-starter");
                return true;
            }

            Console.print(Colors.RED + args[1] + " is not auto-started service");
            return true;
        });

        getHelpBuilder().setTitleUsage("Auto-Starter command usage")
                .setCmdUsage("Add a service to the auto-start", "add", "<service> [<server|proxy>]")
                .setCmdUsage("Remove a service to the auto-start", "remove", "<service> [<server|proxy>]");
    }

    private void save(String toWrite){
        try {
            FilesManager.getInstance().write("autostartservice", Base64.getEncoder().encodeToString(toWrite.getBytes()));
            Console.print(Colors.GREEN_BACKGROUND + "The file 'autostartservice' has been saved");
        }catch (Exception e){
            Console.print(Colors.RED_BACKGROUND + "The file can't be saved");
        }
    }
}
