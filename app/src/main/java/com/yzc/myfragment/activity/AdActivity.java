package com.yzc.myfragment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yzc.myfragment.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdActivity extends AppCompatActivity {

    @BindView(R.id.tv_count)
    TextView tv_count;
    @BindView(R.id.iv_image)
    ImageView iv_image;

    boolean isstop = false;
    Thread thread;

    Handler handler = new Handler();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);

        ButterKnife.bind(this);

        Glide.with(AdActivity.this)
                .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574142860102&di=11ff67bd9dc873ba51820139b27e191f&imgtype=0&src=http%3A%2F%2Fi1.sinaimg.cn%2Fty%2Fo%2Fnfl%2Fp%2F2008-12-08%2FU3183P6T12D4114230F44DT20081208203416.jpg")
                .error(R.mipmap.error)
                .placeholder(R.mipmap.placeholder)
                .into(iv_image);

        tv_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (thread != null) {
                    thread.stop();
                }
                isstop = true;
                Intent intent = new Intent(AdActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
       /* tv_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.removeCallbacks(task);
                Intent intent=new Intent(AdActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });*/


        /*new Thread(){
            @Override
            public void run() {
                super.run();
            }
        }.start();
        */
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 5; i >= 0; i--) {
                    if (isstop) {
                        return;
                    }
                    SystemClock.sleep(1000);

                    final int count = i;
                     /*runOnUiThread(new Runnable() {
                         @Override
                         public void run() {
                             tv_count.setText("点击跳转 "+count);
                         }
                     });*/
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            tv_count.setText("点击跳转 " + count);
                        }
                    });
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(AdActivity.this, MainActivity.class);
                        startActivity(intent);

                        finish();
                    }
                });
            }
        }).start();


    }

    @OnClick(R.id.tv_count)
    public void onViewClicked() {
    }
}
