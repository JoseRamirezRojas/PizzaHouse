package com.ciencias.tarea2;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.NumberPicker;

import androidx.appcompat.view.ContextThemeWrapper;

public class ThemedNumberPicker extends NumberPicker {

    public ThemedNumberPicker(Activity context) {
        this(context, null);
    }


    public ThemedNumberPicker(Context context, AttributeSet attrs) {
        // wrap the current context in the style we defined before
        super(new ContextThemeWrapper(context, R.style.NumberPickerTextColorStyle), attrs);
    }
}
