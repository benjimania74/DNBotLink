package fr.benjimania74.dnbotlink.utils;

import be.alexandre01.dreamnetwork.api.service.IContainer;
import be.alexandre01.dreamnetwork.api.service.IJVMExecutor;
import be.alexandre01.dreamnetwork.client.console.colors.Colors;
import fr.benjimania74.dnbotlink.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class ServicesStopper {
    private final IContainer container = Main.clientAPI.getContainer();
    private String serviceName = "";

    public String stopD(String @NotNull [] serviceI){
        serviceName = serviceI[0];
        String notAService = Colors.RED + serviceName + " is not a Service";

        if(isAllServices(serviceName)){stopAllServices(); return Colors.GREEN_BACKGROUND + "All Services have been Stopped";}

        if(serviceI.length == 1){
            if(Services.isBoth(serviceName)){
                return Colors.RED + serviceName + " is a Server and a Proxy ! Type '" + serviceName + " server' for server and '" + serviceName + " proxy' for proxy";
            }
            IContainer.JVMType type = Services.getType(serviceName);
            if(type == null){return notAService;}
            if(Services.containMultiple(serviceName, type)){return Colors.RED + "This service is DYNAMIC started multiple time, please use 'stop dynamic' command";}
            stopServer(container.getJVMExecutor(serviceName, Services.getType(serviceName)));
            return Colors.GREEN + "Service '" + serviceName + "' stopped";
        }

        if(serviceI[1].equalsIgnoreCase("server")){
            if(!container.getJVMExecutorsServers().containsKey(serviceName)){return Colors.RED + serviceName + " is not a Server";}
            if(Services.containMultiple(serviceName, IContainer.JVMType.SERVER)){return Colors.RED + "This service is DYNAMIC started multiple time, please use 'stop dynamic' command";}
            stopServer(container.getJVMExecutor(serviceName, Services.getType(serviceName)));
            return Colors.RED + serviceName + " is not a Server";
        }
        if(serviceI[1].equalsIgnoreCase("proxy")){
            if(!container.getJVMExecutorsProxy().containsKey(serviceI[0])){return Colors.RED + serviceName + " is not a Proxy";}
            if(Services.containMultiple(serviceName, IContainer.JVMType.PROXY)){return Colors.RED + "This service is DYNAMIC and started multiple time, please use 'stop dynamic' command";}
            stopServer(container.getJVMExecutor(serviceName, IContainer.JVMType.PROXY));
            return Colors.RED + serviceName + " is not a Proxy";
        }
        return notAService;
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
        EmbedBuilder allServicesStopped = new EmbedBuilder()
                .setColor(Color.GREEN)
                .setTitle("Services Stopped")
                .setDescription("All Services have been stopped !");
        EmbedBuilder doubleService = new EmbedBuilder()
                .setColor(Color.RED)
                .setTitle("Double Service")
                .setDescription(serviceName + " is a Server and a Proxy ! Type '" + serviceName + " server' for server and '" + serviceName + " proxy' for proxy");
        EmbedBuilder innexistantService = new EmbedBuilder()
                .setColor(Color.RED)
                .setTitle("Innexistant Service")
                .setDescription(serviceName + " is not a Service");
        EmbedBuilder incorrectST = new EmbedBuilder()
                .setColor(Color.RED)
                .setTitle("Incorrect Service's Type");
        EmbedBuilder dynamicS = new EmbedBuilder()
                .setColor(Color.RED)
                .setTitle("Dynamic Server")
                .setDescription("This service is DYNAMIC started multiple time, please use 'stop dynamic' command");

        if(isAllServices(serviceName)){stopAllServices();return allServicesStopped;}

        if(serviceI.length == 1){
            IContainer.JVMType type = Services.getType(serviceName);
            if(Services.isBoth(serviceName)){return doubleService;}
            if(type == null){return innexistantService;}
            if(!Services.isLaunched(serviceName)){return notRunning;}
            if(Services.containMultiple(serviceName, type)){return dynamicS;}
            stopServer(container.getJVMExecutor(serviceName, Services.getType(serviceName)));
            return successEmbed;
        }

        if(serviceI[1].equalsIgnoreCase("server")){
            if(container.getJVMExecutorsServers().containsKey(serviceName)){
                if(!Services.isLaunched(serviceName, IContainer.JVMType.SERVER)){return notRunning;}
                if(Services.containMultiple(serviceName, IContainer.JVMType.SERVER)){return dynamicS;}
                stopServer(container.getJVMExecutor(serviceName, IContainer.JVMType.SERVER));
                return successEmbed;
            }
            return incorrectST.setDescription(serviceName + " is not a Server");
        }

        if(serviceI[1].equalsIgnoreCase("proxy")){
            if(container.getJVMExecutorsProxy().containsKey(serviceName)){
                if(!Services.isLaunched(serviceName, IContainer.JVMType.PROXY)){return notRunning;}
                if(Services.containMultiple(serviceName, IContainer.JVMType.PROXY)){return dynamicS;}
                stopServer(container.getJVMExecutor(serviceName, IContainer.JVMType.PROXY));
                return successEmbed;
            }
            return incorrectST.setDescription(serviceName + " is not a Proxy");
        }
        return innexistantService;
    }

    private boolean isAllServices(@NotNull String name){ return name.equalsIgnoreCase("allservices");}
    private void stopServer(@NotNull IJVMExecutor executor){executor.getService(0).stop();}
    private void stopAllServices(){ Main.clientAPI.getClientManager().getClients().values().forEach(iClient -> iClient.getJvmService().stop()); }

    public String stopDynamic(String s) {
        if(Main.clientAPI.getClientManager().getClients().containsKey(s)){
            stopServer(Main.clientAPI.getClientManager().getClient(s).getJvmService().getJvmExecutor());
            return Colors.GREEN + s + " is now stopped";
        }
        return Colors.RED + "This service is not started";
    }
}