package edu.vassar.cmpu203.campusdining.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import edu.vassar.cmpu203.campusdining.R;
import edu.vassar.cmpu203.campusdining.databinding.FragmentProfileDisplayBinding;
import edu.vassar.cmpu203.campusdining.model.FoodProperties;

public class ProfileDisplayFragment extends Fragment implements IProfileDisplayView {
    FragmentProfileDisplayBinding binding;
    Listener listener;
    boolean passHidden = true; // represents password hidden or shown
    boolean editMode = false; // represents when in edit mode
    String previousUsername;
    private static final String PASS_HIDDEN = "passHidden";
    private static final String EDIT_MODE = "editMode";
    private static final String PREV_UNAME = "previousUsername";

    public ProfileDisplayFragment(Listener listener) {
        this.listener = listener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle args = this.getArguments();
        if(args != null){
            this.passHidden = args.getBoolean(PASS_HIDDEN);
            this.editMode = args.getBoolean(EDIT_MODE);
            this.previousUsername = args.getString(PREV_UNAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentProfileDisplayBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    private void setClickable(boolean bool) { // happens when entering and leaving edit mode
        this.binding.profileName.setEnabled(bool);
        this.binding.profileUsername.setEnabled(bool);
        this.binding.profilePassword.setEnabled(bool);
        this.binding.vegetarianSwitch.setClickable(bool);
        this.binding.veganSwitch.setClickable(bool);
        this.binding.glutenFreeSwitch.setClickable(bool);
        this.binding.halalSwitch.setClickable(bool);
        this.binding.kosherSwitch.setClickable(bool);
        this.binding.humaneSwitch.setClickable(bool);
        this.binding.seafoodSwitch.setClickable(bool);
        this.binding.farmToForkSwitch.setClickable(bool);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        this.listener.updateProfileDetails(this);

        //when show password button is clicked
        this.binding.showPasswordButton.setOnClickListener((clickedView) -> {
            if (this.passHidden) {
                //if option is "show"
                displayShownPass();
                this.passHidden = false;
            } else {
                //if option is "hide"
                this.binding.profilePassword.setTransformationMethod(new PasswordTransformationMethod());
                this.passHidden = true;
                this.binding.showPasswordButton.setText(R.string.show_password_button);
            }
        });

        //when edit profile button is clicked
        this.binding.editProfileButton.setOnClickListener((clickedView) -> {
            if(this.editMode) {
                //if option selected is "done"
                String name = this.binding.profileName.getText().toString();
                String username = this.binding.profileUsername.getText().toString();
                String password = this.binding.profilePassword.getText().toString();
                if(name.equals("")){
                    Snackbar.make(clickedView, "Please enter a name", Snackbar.LENGTH_LONG).show();
                }
                else if(username.equals("")){
                    Snackbar.make(clickedView, "Please enter a username", Snackbar.LENGTH_LONG).show();
                }
                else if (!username.equals(this.previousUsername) && this.listener.usernameUnavailable(username)) {
                    Snackbar.make(clickedView, "Username is already taken, please enter a unique username", Snackbar.LENGTH_LONG).show();
                }
                else if(password.equals("")){
                    Snackbar.make(clickedView, "Please enter a password", Snackbar.LENGTH_LONG).show();
                }
                else{
                    //stops everything from being editable
                    this.binding.editProfileButton.setText(R.string.edit_profile_button);
                    this.editMode = false;
                    this.setClickable(false);
                    // edits to give to constructor
                    FoodProperties fp = new FoodProperties
                            (this.binding.vegetarianSwitch.isChecked(),
                                    this.binding.veganSwitch.isChecked(),
                                    this.binding.glutenFreeSwitch.isChecked(),
                                    this.binding.halalSwitch.isChecked(),
                                    this.binding.kosherSwitch.isChecked(),
                                    this.binding.humaneSwitch.isChecked(),
                                    this.binding.seafoodSwitch.isChecked(),
                                    this.binding.farmToForkSwitch.isChecked());
                    this.listener.onSaveEdits(name, username, password, fp);
                    if(!username.equals(this.previousUsername)) {
                        this.listener.removeOldProfile(previousUsername);
                    }
                    this.previousUsername = username;
                }
            }
            else {
                //if option selected is "edit"
                //makes everything editable
                this.previousUsername = this.binding.profileUsername.getText().toString();
                displayEditMode();
                this.editMode = true;
            }
        });

        //when back button is clicked
        this.binding.leaveProfileButton.setOnClickListener((View clickedView) -> {
            this.listener.onExitProfile();
        });

        this.binding.logOutButton.setOnClickListener((View clickedView) -> {
            this.listener.onLogOut();
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(PASS_HIDDEN, this.passHidden);
        outState.putBoolean(EDIT_MODE, this.editMode);
        outState.putString(PREV_UNAME, this.previousUsername);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState){
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState != null){
            this.passHidden = savedInstanceState.getBoolean(PASS_HIDDEN);
            this.editMode = savedInstanceState.getBoolean(EDIT_MODE);
            this.previousUsername = savedInstanceState.getString(PREV_UNAME);
        }
        if(this.editMode){
            displayEditMode();
        }
        if(!this.passHidden){
            displayShownPass();
        }
    }

    @Override
    public void displayEditMode(){
        this.binding.editProfileButton.setText(R.string.done_editing_profile);
        this.setClickable(true);
    }

    @Override
    public void displayShownPass(){
        this.binding.profilePassword.setTransformationMethod(null);
        this.binding.showPasswordButton.setText(R.string.hide_password_button);
    }

    @Override
    public void updateDisplay(String name, String username, String password, boolean vegetarian, boolean vegan, boolean glutenFree, boolean halal, boolean kosher, boolean humane, boolean seafood, boolean farmToFork) {
        this.binding.profileName.setText(name);
        this.binding.profileUsername.setText(username);
        this.binding.profilePassword.setText(password);
        this.binding.vegetarianSwitch.setChecked(vegetarian);
        this.binding.veganSwitch.setChecked(vegan);
        this.binding.glutenFreeSwitch.setChecked(glutenFree);
        this.binding.halalSwitch.setChecked(halal);
        this.binding.kosherSwitch.setChecked(kosher);
        this.binding.humaneSwitch.setChecked(humane);
        this.binding.seafoodSwitch.setChecked(seafood);
        this.binding.farmToForkSwitch.setChecked(farmToFork);
    }
}