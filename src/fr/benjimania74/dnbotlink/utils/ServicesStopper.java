package fr.benjimania74.dnbotlink.utils;

import be.alexandre01.dreamnetwork.api.service.IContainer;
import be.alexandre01.dreamnetwork.client.console.Console;
import be.alexandre01.dreamnetwork.client.console.colors.Colors;
import fr.benjimania74.dnbotlink.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class ServicesStopper {
    private final IContainer container = Main.clientAPI.getContainer();
    private String serviceName = "";

    public void stopD(String @NotNull [] serviceI){
        serviceName = serviceI[0];

        if(serviceI.length == 1){
            if(Services.isBoth(serviceName)){
                Console.print(Colors.RED + serviceName + " is a Server and a Proxy ! Type '" + serviceName + " server' for server and '" + serviceName + " proxy' for proxy");
                return;
            }
            if(Services.getType(serviceName) == null){
                Console.print(Colors.RED + serviceName + " is not a Service");
                return;
            }

            if(!Services.isLaunched(serviceName)){Console.print(Colors.RED + "This service is not Running");return;}

            container.getJVMExecutor(serviceName, Services.getType(serviceName)).getService(0).stop();
            container.getJVMExecutor(serviceName, Services.getType(serviceName)).getService(0).removeService();
            Console.print(Colors.GREEN + "Service '" + serviceName + "' stopped");
            return;
        }

        if(serviceI[1].equalsIgnoreCase("server")){
            if(container.getJVMExecutorsServers().containsKey(serviceName)){
                if(!Services.isLaunched(serviceName, IContainer.JVMType.SERVER)){Console.print(Colors.RED + "This service is not Running");return;}
                container.getJVMExecutor(serviceName, IContainer.JVMType.SERVER).getService(0).stop();
                container.getJVMExecutor(serviceName, IContainer.JVMType.SERVER).getService(0).removeService();
                return;
            }
            Console.print(Colors.RED + serviceName + " is not a Server");
            return;
        }
        if(serviceI[1].equalsIgnoreCase("proxy")){
            if(container.getJVMExecutorsProxy().containsKey(serviceI[0])){
                if(!Services.isLaunched(serviceName, IContainer.JVMType.PROXY)){Console.print(Colors.RED + "This service is not Running");return;}
                container.getJVMExecutor(serviceName, IContainer.JVMType.PROXY).getService(0).stop();
                container.getJVMExecutor(serviceName, IContainer.JVMType.PROXY).getService(0).removeService();
                return;
            }
            Console.print(Colors.RED + serviceName + " is not a Proxy");
            return;
        }

        Console.print(Colors.RED + "This Service doesn't exists");
    }

    public EmbedBuilder stopB(String @NotNull [] serviceI){
        serviceName = serviceI[0];

        EmbedBuilder successEmbed = new EmbedBuilder()
                .setColor(Color.GREEN)
                .setTitle("'" + serviceName + "' Service is Stopping...")
                .setDescription("The service '" + serviceName + "' is now Stopping");
        EmbedBuilder notRunning = new EmbedBuilder()
                .setColor(Color.RED)
                .setTitle("Not Running")
                .setDescription("'" + serviceName + "' Service is not Running");

        if(serviceI.length == 1){
            if(Services.isBoth(serviceName)){
                return new EmbedBuilder()
                        .setColor(Color.RED)
                        .setTitle("Double Service")
                        .setDescription(serviceName + " is a Server and a Proxy ! Type '" + serviceName + " server' for server and '" + serviceName + " proxy' for proxy");
            }

            if(Services.getType(serviceName) == null){
                return new EmbedBuilder()
                        .setColor(Color.RED)
                        .setTitle("Inexistant Service")
                        .setDescription(serviceName + " is not a Service");
            }

            if(!Services.isLaunched(serviceName)){return notRunning;}

            container.getJVMExecutor(serviceName, Services.getType(serviceName)).getService(0).stop();
            container.getJVMExecutor(serviceName, Services.getType(serviceName)).getService(0).removeService();
            return successEmbed;
        }

        if(serviceI[1].equalsIgnoreCase("server")){
            if(container.getJVMExecutorsServers().containsKey(serviceName)){
                if(!Services.isLaunched(serviceName, IContainer.JVMType.SERVER)){return notRunning;}
                container.getJVMExecutor(serviceName, IContainer.JVMType.SERVER).getService(0).stop();
                container.getJVMExecutor(serviceName, IContainer.JVMType.SERVER).getService(0).removeService();
                return successEmbed;
            }
            return new EmbedBuilder()
                    .setColor(Color.RED)
                    .setTitle("Incorrect Service's Type")
                    .setDescription(serviceName + " is not a Server");
        }

        if(serviceI[1].equalsIgnoreCase("proxy")){
            if(container.getJVMExecutorsProxy().containsKey(serviceName)){
                if(!Services.isLaunched(serviceName, IContainer.JVMType.PROXY)){return notRunning;}
                container.getJVMExecutor(serviceName, IContainer.JVMType.PROXY).getService(0).stop();
                container.getJVMExecutor(serviceName, IContainer.JVMType.PROXY).getService(0).removeService();
                return successEmbed;
            }
            return new EmbedBuilder()
                    .setColor(Color.RED)
                    .setTitle("Incorrect Service's Type")
                    .setDescription(serviceName + " is not a Proxy");
        }

        return new EmbedBuilder()
                .setColor(Color.RED)
                .setTitle("Inexistant Service")
                .setDescription(serviceName + " is not a Service");
    }
}
