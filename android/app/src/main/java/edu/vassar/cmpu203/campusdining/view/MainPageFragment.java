package edu.vassar.cmpu203.campusdining.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import edu.vassar.cmpu203.campusdining.R;
import edu.vassar.cmpu203.campusdining.databinding.FragmentMainPageBinding;


public class MainPageFragment extends Fragment implements IMainPageView {
    FragmentMainPageBinding binding;
    Listener listener;
    String mainDate;
    private static final String MAIN_DATE = "mainDate";

    public static Bundle makeArgsBundle(String mainDate) {
        Bundle args = new Bundle();
        args.putString(MainPageFragment.MAIN_DATE, mainDate);
        return args;
    }

    public MainPageFragment(Listener listener) {
        this.listener = listener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = this.getArguments();
        if (args != null) {
            this.mainDate = args.getString(MAIN_DATE);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentMainPageBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        mainDate = this.listener.getDate(); // not needed anymore?
        this.binding.menuDateSelect.setText(mainDate);
        this.binding.setDateButton.setOnClickListener((View clickedView) -> {
            mainDate = this.binding.menuDateSelect.getText().toString();
            this.listener.setDate(mainDate);
        });

        if (this.listener.isLoggedIn()) {
            this.binding.profileButton.setText(R.string.profile_button_logged_in);
        } else {
            this.binding.profileButton.setText(R.string.profile_button_default);
        }

        this.binding.deeceButton.setOnClickListener((View clickedView) -> {
            if (checkValidDate(this.listener.getDate())) {
                Snackbar.make(clickedView, "Please wait while your menu is retrieved", Snackbar.LENGTH_LONG).show();
                listener.onDeece();
            } else {
                Snackbar.make(clickedView, "Please select a date between yesterday and 6 days from today's date (format yyyy-mm-dd)", Snackbar.LENGTH_LONG).show();
            }
        });

        this.binding.foodTruckButton.setOnClickListener((View clickedView) -> {
            if (checkValidDate(this.listener.getDate())) {
                Snackbar.make(clickedView, "Please wait while your menu is retrieved", Snackbar.LENGTH_LONG).show();
                listener.onFoodTruck();
            } else {
                Snackbar.make(clickedView, "Please select a date between yesterday and 6 days from today's date (format yyyy-mm-dd)", Snackbar.LENGTH_LONG).show();
            }
        });
        this.binding.retreatButton.setOnClickListener((View clickedView) -> {
            if (checkValidDate(this.listener.getDate())) {
                Snackbar.make(clickedView, "Please wait while your menu is retrieved", Snackbar.LENGTH_LONG).show();
                listener.onRetreat();
            } else {
                Snackbar.make(clickedView, "Please select a date between yesterday and 6 days from today's date (format yyyy-mm-dd)", Snackbar.LENGTH_LONG).show();
            }
        });
        this.binding.expressButton.setOnClickListener((View clickedView) -> {
            if (checkValidDate(this.listener.getDate())) {
                Snackbar.make(clickedView, "Please wait while your menu is retrieved", Snackbar.LENGTH_LONG).show();
                listener.onExpress();
            } else {
                Snackbar.make(clickedView, "Please select a date between yesterday and 6 days from today's date (format yyyy-mm-dd)", Snackbar.LENGTH_LONG).show();
            }
        });

        this.binding.optionsButton.setOnClickListener((View clickedView) -> {
            Snackbar.make(clickedView, "No options currently available", Snackbar.LENGTH_LONG).show();
            listener.onOptions(); //currently does nothing
        });
        this.binding.profileButton.setOnClickListener((View clickedView) -> {
            if (this.listener.isLoggedIn()) {
                listener.onProfile();
            } else {
                listener.onLogIn();
            }
        });
    }

    @Override
    public boolean checkValidDate(String date) {
        // Note: normally below line is correct and the first line of the try should be removed,
        // but since we only have access to certain menus from the API we don't actually want "today"
        // to be today

        // LocalDate currentDate = java.time.LocalDate.now();
        try {
            LocalDate currentDate = LocalDate.parse("2021-10-09");
            //checks for yyyy-mm-dd format
            LocalDate inDate = LocalDate.parse(date);
            LocalDate startRange;
            LocalDate endRange;
            //makes range of valid dates (one day before to 6 days after currentDate)
            startRange = currentDate.plusDays(-1);
            endRange = currentDate.plusDays(6);
            return !inDate.isBefore(startRange) && !inDate.isAfter(endRange);
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(MAIN_DATE, this.mainDate);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState != null) {
            this.mainDate = savedInstanceState.getString(MAIN_DATE);
        }
    }
}