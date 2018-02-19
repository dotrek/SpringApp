package com.ttpsc.otapowiczp.springApp.controllers;

import com.oracle.tools.packager.Log;
import com.thoughtworks.xstream.XStream;
import com.ttpsc.otapowiczp.springApp.converters.YearConverter;
import com.ttpsc.otapowiczp.springApp.models.Library;
import com.ttpsc.otapowiczp.springApp.models.Movie;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class XmlController {
    private static final Logger logger = LogManager.getLogger(XmlController.class);

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
