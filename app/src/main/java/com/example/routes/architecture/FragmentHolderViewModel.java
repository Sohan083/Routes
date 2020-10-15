package com.example.routes.architecture;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.routes.databinding.ProfileFragmentBinding;

public class FragmentHolderViewModel extends ViewModel {
    private MutableLiveData<String> navigation = new MutableLiveData<>();


    public MutableLiveData<String> getNavigation() {
        return navigation;
    }

    public void setNavigation(String navigateTo){
        navigation.setValue(navigateTo);
    }
}