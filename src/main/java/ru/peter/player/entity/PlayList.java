package ru.peter.player.entity;

import ru.peter.player.conf.Settings;
import ru.peter.player.servises.ServiceFiles;
import javafx.scene.media.Media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PlayList {

    private Settings settings;
    private Map<Integer, Media> playList = new HashMap<>();
    private Integer currentSong;
    private int size = 0;
    private boolean isRepeate;

    private String currentMusicDirectory;

    public Integer getCurrentSong() {
        return currentSong;
    }

    public void setCurrentSong(Integer currentSong) {
        this.currentSong = currentSong;
    }

    @Autowired
    public PlayList(Settings settings) {
        System.out.println("Start PlayList");
        isRepeate = settings.isRepeate();
        setPlayList(settings.getMusicDirectory(), settings.getCurrentSong());
    }


    public Map<Integer, Media> getPlayList() {
        return playList;
    }


    public boolean setPlayList(String path, Integer currentSong) {
        Map<Integer, Media> newPlayList = new HashMap<>();
        int newSize = ServiceFiles.GetFileIntoDir(path, newPlayList, 0);
        if (newSize != 0) {
            this.currentSong = currentSong;
            currentMusicDirectory = path;
            playList=newPlayList;
            size = newSize;
            return true;
        }
        return false;
    }

    public boolean addPlayList(String path, Integer currentSong){
        Map<Integer, Media> newPlayList = new HashMap<>();
        int newSize = ServiceFiles.GetFileIntoDir(path, newPlayList, size);
        if (newSize != 0) {
            this.currentSong = currentSong;
            currentMusicDirectory = path;
            playList.putAll(newPlayList);
            size += newSize;
            return true;
        }
        return false;
    }

    public Media getSong() {
        return playList.get(currentSong);
    }

    public boolean isRepeat() {
        return isRepeate;
    }

    public void setRepeat(boolean repeat) {
        isRepeate = repeat;
        settings.setRepeate(repeat);
    }

    public Media nextSong() {
        System.out.println("currentSong " + currentSong);
        System.out.println("size " + size);

        if (currentSong.compareTo(size - 1) < 0) {
            System.out.println("currentSong " + currentSong + "<" + size);

            return playList.get(++currentSong);
        }
//        TODO продумать нужно ли иметь триггер на повторение
//        else if ((isRepeate) && (currentSong.compareTo(size-1)>=0)) {
        else if (currentSong.compareTo(size - 1) >= 0) {
            System.out.println("currentSong " + currentSong + ">=" + (size - 1));
            currentSong = 0;
            return playList.get(0);
        }
        return null;
    }

    public Media prevSong() {

        if (currentSong.compareTo(0) == 0) {
            System.out.println("currentSong " + currentSong + "<" + size);
            currentSong = size - 1;
            return playList.get(currentSong);
        }

        return playList.get(--currentSong);
    }

    public String getCurrentDir() {
        return currentMusicDirectory;
    }


    public void setCurrentMusicDirectory(String currentMusicDirectory) {
        this.currentMusicDirectory = currentMusicDirectory;
        settings.setMusicDirectory(currentMusicDirectory);
    }

    public int getSize() {
        return size;
    }
}
