package fer.hr.webscraper;

public class Item {
    String title;
    String url;
    String pictureUrl;
    String price;
    String store;
    String state;
    boolean isDiscounted;
    String discount;
    String oldPrice;
    String availability;
    String additionalInfo;
    int group;

    public Item() {
    }
    public Item(String title, String url, String pictureUrl, String price, String store, String state, boolean isDiscounted, int group, String discount, String oldPrice, String availability, String additionalInfo) {
        this.title = title;
        this.url = url;
        this.pictureUrl = pictureUrl;
        this.price = price;
        this.store = store;
        this.state = state;
        this.isDiscounted = isDiscounted;
        this.group = group;
        this.discount = discount;
        this.oldPrice = oldPrice;
        this.availability = availability;
        this.additionalInfo = additionalInfo;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isDiscounted() {
        return isDiscounted;
    }

    public void setDiscounted(boolean discounted) {
        isDiscounted = discounted;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    @Override
    public String toString() {
        return "Item{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", price='" + price + '\'' +
                ", store='" + store + '\'' +
                ", state='" + state + '\'' +
                ", isDiscounted=" + isDiscounted +
                ", discount='" + discount + '\'' +
                ", oldPrice='" + oldPrice + '\'' +
                ", availability='" + availability + '\'' +
                ", additionalInfo='" + additionalInfo + '\'' +
                ", group=" + group +
                '}';
    }
}