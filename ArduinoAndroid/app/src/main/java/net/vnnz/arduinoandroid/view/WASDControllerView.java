package net.vnnz.arduinoandroid.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import net.vnnz.arduinoandroid.R;

/**
 * Created by viktoriala on 4/10/2015.
 */
public class WASDControllerView extends LinearLayout implements View.OnClickListener {

    public static final int COMMAND_UP    = 1;
    public static final int COMMAND_LEFT  = 2;
    public static final int COMMAND_RIGHT = 3;
    public static final int COMMAND_DOWN  = 4;

    public static final int COMMAND_ACTION = 10;
    private WASDListener listener;

    public WASDControllerView(Context context) {
        super(context);
        initView(context);
    }

    public WASDControllerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public WASDControllerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        // prevent exception in Android Studio
        if (this.isInEditMode()) {
            return;
        }

       initView(context);
    }

    private void initView(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.view_wasd, this, true);
        v.findViewById(R.id.iv_space).setOnClickListener(this);
        v.findViewById(R.id.iv_down).setOnClickListener(this);
        v.findViewById(R.id.iv_up).setOnClickListener(this);
        v.findViewById(R.id.iv_left).setOnClickListener(this);
        v.findViewById(R.id.iv_right).setOnClickListener(this);
    }


    public void addOnItemClickListener(WASDListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
       int command = 0;
       switch (v.getId()) {
           case R.id.iv_space:
               command = COMMAND_ACTION;
               break;
           case R.id.iv_down:
               command = COMMAND_DOWN;
               break;
           case R.id.iv_left:
               command = COMMAND_LEFT;
               break;
           case R.id.iv_up:
               command = COMMAND_UP;
               break;
           case R.id.iv_right:
               command = COMMAND_RIGHT;
               break;
       }

       if (listener != null) {
            listener.onWASDItemClicked(command);
       }
    }

    public interface WASDListener {
        public void onWASDItemClicked(int item);
    }
}
