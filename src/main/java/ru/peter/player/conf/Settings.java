package ru.peter.player.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "music")
public class Settings {
    private String musicDirectory;
    private Integer currentSong;
    private boolean isRepeate;
    @Value("${server.port}")
    private String port;

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

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}