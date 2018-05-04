package app.citta.utilities.GeneralizedModules.MainActivity.POJO;

/**
 * Created by ws-16 on 7/17/2017.
 */

public class mainActivityPOJO {
    String itemName;
    int position;

    public mainActivityPOJO(String itemName,int position) {
        this.itemName = itemName;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
