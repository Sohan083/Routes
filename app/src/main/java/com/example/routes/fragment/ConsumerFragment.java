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
import com.example.routes.databinding.ConsumerFragmentBinding;

public class ConsumerFragment extends RoutesFragment{

    public static ConsumerFragment consumerFragment;

    ConsumerFragmentBinding binding;
    FragmentHolderViewModel fragmentHolderViewModel;

    public static ConsumerFragment newInstance()
    {
        if(consumerFragment == null)
        {
            consumerFragment = new ConsumerFragment();
        }
        return consumerFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentHolderViewModel = new ViewModelProvider(requireActivity()).get(FragmentHolderViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ConsumerFragmentBinding.inflate(getLayoutInflater(),container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
