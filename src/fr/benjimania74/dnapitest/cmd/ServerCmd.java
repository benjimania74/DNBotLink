package fr.benjimania74.dnapitest.cmd;

import be.alexandre01.dreamnetwork.api.commands.Command;
import be.alexandre01.dreamnetwork.api.connection.core.communication.IClient;
import be.alexandre01.dreamnetwork.api.connection.core.communication.IClientManager;
import be.alexandre01.dreamnetwork.api.service.IContainer;
import be.alexandre01.dreamnetwork.client.console.Console;
import be.alexandre01.dreamnetwork.client.console.colors.Colors;
import fr.benjimania74.dnapitest.Main;
import fr.benjimania74.dnapitest.utils.ServicesStarter;
import fr.benjimania74.dnapitest.utils.ServicesStopper;

public class ServerCmd extends Command {
    public ServerCmd(String name) {
        super(name);

        addSubCommand("start", args -> {
            if(args.length == 1){
                Console.print(Colors.RED + "Invalid Command");
                return true;
            }

            if(!Main.clientAPI.getClientManager().getClients().isEmpty()){
                for(IClient client : Main.clientAPI.getClientManager().getClients().values()){
                    if(client.getJvmService().getJvmExecutor().getName().equals(args[1])){
                        Console.print(Colors.RED + "This Service is already Running");
                        return true;
                    }
                }
            }

            String[] serviceI;
            if(args.length == 2){ serviceI = new String[]{args[1]}; }else{ serviceI = new String[]{args[1], args[2]}; }
            new ServicesStarter().startD(serviceI);
            return true;
        });

        addSubCommand("stop", args -> {
            if(args.length == 1){
                Console.print(Colors.RED + "Invalid Command");
                return true;
            }

            if(Main.clientAPI.getClientManager().getClients().isEmpty()){
                Console.print(Colors.RED + "There's no Services Running");
                return true;
            }

            for(IClient client : Main.clientAPI.getClientManager().getClients().values()){
                if(!client.getJvmService().getJvmExecutor().getName().equals(args[1])){
                    Console.print(Colors.RED + "This Service is not Running");
                    return true;
                }
            }

            String[] serviceI;
            if(args.length == 2){ serviceI = new String[]{args[1]}; }else{ serviceI = new String[]{args[1], args[2]}; }
            new ServicesStopper().stopD(serviceI);
            return true;
        });

        getHelpBuilder().setTitleUsage("Server Command");
        getHelpBuilder().setCmdUsage("Launch a server","start", "<service name> [<proxy | server>]");
        getHelpBuilder().setCmdUsage("Launch a server","stop", "<service name> [<proxy | server>]");
    }
}
