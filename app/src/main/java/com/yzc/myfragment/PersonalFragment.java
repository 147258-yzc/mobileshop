package com.yzc.myfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.yzc.myfragment.activity.LoginActivity;
import com.yzc.myfragment.activity.SpTools;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class PersonalFragment extends Fragment {

    @BindView(R.id.bt_login2)
    Button button;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_personal,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        boolean islogin = SpTools.getBoolean("islogin", false);
        if (islogin){
            button.setText("退出登录");
        }else {
            button.setText("登录");
        }
    }
    @OnClick(R.id.bt_login2)
    void  login(){
        boolean islogin = SpTools.getBoolean("islogin", false);
        if (islogin){
            SpTools.setBoolean("islogin",false);
            Toast.makeText(getActivity(),"退出成功",Toast.LENGTH_SHORT).show();
            onResume();
        }else {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }
    }
}
