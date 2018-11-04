package com.example.spade.thebigone;

import java.util.ArrayList;

public final class CruiseList extends ArrayList<Cruise> {
    private static CruiseList ourInstance = null;

    public static CruiseList getInstance() {
        if(ourInstance == null)
            ourInstance = new CruiseList();
        return ourInstance;
    }

    private CruiseList() {// Exists only to defeat additional instantiations.}
    }
}
