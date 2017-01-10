package com.example.nikola.sunshine;

import android.media.MediaPlayer;
import android.view.animation.Animation;

/**
 * Created by Nikola on 0006 06/01/17 .
 */

public class MyAnimationListener implements Animation.AnimationListener {

    private MediaPlayer mp;

    public MyAnimationListener(MediaPlayer mp) {
        this.mp = mp;
    }


    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
