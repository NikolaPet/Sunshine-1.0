package com.example.nikola.sunshine;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.percent.PercentLayoutHelper;
import android.support.percent.PercentLayoutHelper.PercentLayoutInfo;
import android.support.percent.PercentRelativeLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;



import java.util.ArrayList;
import java.util.Random;

public class LearnNumbersActivity extends AppCompatActivity {

    // The following are used for the shake detection
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

    // boolean values
    private boolean meaningfulShake = true;
    private boolean canClick = false;
    private boolean canShake = false;

    // media player
    private MediaPlayer mp;
    private MediaPlayer mp_droplet0;
    private MediaPlayer mp_droplet1;
    private MediaPlayer mp_droplet2;
    private MediaPlayer mp_droplet3;
    private MediaPlayer mp_droplet4;
    private MediaPlayer mp_droplet5;
    private MediaPlayer mp_droplet6;
    private MediaPlayer mp_droplet7;
    private MediaPlayer mp_droplet8;
    private MediaPlayer mp_droplet9;
    private MediaPlayer mp_shake;
    private MediaPlayer mp_windIntro;

    // list of droplet media players for convenience
    private MediaPlayer[] droplet_players;

    // views
    private ImageView sun;
    private ImageView face_sun;
    private HorizontalScrollView horizontalScrollView;
    private ArrayList<ImageView> droplets;

    // animations
    Animation fade_in;
    Animation fade_out;
    Animation indefinite_rotation;
    Animation alpha_1;
    Animation droplet_falling;
    Animation droplet_falling_with_offset;

    // layout
    PercentRelativeLayout layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_numbers);

        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {
            @Override
            public void onShake(int count) {
                handleShakeEvent();
            }
        });

        // play intro sound and create media players
        mp = MediaPlayer.create(this, R.raw.learn_numbers_intro); // this will also include "На Сончко му е топло! Протреси го екранот!"
        mp_droplet0 = MediaPlayer.create(this, R.raw.droplet_high);
        mp_droplet1 = MediaPlayer.create(this, R.raw.droplet_high);
        mp_droplet2 = MediaPlayer.create(this, R.raw.droplet_high);
        mp_droplet3 = MediaPlayer.create(this, R.raw.droplet_high);
        mp_droplet4 = MediaPlayer.create(this, R.raw.droplet_high);
        mp_droplet5 = MediaPlayer.create(this, R.raw.droplet_high);
        mp_droplet6 = MediaPlayer.create(this, R.raw.droplet_high);
        mp_droplet7 = MediaPlayer.create(this, R.raw.droplet_high);
        mp_droplet8 = MediaPlayer.create(this, R.raw.droplet_high);
        mp_droplet9 = MediaPlayer.create(this, R.raw.droplet_high);
        mp_shake = MediaPlayer.create(this, R.raw.wind_shake);
        mp_windIntro = MediaPlayer.create(this, R.raw.wind_intro);

        // populate droplet media player array
        droplet_players = new MediaPlayer[10];
        droplet_players[0] = mp_droplet0;
        droplet_players[1] = mp_droplet1;
        droplet_players[2] = mp_droplet2;
        droplet_players[3] = mp_droplet3;
        droplet_players[4] = mp_droplet4;
        droplet_players[5] = mp_droplet5;
        droplet_players[6] = mp_droplet6;
        droplet_players[7] = mp_droplet7;
        droplet_players[8] = mp_droplet8;
        droplet_players[9] = mp_droplet9;

        // consider moving this to onStart()
        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                queueMediaPlayer(mp_shake);
                mp_shake.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        canShake = true;
                    }
                });
            }
        });

        // setting up the views
        sun = (ImageView)findViewById(R.id.learnNumbers_imageView);
        face_sun = (ImageView)findViewById(R.id.learnNumbers_image_face_sun);

        horizontalScrollView = (HorizontalScrollView)findViewById(R.id.horizontalScrollView);
        droplets = new ArrayList<>();

        // setting up the animations
        indefinite_rotation = AnimationUtils.loadAnimation(this, R.anim.indefinite_rotation_animation);
        fade_in = AnimationUtils.loadAnimation(this, R.anim.fade_in_animation_short);
        fade_out = AnimationUtils.loadAnimation(this, R.anim.fade_out_animation_short);
        alpha_1 = AnimationUtils.loadAnimation(this, R.anim.alpha_1_animation);
        droplet_falling = AnimationUtils.loadAnimation(this, R.anim.droplet_falling_animation);
        droplet_falling_with_offset = AnimationUtils.loadAnimation(this, R.anim.droplet_falling_with_offset_animation);

        // setting up the layout
        layout = (PercentRelativeLayout)findViewById(R.id.activity_learn_numbers);

        // setting up the listeners
        horizontalScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                for (int i=0; i<droplets.size(); i++) {
                    ImageView droplet = droplets.get(i);
                    droplet.clearAnimation();

                    if (droplet.getParent() != null)
                        ((ViewGroup) droplets.get(i).getParent()).removeView(droplets.get(i));

                }

            }

        });



        ObjectAnimator scaleDown = ObjectAnimator.ofPropertyValuesHolder(sun,
                PropertyValuesHolder.ofFloat("scaleX", 1.2f),
                PropertyValuesHolder.ofFloat("scaleY", 1.2f));
        scaleDown.setDuration(310);

        scaleDown.setRepeatCount(ObjectAnimator.INFINITE);
        scaleDown.setRepeatMode(ObjectAnimator.REVERSE);

        scaleDown.start();

        //sun.startAnimation(indefinite_rotation);
        sun.startAnimation(fade_in);
        face_sun.startAnimation(fade_in);


        // set the view invisible
        horizontalScrollView.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    protected void onPause() {
        super.onPause();
        // unregister the accelerometer
        mSensorManager.unregisterListener(mShakeDetector);

        // pause the media player
        mp.pause();
        mp.seekTo(0);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // register the accelerometer
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
        // resume the media player
        //mp.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        // play the fade animation
        Intent backToLearningMode = new Intent(this, LearningModeActivity.class);
        startActivity(backToLearningMode);
        //overridePendingTransition(R.anim.fade_in_animation_short, R.anim.fade_out_animation_short);
    }

    public void handleShakeEvent() {
        if (!mp.isPlaying() && canShake) {
            if (meaningfulShake) {
                meaningfulShake = false;
                face_sun.startAnimation(fade_out);
                sun.startAnimation(fade_out);
                fade_out.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        // acquiring the percent relative layout parameters for our views
                        PercentRelativeLayout.LayoutParams params_sun = (PercentRelativeLayout.LayoutParams) sun.getLayoutParams();
                        PercentLayoutHelper.PercentLayoutInfo info_sun = params_sun.getPercentLayoutInfo();

                        PercentRelativeLayout.LayoutParams params_face_sun = (PercentRelativeLayout.LayoutParams) face_sun.getLayoutParams();
                        PercentLayoutHelper.PercentLayoutInfo info_face_sun = params_face_sun.getPercentLayoutInfo();

                        // resetting the height and width of the images
                        sun.getLayoutParams().height = PercentRelativeLayout.LayoutParams.WRAP_CONTENT;
                        sun.getLayoutParams().width = PercentRelativeLayout.LayoutParams.WRAP_CONTENT;

                        info_face_sun.widthPercent=0.15f;
                        info_face_sun.heightPercent=0.15f;

                        // resetting the top and left margin of the images
                        info_sun.leftMarginPercent=0.7f;
                        info_sun.topMarginPercent=0.75f;

                        info_face_sun.leftMarginPercent=0.78f;
                        info_face_sun.topMarginPercent=0.8f;

                        face_sun.requestLayout();
                        sun.requestLayout();

                        sun.startAnimation(indefinite_rotation);
                        face_sun.startAnimation(alpha_1);

                        // make the clouds view visible again
                        //horizontalScrollView.setVisibility(View.VISIBLE);
                        Animation view_fadeIn = AnimationUtils.loadAnimation(LearnNumbersActivity.this, R.anim.fade_in_animation_short);
                        horizontalScrollView.startAnimation(view_fadeIn);
                        view_fadeIn.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                horizontalScrollView.setVisibility(View.VISIBLE);
                                mp_windIntro.start();
                                mp_windIntro.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    @Override
                                    public void onCompletion(MediaPlayer mp) {
                                        canClick = true;
                                    }
                                });
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        }
    }

    private void focusOnView(final HorizontalScrollView scroll, final View view) {
        new Handler().post(new Runnable() {
           @Override
            public void run() {
                int vLeft = view.getLeft();
                int vRight = view.getRight();
                int sWidth = scroll.getWidth();
                scroll.smoothScrollTo(((vLeft + vRight - sWidth) / 2), 0);
            }
        });
    }

    private PercentLayoutInfo generateInfo(PercentLayoutInfo info) {
        info.topMarginPercent = 0.50f;
        info.heightPercent = 0.06f;
        info.widthPercent = 0.06f;

        return info;
    }

    private void setDropletFallingAnimationListener(Animation animation, final MediaPlayer player) {
        animation.setAnimationListener(new MyAnimationListener(player) {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                if (player != null) {
                    if (!player.isPlaying())
                        player.start();
                }
            }
        });
    }

    private Animation getRandomizedDropletFallingAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.droplet_falling_animation);
        //setDropletFallingAnimationListener(animation);
        Random random = new Random();
        int roll_result = random.nextInt(10);
        switch(roll_result) {
            case 0:
                animation.setDuration(1050);
                animation.setStartOffset(50);
                break;
            case 1:
                animation.setDuration(1000);
                animation.setStartOffset(100);
                break;
            case 3:
                animation.setDuration(950);
                animation.setStartOffset(150);
                break;
            case 4:
                animation.setDuration(900);
                animation.setStartOffset(200);
                break;
            case 5:
                animation.setDuration(850);
                animation.setStartOffset(250);
                break;
            case 6:
                animation.setDuration(800);
                animation.setStartOffset(300);
                break;
            case 7:
                animation.setDuration(750);
                animation.setStartOffset(350);
                break;
            case 8:
                animation.setDuration(700);
                animation.setStartOffset(400);
                break;
            case 9:
                animation.setDuration(650);
                animation.setStartOffset(450);
                break;
            default:
                break;
        }

        setDropletFallingAnimationListener(animation, droplet_players[roll_result]);


        return animation;

    }

    private void queueMediaPlayer(MediaPlayer player) {
        player.start();
    }

    private void generateDroplets(int count) {
        // case: even number of droplets
        if (count % 2 == 0) {
            float start_point = 0.50f;
            float distance = 0.10f;
            for (int i=0; i<count; i++) {
                ImageView droplet = new ImageView(this);
                droplet.setBackgroundResource(R.drawable.droplet);
                PercentRelativeLayout.LayoutParams params = new PercentRelativeLayout.LayoutParams(PercentRelativeLayout.LayoutParams.WRAP_CONTENT, PercentRelativeLayout.LayoutParams.WRAP_CONTENT);
                PercentLayoutHelper.PercentLayoutInfo info = params.getPercentLayoutInfo();

                info = generateInfo(info);

                if (i%2 == 0) {
                    info.leftMarginPercent = start_point - 0.05f + distance * (float) Math.ceil((i + 2) / 2);
                    Button btn = (Button)findViewById(R.id.Button2);
                    btn.setText(String.valueOf(info.leftMarginPercent));
                    btn.requestLayout();
                }
                else
                    info.leftMarginPercent = start_point + 0.05f - distance*(float)Math.ceil((i+2)/2);

                droplet.setLayoutParams(params);

                droplets.add(droplet);

                Animation randomized_animation = getRandomizedDropletFallingAnimation();

                layout.addView(droplet);
                droplet.startAnimation(randomized_animation);
                droplet.requestLayout();
            }
        }
        // case: odd number of droplets
        else {
            float start_point = 0.50f;
            float distance = 0.10f;
            for (int i=0; i<count; i++) {
                ImageView droplet = new ImageView(this);
                droplet.setBackgroundResource(R.drawable.droplet);
                PercentRelativeLayout.LayoutParams params = new PercentRelativeLayout.LayoutParams(PercentRelativeLayout.LayoutParams.WRAP_CONTENT, PercentRelativeLayout.LayoutParams.WRAP_CONTENT);
                PercentLayoutHelper.PercentLayoutInfo info = params.getPercentLayoutInfo();

                info = generateInfo(info);
                if (i == 0)
                    info.leftMarginPercent = start_point;
                else {
                    if (i%2 == 0)
                        info.leftMarginPercent = start_point + distance * (float)Math.ceil((i+1)/2);
                    else
                        info.leftMarginPercent = start_point - distance * (float)Math.ceil((i+2)/2);
                }

                droplet.setLayoutParams(params);

                droplets.add(droplet);

                Animation randomized_animation = getRandomizedDropletFallingAnimation();

                layout.addView(droplet);
                droplet.startAnimation(randomized_animation);
                droplet.requestLayout();
            }
        }
    }

    public void deleteDroplets() {

        for (int i=0; i<droplets.size(); i++) {
            if (droplets.get(i).getParent() != null) {
                ((ViewGroup) droplets.get(i).getParent()).removeView(droplets.get(i));
            }
        }

        droplets = new ArrayList<>();
    }

    public void onButtonClick(View v) {

        if (canClick) {

            deleteDroplets();

            focusOnView(horizontalScrollView, v);

            droplets = new ArrayList<>();

            Button button = (Button) findViewById(v.getId());
            String tag = button.getTag().toString();
            int count = Integer.parseInt(tag);

            generateDroplets(count);
        }

    }

    // REMOVE THIS METHOD
    public void onTestButtonClick(View view) {
        Intent REMOVE_ME = new Intent(this, LearnNumbersGameScreenActivity.class);
        startActivity(REMOVE_ME);
    }
}

