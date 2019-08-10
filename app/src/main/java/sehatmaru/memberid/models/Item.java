package sehatmaru.memberid.models;

public class Item {

    private String id;
    private String name;
    private String poin;
    private String type;
    private String gift;

    public Item() {
    }

    public Item(String id, String name, String poin, String type, String gift) {
        this.id = id;
        this.name = name;
        this.poin = poin;
        this.type = type;
        this.gift = gift;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoin() {
        return poin;
    }

    public void setPoin(String poin) {
        this.poin = poin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGift() {
        return gift;
    }

    public void setGift(String gift) {
        this.gift = gift;
    }
}
