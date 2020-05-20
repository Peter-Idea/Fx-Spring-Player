package ru.peter.player.servises;

import javafx.scene.media.Media;
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
        play(this.playList.getSong());
    }

    public void play() {
        System.out.println("Player play");
        if (player!=null){
        player.play();
        player.setOnEndOfMedia(() -> next());
        }
    }

    public boolean play(Media media) {

        if (media != null) {
            player = new MediaPlayer(media);
            play();
            System.out.println("Player play song ");
            return true;
        }
        System.out.println("Player not play song ");
        return false;
    }


    public void numberSongPlay(Integer number) {
        playList.setCurrentSong(number);
        player.stop();
        play(playList.getSong());
    }

    public boolean stop() {
        if (player != null) {
            player.stop();
            return true;
        }
        return false;
    }

    public void pause() {
        player.pause();
    }


    public void next() {
        stop();
        play(playList.nextSong());
    }

    public void prev() {
        stop();
        play(playList.prevSong());
    }

    public MediaPlayer info() {
        return player;
    }

    public boolean playDir(String path) {
        System.out.println("Загружаем в playList");
        if (playList.setPlayList(path, 0)) return false;
        else stop();
        play();
        return true;
    }
}
