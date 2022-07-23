package fr.benjimania74.dnbotlink.utils;

import be.alexandre01.dreamnetwork.api.connection.core.communication.IClient;
import be.alexandre01.dreamnetwork.api.service.IContainer;
import fr.benjimania74.dnbotlink.Main;

public class Services {
    private static final IContainer container = Main.clientAPI.getContainer();

    public static boolean isBoth(String serviceName){
        boolean isServ = false;
        boolean isProxy = false;

        if(container.getJVMExecutorsServers().containsKey(serviceName)){isServ = true;}
        if(container.getJVMExecutorsProxy().containsKey(serviceName)){isProxy = true;}

        return isProxy && isServ;
    }

    public static IContainer.JVMType getType(String serviceName){
        if(container.getJVMExecutorsServers().containsKey(serviceName)){return IContainer.JVMType.SERVER;}
        if(container.getJVMExecutorsProxy().containsKey(serviceName)){return IContainer.JVMType.PROXY;}
        return null;
    }

    public static boolean isLaunched(String serviceName){
        for(IClient client : Main.clientAPI.getClientManager().getClients().values()) {
            if(client.getJvmService().getJvmExecutor().getName().equals(serviceName)){
                return true;
            }
        }
        return false;
    }

    public static boolean isLaunched(String serviceName, IContainer.JVMType serviceType){
        for(IClient client : Main.clientAPI.getClientManager().getClients().values()) {
            if(client.getJvmService().getJvmExecutor().getName().equals(serviceName) && client.getJvmType().equals(serviceType)){
                return true;
            }
        }
        return false;
    }
}
