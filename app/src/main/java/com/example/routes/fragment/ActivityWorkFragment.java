package com.example.routes.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.routes.R;
import com.example.routes.architecture.FragmentHolderViewModel;
import com.example.routes.databinding.ActivityWorkFragmentBinding;

public class ActivityWorkFragment extends RoutesFragment {

    public static ActivityWorkFragment activityWorkFragment;
    ActivityWorkFragmentBinding binding;
    FragmentHolderViewModel fragmentHolderViewModel;
     public static ActivityWorkFragment newInstance()
     {
         if(activityWorkFragment == null)
         {
             activityWorkFragment = new ActivityWorkFragment();
         }
         return activityWorkFragment;
     }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentHolderViewModel = new ViewModelProvider(requireActivity()).get(FragmentHolderViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ActivityWorkFragmentBinding.inflate(getLayoutInflater(),container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button activeBtn = getActivity().findViewById(R.id.active_btn);
        activeBtn.setText("Activity");
    }
}
