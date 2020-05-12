package ru.peter.player.servises;

import javafx.scene.media.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.peter.player.conf.Settings;
import ru.peter.player.entity.Folder;
import ru.peter.player.entity.SendFolder;
import java.io.File;
import java.io.FilenameFilter;
import java.net.MalformedURLException;
import java.util.Map;

@Service
public class ServiceFiles {

    @Autowired
    Settings settings;

    public static int GetFileIntoDir(String path, Map<Integer, Media> playList, int size) {
        System.out.println("path 2 " + path);
        File dir = new File(path);
//      TODO подумать над фильтром поддерживаемых файлов
        FilenameFilter mp3Filter = new FilenameFilter() {
            public boolean accept(File file, String name) {
                if (name.endsWith(".mp3")) {
                    // filters files whose extension is .mp3
                    return true;
                } else {
                    return false;
                }
            }
        };

        try {
            if (dir.isDirectory()) {
                // получаем все вложенные объекты в каталоге
//                System.out.println("получаем все вложенные объекты в каталоге");
                for (File file : dir.listFiles(mp3Filter)) {
                    System.out.println(file.toURI().toURL().toString());
                    if (file.isFile()) {
                        System.out.println(file.toURI().toURL().toString());
                        playList.put(size++, new Media(file.toURI().toURL().toString()));
                    }
                }
            } else {
                playList.put(size++, new Media(dir.toURI().toURL().toString()));
            }

        } catch (
                MalformedURLException e) {
            e.printStackTrace();
        } finally {
            return size;
        }
    }

    public SendFolder openDir(Folder folder) {
        File file;
//      TODO придумать нормальный путь есть folder.getPath()==null
        if (folder.getPath()==null) {file = new File(settings.getMusicDirectory());}
         else{
        if (folder.isUp()) {
            file = new File(folder.getPath()).getParentFile();
            System.out.println(new File(folder.getPath()).getParentFile());
        } else {
            file = new File(folder.getPath());
        }}

        SendFolder sendFolder = new SendFolder();
        sendFolder.setFile(file);
        sendFolder.setListFile(file.listFiles());
        return sendFolder;
    }
}
