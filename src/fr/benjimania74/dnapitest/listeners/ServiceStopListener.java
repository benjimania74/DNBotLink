package fr.benjimania74.dnapitest.listeners;

import be.alexandre01.dreamnetwork.api.events.EventCatcher;
import be.alexandre01.dreamnetwork.api.events.Listener;
import be.alexandre01.dreamnetwork.api.events.list.services.CoreServiceStartEvent;
import be.alexandre01.dreamnetwork.api.service.IService;
import fr.benjimania74.dnapitest.Main;

public class ServiceStopListener extends Listener {
    @EventCatcher
    public void onServiceStop(CoreServiceStartEvent event){
        IService service = event.getService();
        Main.clients.remove(service.getClient());
    }
}
