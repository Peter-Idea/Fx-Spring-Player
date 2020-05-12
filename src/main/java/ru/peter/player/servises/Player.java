package ru.peter.player.servises;

import ru.peter.player.conf.Settings;
import ru.peter.player.entity.PlayList;
import javafx.scene.media.MediaPlayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class Player {

    private PlayList playList;
    private MediaPlayer player;

    @Autowired
    public Player(PlayList playList) {
        this.playList = playList;
        System.out.println("Player constructor");
        player = new MediaPlayer(this.playList.getSong());
        play();
    }

    public void play() {
        System.out.println("Player play");
        player.play();
        player.setOnEndOfMedia(() -> {
            next();
        });
    }


    public void numberSongPlay(Integer number) {
        playList.setCurrentSong(number);
        player.stop();
        player = new MediaPlayer(playList.getSong());
        play();
    }

    public void stop() {
        player.stop();
    }

    public void next() {
        player.stop();
        player = new MediaPlayer(playList.nextSong());
        play();
    }

    public void prev() {
        player.stop();
        player = new MediaPlayer(playList.prevSong());
        play();
    }

    public MediaPlayer info() {
        return player;
    }

    public boolean playDir(String path) {
        System.out.println("плеер останавливает проигрывание");
        stop();
        System.out.println("Загружаем в playList");
        if (playList.setPlayList(path, 0)) return false;
        play();
        return true;
    }
}
