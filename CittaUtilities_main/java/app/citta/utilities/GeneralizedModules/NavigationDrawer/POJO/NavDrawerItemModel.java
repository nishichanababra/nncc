package app.citta.utilities.GeneralizedModules.NavigationDrawer.POJO;

public class NavDrawerItemModel {
    private boolean showNotify;
    private String title;


    public NavDrawerItemModel() {

    }

    public NavDrawerItemModel(boolean showNotify, String title) {
        this.showNotify = showNotify;
        this.title = title;
    }

    public boolean isShowNotify() {
        return showNotify;
    }

    public void setShowNotify(boolean showNotify) {
        this.showNotify = showNotify;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}