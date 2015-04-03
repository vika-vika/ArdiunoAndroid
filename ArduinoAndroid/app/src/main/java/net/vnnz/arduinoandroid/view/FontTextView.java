package net.vnnz.arduinoandroid.view;

/**
 * Created by viktoriala on 4/3/2015.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import net.vnnz.arduinoandroid.R;


public class FontTextView extends TextView {


    public FontTextView(final Context context) {
        this(context, null);
    }

    public FontTextView(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FontTextView(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);

        // prevent exception in Android Studio
        if (this.isInEditMode()) {
            return;
        }

        TypedArray array = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.FontTextView, defStyle, 0);

        int fontRef = array.getResourceId(R.styleable.FontTextView_font, 0);

        if (fontRef != 0) {
            Typeface font = Typeface.createFromAsset(getContext().getAssets(), getContext().getString(fontRef));
            setTypeface(font);
        }
        array.recycle();
    }

}