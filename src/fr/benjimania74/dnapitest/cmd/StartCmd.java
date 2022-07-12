package fr.benjimania74.dnapitest.cmd;

import be.alexandre01.dreamnetwork.api.commands.Command;
import be.alexandre01.dreamnetwork.api.service.IContainer;
import fr.benjimania74.dnapitest.Main;

public class StartCmd extends Command {
    public StartCmd(String name) {
        super(name);

        addSubCommand("start", args -> {
            if(args.length == 1){
                System.out.println("Commande invalide !");
                return true;
            }

            if(Main.clientAPI.getClientManager().getClients().containsKey(args[1])){
                System.out.println("This Server is already Launched");
                return true;
            }

            if(Main.clientAPI.getContainer().getJVMExecutorsServers().containsKey(args[1])){
                Main.clientAPI.getContainer().getJVMExecutor(args[1], IContainer.JVMType.SERVER).startServer();
            }else{
                System.out.println("This Server does't exist");
            }
            return true;
        });

        /*addSubCommand("start", args -> {
            if(args.length == 1){
                System.out.println("Commande invalide !");
                return true;
            }

            Main.clientAPI.getContainer().stop(args[1], "Test");
            return true;
        });*/

        getHelpBuilder().setTitleUsage("Server Command");
        getHelpBuilder().setCmdUsage("Launch a server","start", "<server name>");
    }
}
