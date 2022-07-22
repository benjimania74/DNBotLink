package fr.benjimania74.dnbotlink.bot;

import be.alexandre01.dreamnetwork.api.connection.core.communication.IClient;
import be.alexandre01.dreamnetwork.api.service.IContainer;
import fr.benjimania74.dnbotlink.Main;

public class ExecuteServerCmd {
    public void execute(String cmd, String serviceName, IContainer.JVMType type){
        for(IClient client : Main.clientAPI.getClientManager().getClients().values()){
            if(client.getJvmService().getJvmExecutor().getName().equals(serviceName) && client.getJvmType() == type){
                // EXECUTE COMMAND
                // SERVICE IS ONLINE
                return;
            }
        }

        // SERVICE IS OFFLINE
    }
}
