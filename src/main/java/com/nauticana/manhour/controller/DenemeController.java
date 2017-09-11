package com.nauticana.manhour.controller;


import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DenemeController {


    /* this is the conroller's part of the magic; I'm just using a simple GET but you
     * could just as easily do a POST here, obviously
     */
    @RequestMapping( method=RequestMethod.GET, value="/subView" )
    public ModelAndView getSubView( Model model ) {
        model.addAttribute( "user", "Joe Dirt" );
        model.addAttribute( "time", new Date() );
        return new ModelAndView( "subView" );
    }
}
