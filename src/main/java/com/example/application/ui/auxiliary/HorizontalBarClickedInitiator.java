package com.example.application.ui.auxiliary;

import java.util.ArrayList;
import java.util.List;

/**
 *  HorizontalBarClickedInitiator shows ...
 *
 *  @author Vanessa Skowronsky
 *  @version 1.0
 *  @since 17.01.2021
 *  @lastUpdated 17.01.2021
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
