package fr.benjimania74.dnapitest.listeners;

import be.alexandre01.dreamnetwork.api.events.EventCatcher;
import be.alexandre01.dreamnetwork.api.events.Listener;
import be.alexandre01.dreamnetwork.api.events.list.services.CoreServiceStartEvent;
import be.alexandre01.dreamnetwork.api.service.IService;

public class ServiceStartListener extends Listener {
    @EventCatcher
    public void onServiceStart(CoreServiceStartEvent event){
        IService service = event.getService();
    }
}
