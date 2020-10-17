package com.example.routes.ui;



import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.routes.R;
import com.example.routes.appcomponents.NavigatorsTags;
import com.example.routes.appcomponents.RoutesFragmentManager;
import com.example.routes.architecture.FragmentHolderViewModel;
import com.example.routes.architecture.MainViewModel;
import com.example.routes.databinding.ActivityFragmentHolderBinding;
import com.example.routes.fragment.ActivityWorkFragment;
import com.example.routes.fragment.ConsumerFragment;
import com.example.routes.fragment.ProfileFragment;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class FragmentHolderActivity extends AppCompatActivity {

    ActivityFragmentHolderBinding binding;
    ProfileFragment profileFragment;
    RoutesFragmentManager routesFragmentManager;
    FragmentHolderViewModel fragmentHolderViewModel;
    Observer<String> navigationObserver;
    Button activeBtn;
    ConstraintLayout nextBtnlayout, prevBtnLayout;
    ConsumerFragment consumerFragment;
    ActivityWorkFragment activityWorkFragment;

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
                Log.d("check tag",tag);
            }
        };

        initalize();
       prevBtnLayout.setVisibility(View.GONE);


        profileFragment = ProfileFragment.newInstance();
        consumerFragment = ConsumerFragment.newInstance();
        activityWorkFragment = ActivityWorkFragment.newInstance();
        routesFragmentManager = RoutesFragmentManager.getInstance();
        routesFragmentManager.initRouteFragmentManger(getSupportFragmentManager());
        fragmentHolderViewModel.getNavigation().observe(this,navigationObserver);

        routesFragmentManager.addFragmentToBackStack(activityWorkFragment,NavigatorsTags.ACTIVITY_WORK_FRAGMENT,binding.fragmentHolder.getId());
        routesFragmentManager.addFragmentToBackStack(consumerFragment,NavigatorsTags.CONSUMER_FRAGMENT,binding.fragmentHolder.getId());
        routesFragmentManager.addFragmentToBackStack(profileFragment, NavigatorsTags.PROFILE_FRAGMENT,binding.fragmentHolder.getId());

        routesFragmentManager.show(profileFragment);
        routesFragmentManager.hide(consumerFragment);
        routesFragmentManager.hide(activityWorkFragment);

        activeBtn.setText("Profile");

        nextBtnlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = routesFragmentManager.getIndexOfCurrentFragment();
                Log.d("index value", String.valueOf(index));
                if(index<0)
                {
                    //
                }
                else
                {
                    String tag = routesFragmentManager.getTagOfCurrentVisibleFragment();
                    Log.e("checking tag",tag);
                    switch (tag){
                        case NavigatorsTags.PROFILE_FRAGMENT:
                            prevBtnLayout.setVisibility(View.VISIBLE);
                            activeBtn.setText("Consumer");
                            routesFragmentManager.hide(profileFragment);
                            routesFragmentManager.show(consumerFragment);
                            routesFragmentManager.hide(activityWorkFragment);
                            break;
                        case NavigatorsTags.CONSUMER_FRAGMENT:
                            nextBtnlayout.setVisibility(View.GONE);
                            activeBtn.setText("Activity");

                            routesFragmentManager.hide(profileFragment);
                            routesFragmentManager.hide(consumerFragment);
                            routesFragmentManager.show(activityWorkFragment);
                            break;
                        case NavigatorsTags.ACTIVITY_WORK_FRAGMENT:
                          //
                            break;
                    }
                }

            }
        });


        prevBtnLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               backPressed();
            }
        });

    }

    private void initalize() {
        activeBtn = binding.activeBtn;
        nextBtnlayout = binding.nextBtnLayout;
        prevBtnLayout = binding.prevBtnLayout;
    }

    private void backPressed()
    {
        int index = routesFragmentManager.getIndexOfCurrentFragment();
        Log.d("index value", String.valueOf(index));
        if(index<0)
        {
            SweetAlertDialog s = new SweetAlertDialog(this,SweetAlertDialog.WARNING_TYPE);
            s.setTitleText("Are you sure want to close?");
            s.setConfirmButton("Ok", new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    s.dismissWithAnimation();
                    finish();
                }
            });
            s.setCancelButton("No", new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    s.dismissWithAnimation();
                    routesFragmentManager.show(profileFragment);
                }
            });
            s.show();
        }
        else
        {
            String tag = routesFragmentManager.getTagOfCurrentVisibleFragment();
            if(tag == null)
            {
                SweetAlertDialog s = new SweetAlertDialog(this,SweetAlertDialog.WARNING_TYPE);
                s.setTitleText("Are you sure want to close?");
                s.setConfirmButton("Ok", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        s.dismissWithAnimation();
                        finish();
                    }
                });
                s.setCancelButton("No", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        s.dismissWithAnimation();
                        routesFragmentManager.show(profileFragment);
                    }
                });
                s.show();
            }
            else
            {
                switch (tag){
                    case NavigatorsTags.PROFILE_FRAGMENT:
                        //

                        break;
                    case NavigatorsTags.CONSUMER_FRAGMENT:
                        nextBtnlayout.setVisibility(View.VISIBLE);
                        prevBtnLayout.setVisibility(View.GONE);
                        activeBtn.setText("Profile");
                        routesFragmentManager.show(profileFragment);
                        routesFragmentManager.hide(consumerFragment);
                        routesFragmentManager.hide(activityWorkFragment);
                        break;
                    case NavigatorsTags.ACTIVITY_WORK_FRAGMENT:
                        nextBtnlayout.setVisibility(View.VISIBLE);
                        activeBtn.setText("Consumer");
                        routesFragmentManager.hide(profileFragment);
                        routesFragmentManager.show(consumerFragment);
                        routesFragmentManager.hide(activityWorkFragment);
                        break;
                }
            }

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        backPressed();
    }

    private void navigateTo(String tag) {
        routesFragmentManager.popFragmentExclusive(tag);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
