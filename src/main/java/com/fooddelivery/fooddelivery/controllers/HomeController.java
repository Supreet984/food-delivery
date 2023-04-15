package com.fooddelivery.fooddelivery.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * HomeController
 * - Serve the index.html file
 */
@Controller
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class HomeController {
    @RequestMapping("/")
    public String index() {
        return "redirect:swagger-ui/index.html";
    }
}
