package edu.vassar.cmpu203.campusdining.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import edu.vassar.cmpu203.campusdining.R;
import edu.vassar.cmpu203.campusdining.databinding.FragmentMenuDisplayBinding;
import edu.vassar.cmpu203.campusdining.model.FoodItem;
import edu.vassar.cmpu203.campusdining.model.FoodProperties;
import edu.vassar.cmpu203.campusdining.model.Menu;

public class MenuDisplayFragment extends Fragment {

    private FragmentMenuDisplayBinding binding;
    private IMenuDisplayView.Listener listener;
    Menu m;
    private static final String MENU = "menu";

    public MenuDisplayFragment(IMenuDisplayView.Listener listener) {
        this.listener = listener;
    }

    public static Bundle makeArgsBundle(Menu menu) {
        Bundle args = new Bundle();
        args.putSerializable(MenuDisplayFragment.MENU, menu);
        return args;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = this.getArguments();
        if (args != null) {
            this.m = (Menu) args.getSerializable(MENU);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = FragmentMenuDisplayBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        m = listener.getMenu();
        if (m != null) {
            String location = m.getLocation();
            this.binding.menuLocation.setText(location);
            String date = m.getDate();
            this.binding.menuDate.setText(date);
            int rateId = 1;
            for (String mLabel : m.getMealLabelNames()) {
                makeTextView(this.binding.menuLinearLayout, mLabel);
                for (String mStation : m.getMealLabel(mLabel).getStationNames()) {
                    makeTextView(this.binding.menuLinearLayout, mStation);
                    for (FoodItem item : m.getMealLabel(mLabel).getStation(mStation).getItems()) {
                        LinearLayout horizonMenu = new LinearLayout(this.binding.menuLinearLayout.getContext());
                        horizonMenu.setOrientation(LinearLayout.HORIZONTAL);
                        LinearLayout verticalMenu = new LinearLayout(this.binding.menuLinearLayout.getContext());
                        verticalMenu.setOrientation(LinearLayout.VERTICAL);
                        makeTextView(verticalMenu, item.toString());
                        horizonMenu.addView(verticalMenu);
                        Button rating = new Button(this.binding.menuLinearLayout.getContext());
                        rating.setId(rateId);
                        rateId++;
                        rating.setText(R.string.rate_button);
                        rating.setOnClickListener((View clickedView) -> {
                            listener.onRateItem(item);
                        });
                        horizonMenu.addView(rating);
                        String avgRating = getString(R.string.rating_label, item.computeRating());
                        makeTextView(horizonMenu, avgRating);
                        this.binding.menuLinearLayout.addView(horizonMenu);
                    }
                }
            }
            //Add the FoodProperties Key at the bottom of the page
            TextView keyView = new TextView(this.requireContext());
            String key = "\nFood key map:\n" + FoodProperties.getKey();
            keyView.setText(key);
            this.binding.menuLinearLayout.addView(keyView);
        } else {
            this.binding.validDateButUnavailable.setVisibility(View.VISIBLE);
        }
        this.binding.exitMenuButton.setOnClickListener((View clickedView) -> {
            listener.onExitMenu();
        });
    }

    private void makeTextView(LinearLayout l, String s) {
        TextView textView = new TextView(this.binding.menuLinearLayout.getContext());
        textView.setText(s);
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        l.addView(textView);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(MENU, this.m);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            this.m = (Menu) savedInstanceState.getSerializable(MENU);
        }
    }


}