package fr.benjimania74.dnbotlink.utils;

import be.alexandre01.dreamnetwork.api.service.IContainer;
import be.alexandre01.dreamnetwork.client.console.colors.Colors;
import fr.benjimania74.dnbotlink.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Objects;

public class ServicesStarter {
    private final IContainer container = Main.clientAPI.getContainer();
    private String serviceName = "";

    public String startD(String @NotNull [] serviceI){
        serviceName = serviceI[0];
        String serviceStarting = Colors.GREEN_BACKGROUND + serviceName + " is now Starting";

        if(serviceI.length == 1){
            if(Services.isBoth(serviceName)){return Colors.RED + serviceName + " is a Server and a Proxy ! Type '" + serviceName + " server' for server and '" + serviceName + " proxy' for proxy";}
            if(Services.getType(serviceName) == null){return Colors.RED + serviceName + " is not a Service";}
            startService(serviceName);
            return serviceStarting;
        }
        if(serviceI[1].equalsIgnoreCase("server")){
            if(container.getJVMExecutorsServers().containsKey(serviceName)){startService(serviceName, IContainer.JVMType.SERVER);return serviceStarting;}
            return Colors.RED + serviceName + " is not a Server";
        }
        if(serviceI[1].equalsIgnoreCase("proxy")){
            if(container.getJVMExecutorsProxy().containsKey(serviceName)){startService(serviceName, IContainer.JVMType.PROXY);return serviceStarting;}
            return Colors.RED + serviceName + " is not a Proxy";
        }
        return Colors.RED + "This Service doesn't exists";
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
        EmbedBuilder proxyNotLaunched = new EmbedBuilder()
                .setColor(Color.RED)
                .setTitle("There is no Proxy Running")
                .setDescription("To Launch a Server, a Proxy must be Running");
        EmbedBuilder innexistantService = new EmbedBuilder()
                .setColor(Color.RED)
                .setTitle("Innexistant Service")
                .setDescription(serviceName + " is not a Service");
        EmbedBuilder doubleService = new EmbedBuilder()
                .setColor(Color.RED)
                .setTitle("Double Service")
                .setDescription(serviceName + " is a Server and a Proxy ! Type '" + serviceName + " server' for server and '" + serviceName + " proxy' for proxy");
        EmbedBuilder incorrectST = new EmbedBuilder()
                .setColor(Color.RED)
                .setTitle("Incorrect Service's Type");

        if(serviceI.length == 1){
            if(Services.isBoth(serviceName)){return doubleService;}
            IContainer.JVMType type = Services.getType(serviceName);
            if(type == null){return innexistantService;}
            if(!Services.isDynamic(serviceName, type)){
                if(Services.isLaunched(serviceName)){ return alreadyRunning; }
                if(!Services.isProxyLaunched() && type.equals(IContainer.JVMType.SERVER)){return proxyNotLaunched;}
            }
            startService(serviceName, type);
            return successEmbed;
        }
        if(serviceI[1].equalsIgnoreCase("server")){
            if(!Services.isDynamic(serviceName, IContainer.JVMType.SERVER)){
                if(Services.isLaunched(serviceName, IContainer.JVMType.SERVER)){return alreadyRunning; }
                if(!Services.isProxyLaunched()){return proxyNotLaunched;}
            }
            if(container.getJVMExecutorsServers().containsKey(serviceName)){startService(serviceName, IContainer.JVMType.SERVER);return successEmbed;}
            return incorrectST.setDescription(serviceName + " is not a Server");
        }
        if(serviceI[1].equalsIgnoreCase("proxy")){
            if(!Services.isDynamic(serviceName, IContainer.JVMType.PROXY)){
                if(Services.isLaunched(serviceName, IContainer.JVMType.PROXY)){return alreadyRunning; }
                if(container.getJVMExecutorsProxy().containsKey(serviceName)){startService(serviceName, IContainer.JVMType.PROXY);return successEmbed;}
            }
            return incorrectST.setDescription(serviceName + " is not a Proxy");
        }
        return innexistantService;
    }

    public void startService(String serviceName){startService(serviceName, Objects.requireNonNull(Services.getType(serviceName)));}
    public void startService(String serviceName, IContainer.JVMType serviceType){container.getJVMExecutor(serviceName, serviceType).startServer();}
}