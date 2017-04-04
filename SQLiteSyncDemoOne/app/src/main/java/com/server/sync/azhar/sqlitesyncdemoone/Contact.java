package com.server.sync.azhar.sqlitesyncdemoone;

/**
 * Created by Azhar on 3/31/2017.
 */

public class Contact {

    private String name;
    private int syncStatus;
    private  int id;

    public Contact(String name, int syncStatus) {

        setName(name);
        setSyncStatus(syncStatus);
        setId(id);
   
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(int syncStatus) {
        this.syncStatus = syncStatus;
    }
}
