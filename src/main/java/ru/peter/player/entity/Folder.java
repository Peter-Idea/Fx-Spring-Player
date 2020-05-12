package ru.peter.player.entity;

import org.springframework.stereotype.Component;


@Component
public class Folder {
    private String path;
    private boolean up;

    public Folder() {

    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

}
