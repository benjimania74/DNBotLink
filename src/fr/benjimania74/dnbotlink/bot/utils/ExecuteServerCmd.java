package fr.benjimania74.dnbotlink.bot.utils;

import be.alexandre01.dreamnetwork.api.connection.request.RequestType;
import be.alexandre01.dreamnetwork.client.console.Console;
import fr.benjimania74.dnbotlink.Main;

import java.util.logging.Level;

public class ExecuteServerCmd {
    public void execute(String cmd, String serviceName){
        Main.clientAPI.getContainer().getJVMExecutorsServers().get(serviceName).getService(0).getClient().getRequestManager().sendRequest(RequestType.SPIGOT_EXECUTE_COMMAND, cmd);
        Console.print(cmd, Level.CONFIG);
    }
}
