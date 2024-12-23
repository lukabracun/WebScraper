package fer.hr.webscraper;

public class Item {
    String title;
    String url;
    String imageUrl;
    String price;
    String storeName;
    String state;
    boolean isDiscounted;
    String discount;
    String oldPrice;
    String additionalInfo;
    int groupId = 1;

    public Item() {
    }
    public Item(String title, String url, String imageUrl, String price, String store, String state, boolean isDiscounted, int groupId, String discount, String oldPrice, String additionalInfo) {
        this.title = title;
        this.url = url;
        this.imageUrl = imageUrl;
        this.price = price;
        this.storeName = store;
        this.state = state;
        this.isDiscounted = isDiscounted;
        this.groupId = groupId;
        this.discount = discount;
        this.oldPrice = oldPrice;
        this.additionalInfo = additionalInfo;
    }

    public String getName() {
        return title;
    }
    public void setName(String title) {
        this.title = title;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String store) {
        this.storeName = store;
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

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
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
                ", imageUrl='" + imageUrl + '\'' +
                ", price='" + price + '\'' +
                ", store='" + storeName + '\'' +
                ", state='" + state + '\'' +
                ", isDiscounted=" + isDiscounted +
                ", discount='" + discount + '\'' +
                ", oldPrice='" + oldPrice + '\'' +
                ", additionalInfo='" + additionalInfo + '\'' +
                ", groupId=" + groupId +
                '}';
    }
}