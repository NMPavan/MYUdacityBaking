package com.example.manikyapavan.myudacitybaking.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.manikyapavan.myudacitybaking.R;
import com.example.manikyapavan.myudacitybaking.fragments.StepDetailFragment;
import com.example.manikyapavan.myudacitybaking.models.Step;

import java.util.ArrayList;

import static com.example.manikyapavan.myudacitybaking.R.id.step_detail_container;

public class StepActivity extends AppCompatActivity {
    private static final int INITIAL_POSITION = 0;
    private static final boolean TABLET_FLAG = true;
    public static StepDetailFragment sStepDetailInstance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_step);
        // if it's a tablet and it's on landscape mode then add a StepDetailFragment as a static
        // instance to able to handle it from this activity
        if (getResources().getConfiguration().smallestScreenWidthDp >= 600) {
            ArrayList<Step> stepArrayList = getIntent().getParcelableArrayListExtra(getString(R.string.action_steps));
            FragmentManager fragmentManager = getSupportFragmentManager();
            sStepDetailInstance = StepDetailFragment.newtInstance(stepArrayList, INITIAL_POSITION, TABLET_FLAG);

            if (!(savedInstanceState != null)) {
                fragmentManager.beginTransaction()
                        .add( step_detail_container, sStepDetailInstance)
                        .commit();
            }
        }
    }
}
