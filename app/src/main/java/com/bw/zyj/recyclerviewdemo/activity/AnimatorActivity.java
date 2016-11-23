package com.bw.zyj.recyclerviewdemo.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.bw.zyj.recyclerviewdemo.R;

public class AnimatorActivity extends AppCompatActivity {
    private ImageView mball;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);

        mball = (ImageView) findViewById(R.id.id_ball);
    }

    public void togetherRun(View view){

        ObjectAnimator animator1 = ObjectAnimator.ofFloat(mball, "scaleX", 1f, 2f);

        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mball, "scaleY", 1f, 2f);

        AnimatorSet set = new AnimatorSet();

        set.setDuration(3000);

        set.setInterpolator(new LinearInterpolator());

        set.playTogether(animator1, animator2);

        set.start();
    }

    public void playWithAfter(View view){
        float cx = mball.getX();

        ObjectAnimator animator1 = ObjectAnimator.ofFloat(mball, "scaleX", 1f, 2f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mball, "scaleX", 1f, 2f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(mball, "x", cx, 0f);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(mball, "x", cx);

        AnimatorSet set = new AnimatorSet();

        set.play(animator1).with(animator2);
        set.play(animator2).with(animator3);
        set.play(animator4).after(animator3);

        set.setDuration(3000);

        set.start();

    }
}
