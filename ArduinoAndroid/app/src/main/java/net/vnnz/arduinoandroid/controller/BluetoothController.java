package net.vnnz.arduinoandroid.controller;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import net.vnnz.arduinoandroid.listener.BluetoothListener;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

/**
 * Created by viktoriala on 4/3/2015.
 */
public class BluetoothController {

    private final String UUID_VALUE = "00001101-0000-1000-8000-00805f9b34fb";//"21a6d085-401f-4744-a319-97b04e6396c8";
    private Context context;
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothSocket mBlueSocket;
    private BluetoothListener listener;

    public BluetoothController(BluetoothListener listener) {
        this.context = listener.getContext();
        this.bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        this.listener = listener;
    }

    public boolean isBluetoothEnabled(){
        return  bluetoothAdapter.isEnabled();
    }

    private static final String TAG = BluetoothController.class.getSimpleName() ;

    public ArrayList<BluetoothDevice> getPairedDevices (){
        ArrayList<BluetoothDevice> arr = new ArrayList<BluetoothDevice>();
        Set<BluetoothDevice> paired = bluetoothAdapter.getBondedDevices();
        if (paired.size() > 0) {
            for (BluetoothDevice device : paired) {
                arr.add(device);
            }
        }
        return arr;

    }

    public boolean connect(final BluetoothDevice btDevice) {

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    listener.connectionInitiated(btDevice);

                    Log.d(TAG, "Connecting to the device...");
                    UUID uuid = UUID.fromString(UUID_VALUE);

                    mBlueSocket = btDevice.createRfcommSocketToServiceRecord(uuid);

                    if (mBlueSocket.isConnected()) {
                        Log.e(TAG, "Already connected to the device...");
                        mBlueSocket.close();
                    }

                    mBlueSocket.connect();
                    OutputStream mOut = mBlueSocket.getOutputStream();
                    InputStream mIn = mBlueSocket.getInputStream();

                    Log.d(TAG, "Connected to the device...");
                    listener.connectionStarted();
                }catch (Exception e){
                    Log.e(TAG, "shit happens", e);
                    listener.connectionFailed();
                }
            }
        });
        t.start();
        return true;

    }
}
