package com.mjc813.springbootwebprj;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

    @GetMapping("/menu_song")
    public String menuSong() {
        return "song/song";
    }

}
