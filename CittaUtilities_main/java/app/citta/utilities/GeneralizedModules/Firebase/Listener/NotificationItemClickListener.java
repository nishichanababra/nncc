package app.citta.utilities.GeneralizedModules.Firebase.Listener;

/**
 * Created by ws-16 on 7/20/2017.
 */

public interface NotificationItemClickListener {
    void notificationItemClicked(String id, String fbToken, String fbData, String fbTime, String fbStatus,int position);
}
