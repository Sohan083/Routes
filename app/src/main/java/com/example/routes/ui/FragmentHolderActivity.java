package com.example.routes.ui;



import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.routes.appcomponents.NavigatorsTags;
import com.example.routes.appcomponents.RoutesFragmentManager;
import com.example.routes.architecture.FragmentHolderViewModel;
import com.example.routes.architecture.MainViewModel;
import com.example.routes.databinding.ActivityFragmentHolderBinding;
import com.example.routes.fragment.ProfileFragment;

public class FragmentHolderActivity extends AppCompatActivity {

    ActivityFragmentHolderBinding binding;
    ProfileFragment profileFragment;
    RoutesFragmentManager routesFragmentManager;
    FragmentHolderViewModel fragmentHolderViewModel;
    Observer<String> navigationObserver;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFragmentHolderBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();
        setContentView(root);
        fragmentHolderViewModel = (FragmentHolderViewModel) new ViewModelProvider(this).get(FragmentHolderViewModel.class);

        navigationObserver = new Observer<String>() {
            @Override
            public void onChanged(String tag) {
                navigateTo(tag);
            }
        };
        profileFragment = ProfileFragment.newInstance();
        routesFragmentManager = RoutesFragmentManager.getInstance();
        routesFragmentManager.initRouteFragmentManger(getSupportFragmentManager());
        fragmentHolderViewModel.getNavigation().observe(this,navigationObserver);
        routesFragmentManager.addFragmentToBackStact(profileFragment, NavigatorsTags.PROFILE_FRAGMENT,binding.fragmentHolder.getId());
    }

    private void navigateTo(String tag) {
        routesFragmentManager.popFragmentExclusive(tag);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
