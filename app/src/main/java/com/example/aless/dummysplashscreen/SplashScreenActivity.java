package com.example.aless.dummysplashscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

public class SplashScreenActivity extends Activity {
    private static final String STATE_SAVED_INSTANCE = "state_saved_instance";

    private AsyncTask mAsyncTaskSplashCreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Check whether we're recreating a previously destroyed instance
        if (savedInstanceState == null) {
            mAsyncTaskSplashCreen = new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... params) {
                    try {
                        Thread.sleep(3000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);

                    Intent intentMainActivity = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(intentMainActivity);

                    finish();

                }
            }.execute();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean(STATE_SAVED_INSTANCE, true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        mAsyncTaskSplashCreen.cancel(true);

        finish();
    }
}
