package com.mjc813.sb_first;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/first")
    public String methodA1(Model md) {
        md.addAttribute("screenKey", "한길수");
        return "/test/first";
    }
}
