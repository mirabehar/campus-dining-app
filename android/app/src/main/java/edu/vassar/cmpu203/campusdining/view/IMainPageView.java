package edu.vassar.cmpu203.campusdining.view;


public interface IMainPageView {
    interface Listener{
        void onDeece();
        void onFoodTruck();
        void onRetreat();
        void onExpress();
        void onLogIn();
        void onProfile();
        void onOptions();
        boolean isLoggedIn();
        String getDate();
        void setDate(String s);
    }
    boolean checkValidDate(String date);
}
