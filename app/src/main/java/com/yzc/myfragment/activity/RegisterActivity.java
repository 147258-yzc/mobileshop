package com.yzc.myfragment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yzc.myfragment.R;
import com.yzc.myfragment.bean.LoginResponse;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;


public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.zc_uname)EditText zc_uname;
    @BindView(R.id.zc_email)EditText zc_email;
    @BindView(R.id.zc_password)EditText zc_password;
    @BindView(R.id.zc_password2)EditText zc_password2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.im_back)
    void back(){
        finish();
    }

    @OnClick(R.id.zc_register)
    void register(){
        String uname=zc_uname.getText().toString();
        if(TextUtils.isEmpty(uname)){
            Toast.makeText(this,"请输入用户名",Toast.LENGTH_SHORT).show();
            return;
        }
        String email=zc_email.getText().toString();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"请输入邮箱",Toast.LENGTH_SHORT).show();
            return;
        }
        String password=zc_password.getText().toString();
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"请输入密码",Toast.LENGTH_SHORT).show();
            return;
        }
        String password2=zc_password2.getText().toString();
        if(TextUtils.isEmpty(password2)){
            Toast.makeText(this,"请再次输入密码",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!password.equals(password2)){
            Toast.makeText(this,"两次输入的密码不一致",Toast.LENGTH_SHORT).show();
            return;
        }
        OkHttpUtils
                .post()
                .url("http://10.10.16.23:8088/MobileShop/member")
                .addParams("uname",uname)
                .addParams("password",password)
                .addParams("email",email)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(RegisterActivity.this,"注册失败"+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson=new Gson();
                        LoginResponse loginResponse=gson.fromJson(response,LoginResponse.class);
                        if(loginResponse!=null&&loginResponse.getStatus()==0){
                            Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(RegisterActivity.this,"注册失败"+loginResponse.getMsg(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}


