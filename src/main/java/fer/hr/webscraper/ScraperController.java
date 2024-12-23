package fer.hr.webscraper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
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

    @PostMapping(path = "/api")
    public Set<Item> getDataApi(@RequestBody String body) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> jsonMap = objectMapper.readValue(body, Map.class);

            String query = (String) jsonMap.get("query");
            List<Map<String, Object>> stores = (List<Map<String, Object>>) jsonMap.get("stores");

            System.out.println("Query: " + query);
            List<String> selectedStores = new ArrayList<>();
            for (Map<String, Object> store : stores) {
                System.out.println("Store Name: " + store.get("name"));
                if (store.get("checked").equals(true)) {
                    selectedStores.add((String) store.get("name"));
                }
            }
            return scraperService.getSelectedData(query, selectedStores);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @GetMapping(path = "/{searchQuery}")
    public Set<Item> getData(@PathVariable String searchQuery) {
        return scraperService.getData(searchQuery);
    }
}