package net.vnnz.arduinoandroid;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import net.vnnz.arduinoandroid.controller.BluetoothController;
import net.vnnz.arduinoandroid.dialog.DeviceSelectDialog;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;


public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private final int REQUEST_TURN_BLUETOOTH = 1;

    private BluetoothController bluetoothController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActionBar().setTitle("");
        getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.header_bg));

        bluetoothController = new BluetoothController(this);

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



}
