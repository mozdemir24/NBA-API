package nba.stats.olle.controller;

import nba.stats.olle.dto.Request;
import nba.stats.olle.service.Service;
import nba.stats.olle.model.Stats;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Controller {

    private final Service service;

    public Controller(Service service) {
        this.service = service;
    }

    @GetMapping("/")
    public ModelAndView welcome(@ModelAttribute("request") Request request) {
        Map<String, Object> model = new HashMap<>();
        return new ModelAndView("welcome", model);
    }

    @PostMapping("/getStats")
    public ModelAndView getStats(@ModelAttribute("request") Request request) {
        Map<String, Object> model = new HashMap<>();
        List<Stats> stats = service.getDoubleDouble(request.getDate().replace("-", ""));
        model.put("stats", stats);
        return new ModelAndView("stats", model);
    }

}
