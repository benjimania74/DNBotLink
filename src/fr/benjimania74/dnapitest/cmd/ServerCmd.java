package fr.benjimania74.dnapitest.cmd;

import be.alexandre01.dreamnetwork.api.commands.Command;
import be.alexandre01.dreamnetwork.api.connection.core.communication.IClient;
import be.alexandre01.dreamnetwork.api.connection.core.communication.IClientManager;
import be.alexandre01.dreamnetwork.api.service.IContainer;
import fr.benjimania74.dnapitest.Main;

public class ServerCmd extends Command {
    public ServerCmd(String name) {
        super(name);

        addSubCommand("start", args -> {
            if(args.length == 1){
                System.out.println("Commande invalide !");
                return true;
            }

            if(Main.clientAPI.getClientManager().getClients().containsKey(args[1])){
                System.out.println("This Server is already Running");
                return true;
            }

            if(Main.clientAPI.getContainer().getJVMExecutorsServers().containsKey(args[1])){
                Main.clientAPI.getContainer().getJVMExecutor(args[1], IContainer.JVMType.SERVER).startServer();
            }else{
                System.out.println("The Server does't exist");
            }
            return true;
        });

        addSubCommand("stop", args -> {
            if(args.length == 1){
                System.out.println("Commande invalide !");
                return true;
            }

            IClientManager clientManager = Main.clientAPI.getClientManager();
            System.out.println(clientManager.getClients());
            if(!clientManager.getClients().containsKey(args[1])){
                System.out.println("The Server is not Running for the moment");
                return true;
            }
            IClient client = clientManager.getClient(args[1]);
            if(client.getJvmType() != IContainer.JVMType.SERVER){
                System.out.println(args[1] + " is not a Server but a Proxy");
                return true;
            }

            clientManager.getClient(args[1]).getJvmService().stop();
            System.out.println("The Server has been Successfully Stopped");
            clientManager.getClients().remove(args[1]);
            return true;
        });

        getHelpBuilder().setTitleUsage("Server Command");
        getHelpBuilder().setCmdUsage("Launch a server","start", "<server name>");
    }
}
