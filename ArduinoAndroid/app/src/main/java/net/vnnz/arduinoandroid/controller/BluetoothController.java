package net.vnnz.arduinoandroid.controller;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

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
    BluetoothSocket mBlueSocket;

    public BluetoothController(Context context) {
        this.context = context;
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
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

    public boolean connect(BluetoothDevice btDevice){

        try{
            Log.e(TAG, "Connecting to the robot...");

            UUID uuid = UUID.fromString(UUID_VALUE);

            mBlueSocket = btDevice.createRfcommSocketToServiceRecord(uuid);
            mBlueSocket.connect();
            OutputStream mOut = mBlueSocket.getOutputStream();
            InputStream mIn = mBlueSocket.getInputStream();
            // connected = true;
            // this.start();

            Log.e(TAG, "Connected to the robot...");
            return true;

        }catch (Exception e){
            Log.e(TAG, "shit happens", e);
            return false;
        }
    }
}
