package ru.peter.player.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.peter.player.entity.Folder;
import ru.peter.player.entity.SendFolder;
import ru.peter.player.servises.ServiceFiles;

@RestController
public class ControllerFS {

    @Autowired
    ServiceFiles serviceFiles;
    @Autowired
    Folder folder;

    @CrossOrigin
    @PostMapping("/opendir")
    public SendFolder openDir(@RequestBody Folder folder) {
        System.out.println(folder);
        System.out.println(folder.getPath());
        System.out.println(folder.isUp());
        return serviceFiles.openDir(folder);
    }
}
