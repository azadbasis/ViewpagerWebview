package com.server.azhar.listviewsectionheaderfooter;

/**
 * Created by Azhar on 3/26/2017.
 */

public class Player {

    private String name;
    private  int image;

    public Player(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
