package fr.benjimania74.dnbotlink.bot.utils;

import be.alexandre01.dreamnetwork.api.connection.request.RequestType;
import fr.benjimania74.dnbotlink.Main;

public class ExecuteServerCmd {
    public void execute(String cmd, String serviceName){
        Main.clientAPI.getContainer().getJVMExecutorsServers().forEach((s, executor) -> {
            if(s.equals(serviceName)){
                executor.getService(0).getClient().getRequestManager().sendRequest(RequestType.SPIGOT_EXECUTE_COMMAND, cmd);
            }
        });
    }
}
