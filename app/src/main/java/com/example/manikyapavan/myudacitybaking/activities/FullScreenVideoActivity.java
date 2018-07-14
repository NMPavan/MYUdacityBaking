package com.example.manikyapavan.myudacitybaking.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import com.example.manikyapavan.myudacitybaking.R;
import com.example.manikyapavan.myudacitybaking.utils.ExoPlayerVideoHandler;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FullScreenVideoActivity extends AppCompatActivity {
    private static final boolean AUTO_HIDE = true;


    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            //  hide();
        }
    };
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };
    public static final String CURRENT_POSITION = "CURRENT_POSITION";
    public static final String URI = "FULL_SCREEN_URI";
    private long mcurrentPostion;

    @BindView(R.id.video_full_sceen_exoplayer_view)
    SimpleExoPlayerView mSimpleExoPlayerView;
    @BindView(R.id.exo_full_screen)
    ImageButton imageButtonFullScreenExit;
    private PlaybackStateCompat.Builder mStateBuilder;
    private MediaSessionCompat mMediaSession;
    private boolean destroyVideo = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_video);
        ButterKnife.bind(this);
        // mcurrentPostion = getIntent().getLongExtra(CURRENT_POSITION, 0);
        String stringUri = getIntent().getStringExtra(URI);
        if (savedInstanceState != null) {
            mcurrentPostion = savedInstanceState.getLong(CURRENT_POSITION);
        }
        hide();
        imageButtonFullScreenExit.setImageResource(R.drawable.exo_fullscreen_exit_24dp);
        imageButtonFullScreenExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendResultBack();

            }
        });
        ExoPlayerVideoHandler.getInstance()
                .prepareExoPlayerForUri(getApplicationContext(), Uri.parse(stringUri), mSimpleExoPlayerView);
        ExoPlayerVideoHandler.getInstance().goToForeground();

        mVisible = true;
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        ExoPlayerVideoHandler.getInstance().updateResumePosition();
        super.onSaveInstanceState(outState);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        destroyVideo = false;
        sendResultBack();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (destroyVideo) {
            ExoPlayerVideoHandler.getInstance().releaseVideoPlayer();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        ExoPlayerVideoHandler.getInstance().goToBackground();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ExoPlayerVideoHandler.getInstance().goToForeground();


    }

    private void sendResultBack() {
        Intent sendInten = new Intent();
        ExoPlayerVideoHandler.getInstance().updateResumePosition();
        ExoPlayerVideoHandler.getInstance().releaseVideoPlayer();
        setResult(RESULT_OK, sendInten);
        finish();
    }

}
