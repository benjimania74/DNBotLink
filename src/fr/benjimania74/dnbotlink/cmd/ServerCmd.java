package fr.benjimania74.dnbotlink.cmd;

import be.alexandre01.dreamnetwork.api.commands.Command;
import be.alexandre01.dreamnetwork.client.console.Console;
import be.alexandre01.dreamnetwork.client.console.colors.Colors;
import fr.benjimania74.dnbotlink.utils.Services;
import fr.benjimania74.dnbotlink.utils.ServicesStarter;
import fr.benjimania74.dnbotlink.utils.ServicesStopper;

public class ServerCmd extends Command {
    public ServerCmd(String name) {
        super(name);

        addSubCommand("start", args -> {
            if(args.length == 1){
                Console.print(Colors.RED + "Invalid Command");
                return true;
            }

            if(Services.isLaunched(args[1])){Console.print(Colors.RED + "This Service is already Running");return true;}

            String[] serviceI;
            if(args.length == 2){ serviceI = new String[]{args[1]}; }else{ serviceI = new String[]{args[1], args[2]}; }
            Console.print(new ServicesStarter().startD(serviceI));
            return true;
        });

        addSubCommand("stop", args -> {
            if(args.length == 1){Console.print(Colors.RED + "Invalid Command");return true;}

            if(!Services.isServicesLaunched()){Console.print(Colors.RED + "There's no Services Running");return true;}
            if(!Services.isLaunched(args[1])){Console.print(Colors.RED + "This Service is not Running");return true;}

            String[] serviceI;
            if(args.length == 2){ serviceI = new String[]{args[1]}; }else{ serviceI = new String[]{args[1], args[2]}; }
            Console.print(new ServicesStopper().stopD(serviceI));
            return true;
        });

        getHelpBuilder().setTitleUsage("Server Command")
                .setCmdUsage("Launch a server","start", "<service> [<proxy | server>]")
                .setCmdUsage("Launch a server","stop", "<service> [<proxy | server>]");
    }
}
