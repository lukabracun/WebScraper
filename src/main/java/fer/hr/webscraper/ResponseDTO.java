package fer.hr.webscraper;

public class ResponseDTO {
    String title;
    String url;
    String pictureUrl;
    String price;
    String store;

    public ResponseDTO() {
    }
    public ResponseDTO(String title, String url, String pictureUrl, String price, String store) {
        this.title = title;
        this.url = url;
        this.pictureUrl = pictureUrl;
        this.price = price;
        this.store = store;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }
}