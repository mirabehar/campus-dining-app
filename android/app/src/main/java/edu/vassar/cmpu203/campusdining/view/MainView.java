package edu.vassar.cmpu203.campusdining.view;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import edu.vassar.cmpu203.campusdining.databinding.MainBinding;

public class MainView implements IMainView{

    FragmentActivity activity;
    MainBinding binding;

    public MainView(@NonNull FragmentActivity activity){
        this.activity = activity;
        this.binding = MainBinding.inflate(activity.getLayoutInflater());
    }

    @Override
    public View getRootView() {
        return this.binding.getRoot();
    }

    @Override
    public void displayFragment(Fragment fragment) {
        this.activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(this.binding.fragmentContainerView.getId(), fragment)
                .commitNow();
    }
}
