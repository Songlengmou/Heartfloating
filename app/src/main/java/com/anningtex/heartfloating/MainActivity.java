package com.anningtex.heartfloating;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.anningtex.heartfloating.animator.HeartLayout;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Song
 * desc:心形动画悬浮
 */
public class MainActivity extends AppCompatActivity {
    private HeartLayout mHeartLayout;
    private Random mRandom = new Random();
    private Timer mTimer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mHeartLayout = findViewById(R.id.heart_layout);

        mTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                mHeartLayout.post(() -> mHeartLayout.addHeart(randomColor()));
            }
        }, 500, 200);


        //有问题
////        org.apache.commons.lang3.concurrent.BasicThreadFactory
//        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
//                new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
//        executorService.scheduleAtFixedRate(() -> {
//            //do something
//            mHeartLayout.post(() -> mHeartLayout.addHeart(randomColor()));
//        }, 500, 200, TimeUnit.SECONDS);
    }

    private int randomColor() {
        return Color.rgb(mRandom.nextInt(255), mRandom.nextInt(255), mRandom.nextInt(255));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTimer.cancel();
    }
}
