package com.example.routes.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ViewModelProvider;

import com.example.routes.architecture.FragmentHolderViewModel;
import com.example.routes.architecture.MainViewModel;

public abstract class RoutesFragment extends Fragment {
    protected FragmentHolderViewModel fragmentHolderViewModel;
    protected FragmentLifecycleOwner fragmentLifecycleOwner;

    public static class FragmentLifecycleOwner implements LifecycleOwner{
        private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

        @NonNull
        @Override
        public LifecycleRegistry getLifecycle() {
            return lifecycleRegistry;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentHolderViewModel = new ViewModelProvider(requireActivity()).get(FragmentHolderViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentLifecycleOwner = new FragmentLifecycleOwner();
        fragmentLifecycleOwner.getLifecycle().handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (fragmentLifecycleOwner != null)
        {
            fragmentLifecycleOwner.getLifecycle().handleLifecycleEvent(Lifecycle.Event.ON_START);
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        if(fragmentLifecycleOwner != null){
            fragmentLifecycleOwner.getLifecycle().handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
        }
    }



    @Override
    public void onPause() {
        super.onPause();
        if(fragmentLifecycleOwner != null){
            fragmentLifecycleOwner.getLifecycle().handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if(fragmentLifecycleOwner != null){
            fragmentLifecycleOwner.getLifecycle().handleLifecycleEvent(Lifecycle.Event.ON_STOP);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (fragmentLifecycleOwner != null) {
            fragmentLifecycleOwner.getLifecycle().handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
            fragmentLifecycleOwner = null;
        }
    }
}
