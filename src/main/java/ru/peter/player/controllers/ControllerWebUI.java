package ru.peter.player.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.peter.player.conf.Settings;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Controller
@RequestMapping("/")
public class ControllerWebUI {

    @Autowired
    Settings settings;
    @RequestMapping(method = RequestMethod.GET)
    public String printHello(ModelMap model) {
        System.out.println("controller");
//        String hostname,
        String serverAddress;
        String serverpPort = settings.getPort();
        String serverURL;

        serverURL = "error";
//        hostname = "error";
        serverAddress = "error";
        try {
            InetAddress inetAddress;
            inetAddress = InetAddress.getLocalHost();
//            hostname = inetAddress.getHostName();
            serverAddress = inetAddress.getHostAddress();
//            .toString();
            serverURL = "http://"+serverAddress+":"+serverpPort+"/";

        } catch (UnknownHostException e) {

            e.printStackTrace();
        }
        System.out.println(serverAddress);
        model.addAttribute("URL", serverURL);
        return "index";
    }
}
