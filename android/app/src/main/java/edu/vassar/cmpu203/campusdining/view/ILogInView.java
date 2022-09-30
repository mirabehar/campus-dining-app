package edu.vassar.cmpu203.campusdining.view;

import edu.vassar.cmpu203.campusdining.model.Profile;

public interface ILogInView {
    interface Listener{
        void onProfile();
        Profile getProfile();
        void retrieveProfile(String username);
        void onBackToMain();
        void onCreateProfile(String name, String username, String password);
        boolean usernameUnavailable(String username);
        void setCurPassword(String logInPass);
    }
    boolean passwordsMatch();
    void displayCreatingMode();
}
