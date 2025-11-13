package com.mjc813.gugudan_thymeleaf.gugudan;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/testdata")
public class GugudanController {
    @GetMapping("/gugudanparam")
    public String viewGugugdanParam(Model model, @RequestParam("num") Integer number) {
        List<String> array = new ArrayList<>();
        for( int i = 1; i <= 9; i++ ) {
            array.add(String.format("%d X %d = %d", number, i, number*i));
        }
        model.addAttribute("number", number);
        model.addAttribute("array", array);
        return "/gugudan/listview";
    }

    @GetMapping("/gugudanpath/{number}")
    public String viewGugudanPath(Model model, @PathVariable("number") Integer num) {
        List<String> array = new ArrayList<>();
        for( int i = 1; i <= 9; i++ ) {
            array.add(String.format("%d X %d = %d", num, i, num*i));
        }
        model.addAttribute("number", num);
        model.addAttribute("array", array);
        return "gugudan/listview";
    }

    @GetMapping("/gugudan")
    public String viewGugugdan(Model model, HttpServletRequest request) {
        String num = request.getParameter("num");
        Integer number = 0;
        try {
            number = Integer.parseInt(num);
        } catch (Exception e) {
            return "redirect:./gugudan?num=1";
        }
        List<String> array = new ArrayList<>();
        for( int i = 1; i <= 9; i++ ) {
            array.add(String.format("%d X %d = %d", number, i, number*i));
        }
        model.addAttribute("number", number);
        model.addAttribute("array", array);
        return "gugudan/listview";
    }
}
