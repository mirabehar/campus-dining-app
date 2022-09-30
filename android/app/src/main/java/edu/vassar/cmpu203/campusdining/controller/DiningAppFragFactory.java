package edu.vassar.cmpu203.campusdining.controller;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;

import edu.vassar.cmpu203.campusdining.view.LogInFragment;
import edu.vassar.cmpu203.campusdining.view.MainPageFragment;
import edu.vassar.cmpu203.campusdining.view.MenuDisplayFragment;
import edu.vassar.cmpu203.campusdining.view.ProfileDisplayFragment;
import edu.vassar.cmpu203.campusdining.view.RateItemFragment;

public class DiningAppFragFactory extends FragmentFactory {
    private ControllerActivity controller;

    DiningAppFragFactory(ControllerActivity controller){
        this.controller = controller;
    }

    @NonNull
    @Override
    public Fragment instantiate(@NonNull ClassLoader classLoader, @NonNull String className) {
        Class<? extends Fragment> fragmentClass = loadFragmentClass(classLoader, className);

        Fragment fragment;
        if(fragmentClass == MainPageFragment.class){
            fragment = new MainPageFragment(controller);
        }
        else if(fragmentClass == LogInFragment.class){
            fragment = new LogInFragment(controller);
        }
        else if(fragmentClass == ProfileDisplayFragment.class){
            fragment = new ProfileDisplayFragment(controller);
        }
        else if(fragmentClass == MenuDisplayFragment.class){
            fragment = new MenuDisplayFragment(controller);
        }
        else if(fragmentClass == RateItemFragment.class){
            fragment = new RateItemFragment(controller);
        }
        else fragment = super.instantiate(classLoader, className);

        return fragment;
    }
}
