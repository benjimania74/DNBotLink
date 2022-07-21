package fr.benjimania74.dnapitest.utils;

import be.alexandre01.dreamnetwork.api.service.IContainer;
import be.alexandre01.dreamnetwork.client.console.Console;
import be.alexandre01.dreamnetwork.client.console.colors.Colors;
import fr.benjimania74.dnapitest.Main;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

public class ServicesStarter {
    private IContainer container = Main.clientAPI.getContainer();
    private String serviceName = "";
    private EmbedBuilder succesEmbed = new EmbedBuilder()
            .setColor(Color.GREEN)
            .setTitle(serviceName + " Started")
            .setDescription("The service " + serviceName + " is now Starting");

    public void startD(String[] serviceI){;
        serviceName = serviceI[0];

        if(serviceI.length == 1){
            if(isBoth(serviceName)){
                Console.print(Colors.RED + serviceName + " is a Server and a Proxy ! Type '" + serviceName + " server' for server and '" + serviceName + " proxy' for proxy");
                return;
            }
            if(getType(serviceName) == null){
                Console.print(Colors.RED + serviceName + " is not a Service");
                return;
            }
            container.getJVMExecutor(serviceName, getType(serviceName)).startServer();
            return;
        }

        if(serviceI[1].equalsIgnoreCase("server")){
            if(container.getJVMExecutorsServers().containsKey(serviceI[0])){
                container.getJVMExecutor(serviceName, IContainer.JVMType.SERVER).startServer();
                return;
            }
            Console.print(Colors.RED + serviceName + " is not a Server");
            return;
        }
        if(serviceI[1].equalsIgnoreCase("proxy")){
            if(container.getJVMExecutorsProxy().containsKey(serviceI[0])){
                container.getJVMExecutor(serviceName, IContainer.JVMType.PROXY).startServer();
                return;
            }
            Console.print(Colors.RED + serviceName + " is not a Proxy");
            return;
        }

        Console.print(Colors.RED + "This Service doesn't exists");
    }

    public EmbedBuilder startB(String[] serviceI){
        serviceName = serviceI[0];

        if(serviceI.length == 1){
            if(isBoth(serviceName)){
                return new EmbedBuilder()
                        .setColor(Color.RED)
                        .setTitle("Double Service")
                        .setDescription(serviceName + " is a Server and a Proxy ! Type '" + serviceName + " server' for server and '" + serviceName + " proxy' for proxy");
            }

            if(getType(serviceName) == null){
                return new EmbedBuilder()
                        .setColor(Color.RED)
                        .setTitle("Inexistant Service")
                        .setDescription(serviceName + " is not a Service");
            }
            container.getJVMExecutor(serviceName, getType(serviceName)).startServer();
            return succesEmbed;
        }

        if(serviceI[1].equalsIgnoreCase("server")){
            if(container.getJVMExecutorsServers().containsKey(serviceI[0])){
                container.getJVMExecutor(serviceName, IContainer.JVMType.SERVER).startServer();
                return succesEmbed;
            }
            return new EmbedBuilder()
                    .setColor(Color.RED)
                    .setTitle("Incorrect Service's Type")
                    .setDescription(serviceName + " is not a Server");
        }

        if(serviceI[1].equalsIgnoreCase("proxy")){
            if(container.getJVMExecutorsProxy().containsKey(serviceI[0])){
                container.getJVMExecutor(serviceName, IContainer.JVMType.PROXY).startServer();
                return succesEmbed;
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

    private boolean isBoth(String serviceName){
        boolean isServ = false;
        boolean isProxy = false;

        if(container.getJVMExecutorsServers().containsKey(serviceName)){isServ = true;}
        if(container.getJVMExecutorsProxy().containsKey(serviceName)){isProxy = true;}

        return isProxy && isServ;
    }

    private IContainer.JVMType getType(String serviceName){
        if(container.getJVMExecutorsServers().containsKey(serviceName)){return IContainer.JVMType.SERVER;}
        if(container.getJVMExecutorsProxy().containsKey(serviceName)){return IContainer.JVMType.PROXY;}
        return null;
    }
}
