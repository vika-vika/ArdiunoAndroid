package net.vnnz.arduinoandroid.controller;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.view.MenuItem;
import android.widget.TextView;

import net.vnnz.arduinoandroid.R;

/**
 * Created by viktoriala on 4/9/2015.
 */
public class UIController {

    private Activity context;

    public UIController(Activity context) {
        this.context = context;
    }

    public void changeTextHeader(final MenuItem nameItem, final String text){
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView name = (TextView) nameItem.getActionView();
                name.setText(text);
        }});

}

    public void startStatusAnimation(final MenuItem statusItem) {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                statusItem.setIcon(R.drawable.animation_status);
                AnimationDrawable rocketAnimation = (AnimationDrawable) statusItem.getIcon();
                rocketAnimation.start();
            }
        });
    }

    public void stopStatusAnimation(final MenuItem statusItem, final boolean isSucceed) {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AnimationDrawable rocketAnimation = (AnimationDrawable) statusItem.getIcon();
                rocketAnimation.stop();
                int icon = isSucceed ? R.drawable.status_connected : R.drawable.status_disconnected;
                statusItem.setIcon(icon);
            }
        });
    }

    public void setStatus(final MenuItem statusItem, final boolean isSucceed) {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int icon = isSucceed ? R.drawable.status_connected : R.drawable.status_disconnected;
                statusItem.setIcon(icon);
            }
        });
    }
}
