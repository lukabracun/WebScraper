package fer.hr.webscraper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
    private final CentarTehnikeData hgSpotData;
    private final RonisData ronisData;
    private final EkupiData mallData;
    public ScraperServiceImpl() {
        this.instarData = new InstarData();
        this.linksData = new LinksData();
        this.sanctaData = new SanctaData();
        this.mikronisData = new MikronisData();
        this.hgSpotData = new CentarTehnikeData();
        this.ronisData = new RonisData();
        this.mallData = new EkupiData();
    }

    public Set<Item> getData(String query) {
        Set<Item> items = new HashSet<>();
        for (String url : urls) {
            if (url.contains("instar-informatika")) {
                instarData.extractDataFromInstar(items, url + query);
            } else if (url.contains("links.hr")) {
                linksData.extractDataFromLinks(items, url + query);
            } else if (url.contains("sancta-domenica")) {
                sanctaData.extractDataFromSanctaDomenica(items, url + query);
            } else if (url.contains("mikronis")) {
                mikronisData.extractDataFromMikronis(items, url + query);
            } else if (url.contains("centar-tehnike")) {
                hgSpotData.extractDataFromCentarTehnike(items, url + query);
            } else if (url.contains("ronis")) {
                ronisData.extractDataFromRonis(items, url + query);
            } else if (url.contains("ekupi")) {
                mallData.extractDataFromEkupi(items, url + query);
            }
        }
        return items;
    }

}