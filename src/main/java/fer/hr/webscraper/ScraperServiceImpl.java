package fer.hr.webscraper;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ScraperServiceImpl {
    @Value("#{'${website.urls}'.split(',')}")
    List<String> urls;
    private final InstarData instarData;
    private final LinksData linksData;
    private final SanctaData sanctaData;
    private final MikronisData mikronisData;

    public ScraperServiceImpl() {
        this.instarData = new InstarData();
        this.linksData = new LinksData();
        this.sanctaData = new SanctaData();
        this.mikronisData = new MikronisData();
    }

    public Set<Item> getData(String query) {
        Set<Item> items = new HashSet<>();
        for (String url: urls) {
            if (url.contains("instar-informatika")) {
                instarData.extractDataFromInstar(items, url + query);
            } else if (url.contains("links.hr")) {
                linksData.extractDataFromLinks(items, url + query);
            } else if (url.contains("sancta-domenica")) {
                sanctaData.extractDataFromSanctaDomenica(items, url + query);
            } else if (url.contains("mikronis")) {
                mikronisData.extractDataFromMikronis(items, url + query);
            }
        }
        return items;
    }

}