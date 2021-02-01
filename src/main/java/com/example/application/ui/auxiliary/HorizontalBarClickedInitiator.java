package com.example.application.ui.auxiliary;

import java.util.ArrayList;
import java.util.List;

/**
 *  HorizontalBarClickedInitiator initiates a click listener for a listen-response-system between HorizontalBar and
 *  SideBar
 *
 *  @author Vanessa Skowronsky
 *  @version 1.0
 *  @since 17.01.2021
 *  @lastUpdated 01.02.2021 by Vanessa Skowronsky
 */
public class HorizontalBarClickedInitiator {
    private List<HorizontalBarClickedListener> listeners = new ArrayList<>();

    public void addListener(HorizontalBarClickedListener horizontalBarClickedListener){
        listeners.add(horizontalBarClickedListener);
    }

    public void horizontalBarClicked(){
        for(HorizontalBarClickedListener listener: listeners){
            listener.horizontalBarClicked();
        }
    }
}
