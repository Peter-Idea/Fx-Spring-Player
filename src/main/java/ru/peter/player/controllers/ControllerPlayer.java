package ru.peter.player.controllers;

import org.springframework.web.bind.annotation.*;
import ru.peter.player.entity.Folder;
import ru.peter.player.entity.PlayList;
import ru.peter.player.servises.Player;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
public class ControllerPlayer {

    @Autowired
    Player player;

    @Autowired
    PlayList playList;


    @CrossOrigin
    @RequestMapping("/player")
    public PlayList commandToPlayer(@RequestParam(value = "command", required = false) String command) {
        System.out.println("Command = " + command);

        if (command.equals("play"))
            player.play();

        if (command.equals("stop"))
            player.stop();

        if (command.equals("next"))
            player.next();

        if (command.equals("prev"))
            player.prev();

        return playList;
    }

    @CrossOrigin
    @RequestMapping("/player/{number}")
    public PlayList playNumberSong(@PathVariable Integer number) {
        System.out.println(number);
        if (number != null) player.numberSongPlay(number);
        return playList;
    }

    @CrossOrigin
    @PostMapping("/player")
    public PlayList playDir(@RequestBody Folder folder) {
        System.out.println("плеер открыть папку" +folder.getPath()+" получаем UP"+folder.isUp());
        if (folder.getPath() != null) {
            player.playDir(folder.getPath());
        }
        return playList;
    }
}
