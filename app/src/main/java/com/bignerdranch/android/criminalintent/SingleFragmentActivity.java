package com.bignerdranch.android.criminalintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import java.util.Date;

/**
 * Created by Yogesh on 24-01-2017.
 */

public abstract class SingleFragmentActivity extends FragmentActivity {

    private static final String SEND_DATE = "sendDate";

    protected abstract Fragment createFragment();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

    public void sendResult(Date date) {
        Intent intent = new Intent();
        intent.putExtra(SEND_DATE, date);
        setResult(Activity.RESULT_OK, intent);
    }
}
