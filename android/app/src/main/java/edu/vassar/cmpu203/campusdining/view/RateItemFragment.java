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
import edu.vassar.cmpu203.campusdining.databinding.FragmentRateItemBinding;
import edu.vassar.cmpu203.campusdining.model.FoodItem;

public class RateItemFragment extends Fragment implements IRateItemView{

    private FragmentRateItemBinding binding;
    private Listener listener;
    private FoodItem foodItem;
    public static final String FOOD_IT = "foodItem";

    public RateItemFragment(IRateItemView.Listener listener) {
        this.listener = listener;
    }

    public static Bundle makeArgsBundle(FoodItem foodItem){
        Bundle args = new Bundle();
        args.putSerializable(FOOD_IT, foodItem);
        return args;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle args = this.getArguments();
        if(args != null){
            this.foodItem = (FoodItem) args.getSerializable(FOOD_IT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = FragmentRateItemBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.binding.itemTextView.setText(foodItem.toString());
        String avgRating = getString(R.string.rating_label, foodItem.computeRating());
        this.binding.rateTextView.setText(avgRating);
        this.binding.returnButton.setOnClickListener((View clickedView) -> {
            listener.onBackToMenu();
        });
        this.binding.submitRatingButton.setOnClickListener((View clickedView) -> {
            int rating = (int) this.binding.ratingBar2.getRating();
            listener.onSaveRating(foodItem, rating);
            Snackbar.make(clickedView, "Thank you for your feedback", Snackbar.LENGTH_LONG).show();
            listener.onBackToMenu();
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putSerializable(FOOD_IT, this.foodItem);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState){
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState != null){
            this.foodItem = (FoodItem) savedInstanceState.getSerializable(FOOD_IT);
        }
    }
}