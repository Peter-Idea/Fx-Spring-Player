package ru.peter.player.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Controller
@RequestMapping("/")
public class ControllerWebUI {

    @RequestMapping(method = RequestMethod.GET)
    public String printHello(ModelMap model) {
        System.out.println("controller");
        String hostname, serverAddress;
        hostname = "error";
        serverAddress = "error";
        try {
            InetAddress inetAddress;
            inetAddress = InetAddress.getLocalHost();
            hostname = inetAddress.getHostName();
            serverAddress = inetAddress.getHostAddress().toString();
        } catch (UnknownHostException e) {

            e.printStackTrace();
        }
        System.out.println(serverAddress);
        model.addAttribute("IP", serverAddress);
        return "index";
    }
}
