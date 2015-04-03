package net.vnnz.arduinoandroid.dialog;

import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import net.vnnz.arduinoandroid.R;
import net.vnnz.arduinoandroid.adapter.DeviceAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by viktoriala on 4/2/2015.
 */
public class DeviceSelectDialog  extends Dialog implements View.OnClickListener {

    public DeviceSelectDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_select_device);

        ListView deviceList = (ListView) findViewById(R.id.device_list);
        ArrayList<BluetoothDevice> arr = new ArrayList<BluetoothDevice>();

      // deviceList.setAdapter(new ArrayAdapter<String>());
        BluetoothAdapter mBlueAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBlueAdapter == null) {
            return;
        }

        Set<BluetoothDevice> paired = mBlueAdapter.getBondedDevices();
        if (paired.size() > 0) {
            for (BluetoothDevice device : paired) {
                arr.add(device);
            }
        }

        deviceList.setAdapter(new DeviceAdapter(getContext(), R.layout.list_row_devices, arr));
        Button scan = (Button) findViewById(R.id.btn_scan);
        scan.setTypeface(Typeface.createFromAsset(getContext().getAssets(), getContext().getString(R.string.font_digital_tech)));

    }

    @Override
    public void onClick(View v) {

    }
}
