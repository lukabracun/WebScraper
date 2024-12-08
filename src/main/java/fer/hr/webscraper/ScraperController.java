package fer.hr.webscraper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping(path = "/")
public class ScraperController {

    @Autowired
    ScraperServiceImpl scraperService;

    @GetMapping(path = "/")
    public String home() {
        return "index";
    }

    @GetMapping(path = "/html/{searchQuery}")
    public String getDataHtml(@PathVariable String searchQuery, Model model) {
        Set<Item> results = scraperService.getData(searchQuery);
        model.addAttribute("results", results);
        return "searchResults";
    }

    @GetMapping(path = "/{searchQuery}")
    public Set<Item> getData(@PathVariable String searchQuery, @RequestBody String body) {
        return scraperService.getData(searchQuery);
    }
}