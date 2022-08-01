package fr.benjimania74.dnbotlink.utils;

import be.alexandre01.dreamnetwork.api.service.IJVMExecutor;
import be.alexandre01.dreamnetwork.client.console.Console;
import be.alexandre01.dreamnetwork.client.console.colors.Colors;
import fr.benjimania74.dnbotlink.Main;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class ServiceAutoStarter {
    public ServiceAutoStarter(){
        JSONObject jobj;
        JSONArray object;
        try {
            jobj = (JSONObject) new JSONParser().parse(new String(Base64.getDecoder().decode(FilesManager.getInstance().read("autostartservice"))));
            object = (JSONArray) jobj.get("services");
        }catch (Exception e){
            jobj = new JSONObject();
            jobj.put("services", new JSONArray());
            save(jobj.toJSONString());
            Console.print(Colors.YELLOW + "No Service to Auto-Start");
            return;
        }

        List<String> excuted = new ArrayList<>();
        Console.print(Colors.YELLOW + "Auto-Starting of Proxy");
        for(IJVMExecutor executor : Main.clientAPI.getContainer().getJVMExecutorsProxy().values()){
            if(Services.isBoth(executor.getName())){
                if(object.contains(executor.getName() + "<&>proxy")){
                    executor.startServer();
                    Console.print(Colors.GREEN + executor.getName() + " auto-starting");
                    excuted.add(executor.getName()+"<&>proxy");
                }
            }else if(object.contains(executor.getName())){
                executor.startServer();
                Console.print(Colors.GREEN + executor.getName() + " auto-starting");
                excuted.add(executor.getName());
            }
        }

        excuted.forEach(object::remove);
        if(object.isEmpty()){return;}

        int i = 0;
        while (!Services.isProxyLaunched()){System.out.println(i++);}

        Console.print(Colors.YELLOW + "Auto-Starting of Servers");
        for(IJVMExecutor executor : Main.clientAPI.getContainer().getJVMExecutorsServers().values()){
            if(Services.isBoth(executor.getName())){
                if(object.contains(executor.getName() + "<&>server")){
                    executor.startServer();
                    Console.print(Colors.GREEN + executor.getName() + " auto-starting");
                }
            }else if(object.contains(executor.getName())){
                executor.startServer();
                Console.print(Colors.GREEN + executor.getName() + " auto-starting");
            }
        }
        Console.print(Colors.GREEN + "Auto-Starting finished");
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
