package com.example.routes.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.routes.R;
import com.example.routes.architecture.FragmentHolderViewModel;
import com.example.routes.data.model.UserData;
import com.example.routes.databinding.ProfileFragmentBinding;
import com.example.routes.databinding.SplashFragmentBinding;
import com.example.routes.singleton.MySingleton;
import com.example.routes.utility.CustomUtility;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ProfileFragment extends RoutesFragment {
    SweetAlertDialog sweetAlertDialog;
    JSONObject jsonObject;
    String code, message;
    String userId = "";
    SharedPreferences sharedPreferences;
    TextView txtName, txtId, txtTeam, txtTodayCount, txtTotalCount;

    UserData userData;
    ProfileFragmentBinding binding;
    FragmentHolderViewModel fragmentHolderViewModel;
    public static ProfileFragment newInstance() {

        Bundle args = new Bundle();

        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentHolderViewModel = new ViewModelProvider(requireActivity()).get(FragmentHolderViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ProfileFragmentBinding.inflate(getLayoutInflater(),container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button activeBtn = getActivity().findViewById(R.id.active_btn);
        activeBtn.setText("Profile");

        userData = UserData.getInstance();
        txtName = view.findViewById(R.id.name);
        txtId = view.findViewById(R.id.id);
        txtTeam = view.findViewById(R.id.team);
        txtTodayCount = view.findViewById(R.id.todayCount);
        txtTotalCount = view.findViewById(R.id.totalCount);

        txtName.setText(userData.getName());
        txtTeam.setText(userData.getTeam());
        txtId.setText(userData.getId());
        if(!userData.getTodayCount().equals(""))
        {
            txtTodayCount.setText(userData.getTodayCount());
            txtTotalCount.setText(userData.getTotalCount());
        }
        else
        getStatus();

    }


    public void getStatus() {
        sweetAlertDialog = new SweetAlertDialog(getActivity(), 5);
        sweetAlertDialog.setTitleText("Loading");
        sweetAlertDialog.show();
        MySingleton.getInstance(getActivity()).addToRequestQue(new StringRequest(1, "https://bkash.imslpro.com/api/consumer/user_status.php", new Response.Listener<String>() {
            public void onResponse(String response) {
                try {
                    sweetAlertDialog.dismiss();
                    Log.e("response", response);
                    jsonObject = new JSONObject(response);
                    code = jsonObject.getString("success");
                    if (code.equals("true")) {
                        txtTodayCount.setText(jsonObject.getString("todayCount"));
                        txtTotalCount.setText(jsonObject.getString("totalCount"));
                        userData = UserData.getInstance();
                        userData.setTodayCount(jsonObject.getString("todayCount"));
                        userData.setTotalCount(jsonObject.getString("totalCount"));
                        return;
                    }
                    CustomUtility.showError(getActivity(), "No data found", "Failed");
                } catch (JSONException e) {
                    CustomUtility.showError(getActivity(), e.getMessage(), "Getting Response");
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                sweetAlertDialog.dismiss();
                CustomUtility.showError(getActivity(), "Network Error, try again!", "Failed");
                final SweetAlertDialog s = new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE);
                s.setConfirmText("Ok");
                s.setTitleText("Network Error, try again!");
                s.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        s.dismissWithAnimation();
                        startActivity(getActivity().getIntent());
                        getActivity().finish();
                    }
                });
                s.show();
            }
        }) {
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("UserId", userData.getId());
                return params;
            }
        });
    }
}
