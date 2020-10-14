package com.example.routes.Splash;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.routes.RoutesFragment;
import com.example.routes.appcomponents.NavigatorsTags;
import com.example.routes.architecture.MainViewModel;
import com.example.routes.databinding.SplashFragmentBinding;

public class SplashFragment extends RoutesFragment {
    SplashFragmentBinding binding;
    int timeOut = 2000;
    MainViewModel mainViewModel;

    public static SplashFragment newInstance(){return new SplashFragment();}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = SplashFragmentBinding.inflate(getLayoutInflater(),container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                mainViewModel.setNavigation(NavigatorsTags.LANDING_FRAGMENT);
            }
        },timeOut);
    }

}
