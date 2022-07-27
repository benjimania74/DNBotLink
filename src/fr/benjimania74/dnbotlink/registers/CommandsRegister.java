package fr.benjimania74.dnbotlink.registers;

import be.alexandre01.dreamnetwork.api.DNClientAPI;
import be.alexandre01.dreamnetwork.client.console.Console;
import be.alexandre01.dreamnetwork.client.console.colors.Colors;
import fr.benjimania74.dnbotlink.cmd.AutoStarterCmd;
import fr.benjimania74.dnbotlink.cmd.ClientsCmd;
import fr.benjimania74.dnbotlink.cmd.ServerCmd;

public class CommandsRegister {
    public static boolean register(DNClientAPI clientAPI){
        try {
            clientAPI.getCommandReader().getCommands().addCommands(new ServerCmd("server"));
            clientAPI.getCommandReader().getCommands().addCommands(new ClientsCmd("clients"));
            clientAPI.getCommandReader().getCommands().addCommands(new AutoStarterCmd("autostart"));
            return true;
        }catch (Exception e){
            Console.print(Colors.RED + "[ERROR] Commands can't be registered");
            return false;
        }
    }
}
