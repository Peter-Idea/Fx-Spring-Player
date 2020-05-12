package ru.peter.player.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "music")
public class Settings {
    private String musicDirectory;
    private Integer currentSong;
    private boolean isRepeate;

//    TODO подумать над тем как сохранять настройки

    public Settings() {
        System.out.println("Start Settings");
    }

    public String getMusicDirectory() {
        return musicDirectory;
    }

    public void setMusicDirectory(String musicDirectory) {
        this.musicDirectory = musicDirectory;
    }

    public Integer getCurrentSong() {
        return currentSong;
    }

    public void setCurrentSong(Integer currentSong) {
        this.currentSong = currentSong;
    }

    public boolean isRepeate() {

        return isRepeate;
    }

    public void setRepeate(boolean repeate) {
        isRepeate = repeate;
    }

}