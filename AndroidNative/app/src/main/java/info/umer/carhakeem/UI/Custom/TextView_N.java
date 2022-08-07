package info.umer.carhakeem.UI.Custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.res.ResourcesCompat;

import info.umer.carhakeem.R;


public class TextView_N extends AppCompatTextView {

    Boolean isSpannable= false;
    TypedArray ta;


    public TextView_N(Context context) {
        super(context);

    }

    public TextView_N(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


        ta= context.obtainStyledAttributes(attrs, R.styleable.TextView);
        init(context,ta);
    }

    public TextView_N(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ta= context.obtainStyledAttributes(attrs, R.styleable.TextView);
        init(context,ta);
    }

    private void init(Context context, TypedArray taButton) {


        if (ta != null) {

            Log.d("ButtonCheck", "init: ");

        }
        Typeface tf = ResourcesCompat.getFont(context, R.font.calibri_regular);
        setTypeface(tf);




    }



}