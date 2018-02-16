package com.ttpsc.otapowiczp.springApp.controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import com.ttpsc.otapowiczp.springApp.converters.YearConverter;
import com.ttpsc.otapowiczp.springApp.models.Library;
import com.ttpsc.otapowiczp.springApp.models.Movie;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MyController {
    private static final Logger logger = LogManager.getLogger(MyController.class);

    @RequestMapping("/")
    public String getData(Model model) {
        model.addAttribute("movieList", movieList());
        logger.error("home site launched");
        return "home";
    }




    @ModelAttribute("movieList")
    public List<Movie> movieList() {
        return Library.getInstance();
    }

    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public String newMovie(@ModelAttribute("movieFormData") Movie movie, BindingResult result) {
        logger.error(movie.getTitle() + " " + movie.getYear() + "   added");
        if (result.hasErrors() || movie.getYear() == null) {
            return "addMovie";
        } else {
            movieList().add(movie);
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/delete_movie", method = RequestMethod.GET)
    public String handleDeleteUser(@RequestParam(name = "movieId") int movieId) {
        logger.error(movieId + "\t" + "deleted");
        movieList().removeIf(x -> x.getId() == movieId);
        return "redirect:/";
    }

    @RequestMapping(value = "/xml_view", method = RequestMethod.GET)
    public String showXml(Model model) {
        model.addAttribute("xml_string", configureXstream().toXML(Library.getInstance()));
        logger.info("attempt to parse to xml");
        return "xml_view";
    }

    private static XStream configureXstream() {
        XStream xStream = new XStream();
        xStream.processAnnotations(new Class[]{Movie.class, Library.class});
        XStream.setupDefaultSecurity(xStream);
        xStream.registerConverter(new YearConverter());
        logger.trace("xStream configured");
        return xStream;
    }
}