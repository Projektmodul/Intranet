package com.example.application.ui.vertical.notifications;

import java.util.ArrayList;
import java.util.List;

/**
 * Initiator to trigger an event, when the notification counter is changed
 *
 *
 * @author  Anastasiya Jackwerth, Sabrine Gamdou
 * @version 1.0
 * @since   26.01.2021
 * @lastUpdated 26.01.2021
 */
public class NotificationCounterChangedInitiator{
    private List<NotificationCounterChangedListener> listeners = new ArrayList<>();

    public void addListener(NotificationCounterChangedListener notificationCounterChangedListener){
        listeners.add(notificationCounterChangedListener);
    }

    public void notificationCounterChanged(){
        for(NotificationCounterChangedListener listener : listeners){
            listener.notificationCounterChanged();
        }
    }
}
