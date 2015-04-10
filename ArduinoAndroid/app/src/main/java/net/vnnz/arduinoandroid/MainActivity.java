package net.vnnz.arduinoandroid;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import net.vnnz.arduinoandroid.controller.BluetoothController;
import net.vnnz.arduinoandroid.controller.UIController;
import net.vnnz.arduinoandroid.dialog.DeviceSelectDialog;
import net.vnnz.arduinoandroid.listener.BluetoothListener;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;


public class MainActivity extends Activity implements BluetoothListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private final int REQUEST_TURN_BLUETOOTH = 1;

    private BluetoothController bluetoothController;
    private UIController uiController;
    private MenuItem statusItem;
    private MenuItem nameItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActionBar().setTitle("");

        getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.header_bg));
        bluetoothController = new BluetoothController(this);
        uiController = new UIController(MainActivity.this);

        if (!bluetoothController.isBluetoothEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_TURN_BLUETOOTH);
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_TURN_BLUETOOTH:
                if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(this, "nooo oooook", Toast.LENGTH_LONG).show();
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation, menu);
        statusItem = menu.findItem(R.id.action_connect);
        nameItem   = menu.findItem(R.id.device_name);

        View nameView = getLayoutInflater().inflate(R.layout.texview_action, null);
        nameItem.setActionView(nameView).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

      /*  TextView tv = new TextView(this);
        tv.setTextColor(getResources().getColor(android.R.color.white));
        tv.setTypeface(Typeface.createFromAsset(getAssets(), getString(R.string.font_directive_four)));
        tv.setTextSize(15);
        nameItem.setActionView(tv).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_connect) {
            DeviceSelectDialog dialog = new DeviceSelectDialog(this, bluetoothController);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void connectionInitiated(final BluetoothDevice device) {
        uiController.startStatusAnimation(statusItem);
        uiController.changeTextHeader(nameItem, device.getName());
    }

    @Override
    public void connectionStarted() {
        uiController.stopStatusAnimation(statusItem, true);
    }

    @Override
    public void connectionFailed() {
        uiController.stopStatusAnimation(statusItem, false);
    }

    @Override
    public void deviceDisconnected() {

    }

    @Override
    public Context getContext() {
        return null;
    }
}
