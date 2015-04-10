package net.vnnz.arduinoandroid.dialog;

import android.app.Dialog;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;

import android.widget.Button;

import android.widget.ListView;


import net.vnnz.arduinoandroid.R;
import net.vnnz.arduinoandroid.adapter.DeviceAdapter;
import net.vnnz.arduinoandroid.controller.BluetoothController;


/**
 * Created by viktoriala on 4/2/2015.
 */
public class DeviceSelectDialog  extends Dialog implements View.OnClickListener, AdapterView.OnItemClickListener {

    private BluetoothController bluetoothController;

    public DeviceSelectDialog(Context context, BluetoothController bluetoothController) {
        super(context);
        this.bluetoothController = bluetoothController;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_select_device);

        ListView deviceList = (ListView) findViewById(R.id.device_list);
        deviceList.setOnItemClickListener(this);

        deviceList.setAdapter(new DeviceAdapter(getContext(), R.layout.list_row_devices, bluetoothController.getPairedDevices()));

        Button scan = (Button) findViewById(R.id.btn_scan);
        scan.setTypeface(Typeface.createFromAsset(getContext().getAssets(), getContext().getString(R.string.font_digital_tech)));

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        BluetoothDevice device = (BluetoothDevice) view.getTag();
        bluetoothController.connect(device);
        dismiss();
    }
}
