package net.vnnz.arduinoandroid.listener;

import android.bluetooth.BluetoothDevice;
import android.content.Context;

/**
 * Created by viktoriala on 4/9/2015.
 */
public interface BluetoothListener {

    public void connectionInitiated(BluetoothDevice device);
    public void connectionStarted();
    public void connectionFailed();
    public void deviceDisconnected();
    public Context getContext();

}
