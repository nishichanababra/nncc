package app.citta.utilities.GeneralizedModules.Firebase.POJO;

/**
 * Created by ws-16 on 7/19/2017.
 */

public class FirebaseNotificationsPOJO {

    private String id,fbToken,fbData,fbTime,fbStatus;

    public FirebaseNotificationsPOJO(String id, String fbToken, String fbData, String fbTime, String fbStatus) {
        this.id = id;
        this.fbToken = fbToken;
        this.fbData = fbData;
        this.fbTime = fbTime;
        this.fbStatus = fbStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFbToken() {
        return fbToken;
    }

    public void setFbToken(String fbToken) {
        this.fbToken = fbToken;
    }

    public String getFbData() {
        return fbData;
    }

    public void setFbData(String fbData) {
        this.fbData = fbData;
    }

    public String getFbTime() {
        return fbTime;
    }

    public void setFbTime(String fbTime) {
        this.fbTime = fbTime;
    }

    public String getFbStatus() {
        return fbStatus;
    }

    public void setFbStatus(String fbStatus) {
        this.fbStatus = fbStatus;
    }
}
