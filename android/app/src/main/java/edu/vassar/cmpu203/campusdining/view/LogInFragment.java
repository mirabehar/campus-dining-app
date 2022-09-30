package edu.vassar.cmpu203.campusdining.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import edu.vassar.cmpu203.campusdining.R;
import edu.vassar.cmpu203.campusdining.databinding.FragmentLogInBinding;

public class LogInFragment extends Fragment implements ILogInView {
    FragmentLogInBinding binding;
    Listener listener;
    boolean creating = true; //become false when ready to submit creation
    public static final String CREATING = "creating";

    public LogInFragment(Listener listener) {
        this.listener = listener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = this.getArguments();
        if (args != null){
            this.creating = args.getBoolean(CREATING);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentLogInBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.binding.loginButton.setOnClickListener((View clickedView) -> {
            if (this.binding.loginUsername.getText() != null && this.binding.loginPassword.getText() != null) {
                listener.retrieveProfile(this.binding.loginUsername.getText().toString());
                if (listener.getProfile() != null) {
                    if(this.listener.getProfile().validatePassword(this.binding.loginPassword.getText().toString())){
                        listener.setCurPassword(this.binding.loginPassword.getText().toString());
                        listener.onProfile();
                    }
                    else{
                        Snackbar.make(clickedView, "Password does not match account, please enter correct username or password", Snackbar.LENGTH_LONG).show();
                        this.binding.loginPassword.getText().clear();
                    }
                } else {
                    Snackbar.make(clickedView, "Account not found, please try again", Snackbar.LENGTH_LONG).show();
                }
            } else {
                Snackbar.make(clickedView, "Please enter your username and password to log in", Snackbar.LENGTH_LONG).show();
            }
        });
        this.binding.backButton.setOnClickListener((View clickedView) -> {
            listener.onBackToMain();
        });
        this.binding.createProfileButton.setOnClickListener((View clickedView) -> {
            if (creating) {
                displayCreatingMode();
                creating = false;
            } else {
                String name = this.binding.loginName.getText().toString();
                String username = this.binding.loginUsername.getText().toString();
                String password = this.binding.loginPassword.getText().toString();
                if (name.equals("")) {
                    Snackbar.make(clickedView, "Please provide a name for your account", Snackbar.LENGTH_LONG).show();
                } else if (username.equals("")) {
                    Snackbar.make(clickedView, "Please provide a username for your account", Snackbar.LENGTH_LONG).show();
                } else if(this.listener.usernameUnavailable(username)){
                    Snackbar.make(clickedView, "Username already taken, please enter a unique username", Snackbar.LENGTH_LONG).show();
                }
                else if (password.equals("")) {
                    Snackbar.make(clickedView, "Please provide a password for your account", Snackbar.LENGTH_LONG).show();
                } else if (passwordsMatch()) {
                    listener.onCreateProfile(name, username, password);
                    listener.onProfile();
                } else {
                    Snackbar.make(clickedView, "Passwords do not match, try again", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean passwordsMatch() {
        return this.binding.loginPassword.getText().toString().equals(this.binding.secondLoginPassword.getText().toString());
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(CREATING, this.creating);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState){
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState != null){
            this.creating = savedInstanceState.getBoolean(CREATING);
        }
        if(!this.creating){ // they've already started the process of creating
            displayCreatingMode();
        }
    }

    @Override
    public void displayCreatingMode(){
        this.binding.loginNameLabel.setVisibility(View.VISIBLE);
        this.binding.loginName.setVisibility(View.VISIBLE);
        this.binding.secondPasswordLabel.setVisibility(View.VISIBLE);
        this.binding.secondLoginPassword.setVisibility(View.VISIBLE);
        this.binding.loginButton.setVisibility(View.GONE);
        this.binding.createProfileButton.setText(R.string.done_creating_button);
    }
}