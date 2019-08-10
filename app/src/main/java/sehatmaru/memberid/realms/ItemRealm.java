package sehatmaru.memberid.realms;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ItemRealm extends RealmObject {

    @PrimaryKey
    private String id;
    private String name;
    private String poin;
    private String type;
    private String gift;

    public ItemRealm() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGift() {
        return gift;
    }

    public void setGift(String gift) {
        this.gift = gift;
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

    @Override
    public String toString() {
        return "ItemRealm{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", poin='" + poin + '\'' +
                ", type='" + type + '\'' +
                ", gift='" + gift + '\'' +
                '}';
    }
}
