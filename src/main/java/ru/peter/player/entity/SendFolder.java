package ru.peter.player.entity;

import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class SendFolder {
    File file;
    File[] listFile;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public File[] getListFile() {
        return listFile;
    }

    public void setListFile(File[] listFile) {
        this.listFile = listFile;
    }
}
