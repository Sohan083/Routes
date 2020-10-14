package com.example.routes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.example.routes.Splash.SplashFragment;
import com.example.routes.appcomponents.NavigatorsTags;
import com.example.routes.appcomponents.RoutesFragmentManager;
import com.example.routes.architecture.MainViewModel;
import com.example.routes.databinding.ActivityMainBinding;
import com.example.routes.ui.login.LoginFragment;

public class MainActivity extends AppCompatActivity {

    SplashFragment splashFragment;
    LoginFragment loginFragment;
    ActivityMainBinding binding;
    RoutesFragmentManager routesFragmentManager;
    MainViewModel mainViewModel;
    Observer<String> navigationObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();
        setContentView(root);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        navigationObserver = new Observer<String>() {
            @Override
            public void onChanged(String tag) {
                navigateTo(tag);
            }
        };

        mainViewModel.getNavigation().observe(this,navigationObserver);
        routesFragmentManager = RoutesFragmentManager.getInstance();
        splashFragment = SplashFragment.newInstance();
        loginFragment = LoginFragment.newInstance();
        routesFragmentManager.initRouteFragmentManger(getSupportFragmentManager());
        routesFragmentManager.addFragmentToBackStact(loginFragment, NavigatorsTags.LANDING_FRAGMENT,binding.mainFragmentHolder.getId());
        routesFragmentManager.addFragmentToBackStact(splashFragment,NavigatorsTags.SPLASH_FRAGMENT,binding.mainFragmentHolder.getId());
    }
    private void navigateTo(String tag) {
        routesFragmentManager.popFragmentExclusive(tag);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}