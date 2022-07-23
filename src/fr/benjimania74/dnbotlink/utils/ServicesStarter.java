package fr.benjimania74.dnbotlink.utils;

import be.alexandre01.dreamnetwork.api.service.IContainer;
import be.alexandre01.dreamnetwork.client.console.Console;
import be.alexandre01.dreamnetwork.client.console.colors.Colors;
import fr.benjimania74.dnbotlink.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class ServicesStarter {
    private final IContainer container = Main.clientAPI.getContainer();
    private String serviceName = "";

    public void startD(String @NotNull [] serviceI){
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
            container.getJVMExecutor(serviceName, Services.getType(serviceName)).startServer();
            return;
        }

        if(serviceI[1].equalsIgnoreCase("server")){
            if(container.getJVMExecutorsServers().containsKey(serviceName)){
                container.getJVMExecutor(serviceName, IContainer.JVMType.SERVER).startServer();
                return;
            }
            Console.print(Colors.RED + serviceName + " is not a Server");
            return;
        }
        if(serviceI[1].equalsIgnoreCase("proxy")){
            if(container.getJVMExecutorsProxy().containsKey(serviceName)){
                container.getJVMExecutor(serviceName, IContainer.JVMType.PROXY).startServer();
                return;
            }
            Console.print(Colors.RED + serviceName + " is not a Proxy");
            return;
        }

        Console.print(Colors.RED + "This Service doesn't exists");
    }

    public EmbedBuilder startB(String @NotNull [] serviceI){
        serviceName = serviceI[0];

        EmbedBuilder successEmbed = new EmbedBuilder()
                .setColor(Color.GREEN)
                .setTitle("'" + serviceName + "' Service is Starting...")
                .setDescription("The service '" + serviceName + "' is now Starting");
        EmbedBuilder alreadyRunning = new EmbedBuilder()
                .setColor(Color.RED)
                .setTitle("Already Running")
                .setDescription("'" + serviceName + "'Service is Already Running");

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

            if(Services.isLaunched(serviceName)){ return alreadyRunning; }

            container.getJVMExecutor(serviceName, Services.getType(serviceName)).startServer();
            return successEmbed;
        }

        if(serviceI[1].equalsIgnoreCase("server")){
            if(Services.isLaunched(serviceName, IContainer.JVMType.SERVER)){return alreadyRunning; }
            if(container.getJVMExecutorsServers().containsKey(serviceName)){
                container.getJVMExecutor(serviceName, IContainer.JVMType.SERVER).startServer();
                return successEmbed;
            }
            return new EmbedBuilder()
                    .setColor(Color.RED)
                    .setTitle("Incorrect Service's Type")
                    .setDescription(serviceName + " is not a Server");
        }

        if(serviceI[1].equalsIgnoreCase("proxy")){
            if(Services.isLaunched(serviceName, IContainer.JVMType.PROXY)){return alreadyRunning; }
            if(container.getJVMExecutorsProxy().containsKey(serviceName)){
                container.getJVMExecutor(serviceName, IContainer.JVMType.PROXY).startServer();
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
