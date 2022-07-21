package fr.benjimania74.dnapitest.cmd;

import be.alexandre01.dreamnetwork.api.commands.Command;
import be.alexandre01.dreamnetwork.api.connection.core.communication.IClient;
import be.alexandre01.dreamnetwork.api.connection.core.communication.IClientManager;
import be.alexandre01.dreamnetwork.api.service.IContainer;
import be.alexandre01.dreamnetwork.client.console.Console;
import be.alexandre01.dreamnetwork.client.console.colors.Colors;
import fr.benjimania74.dnapitest.Main;
import fr.benjimania74.dnapitest.utils.ServicesStarter;

public class ServerCmd extends Command {
    public ServerCmd(String name) {
        super(name);

        addSubCommand("start", args -> {
            if(args.length == 1){
                Console.print(Colors.RED + "Invalid Command");
                return true;
            }

            for(IClient client : Main.clients){
                if(client.getJvmService().getJvmExecutor().getName().equals(args[1])){
                    Console.print(Colors.RED + "This Service is already Running");
                    return true;
                }
            }

            String[] serviceI;
            if(args.length == 2){ serviceI = new String[]{args[1]}; }else{ serviceI = new String[]{args[1], args[2]}; }
            new ServicesStarter().startD(serviceI);
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
