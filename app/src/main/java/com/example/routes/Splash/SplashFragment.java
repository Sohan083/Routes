package com.example.routes.Splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.routes.R;
import com.example.routes.fragment.RoutesFragment;
import com.example.routes.appcomponents.NavigatorsTags;
import com.example.routes.architecture.MainViewModel;
import com.example.routes.databinding.SplashFragmentBinding;
import com.example.routes.ui.LoginActivity;

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


        ImageView iv = view.findViewById(R.id.iv);

        Animation animation = new AlphaAnimation(1, (float) 0.70); //to change visibility from visible to invisible
        animation.setDuration(2000); //1 second duration for each animation cycle
        animation.setInterpolator(new LinearInterpolator());
        iv.startAnimation(animation); //to start animation

        final Intent i = new Intent(getContext(), LoginActivity.class);
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(i);
                    getActivity().finish();
                }
            }
        };
        timer.start();
    }


}
