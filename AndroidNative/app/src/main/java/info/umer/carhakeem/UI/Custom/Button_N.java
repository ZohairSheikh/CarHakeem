package info.umer.carhakeem.UI.Custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.res.ResourcesCompat;

import info.umer.carhakeem.R;


public class Button_N extends AppCompatButton {




    public Button_N(Context context) {
        super(context);

    }

    public Button_N(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray taButton = context.obtainStyledAttributes(attrs, R.styleable.Button);
        init(taButton,context);
    }

    public Button_N(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray taButton = context.obtainStyledAttributes(attrs, R.styleable.Button);
        init(taButton,context);




    }



    private void init(TypedArray taButton, Context context) {

        setAllCaps(false);
        setTextSize(20);
        if (taButton != null) {

            Log.i("ButtonCheck", "init: ");
            int backColor  = taButton.getInteger(R.styleable.Button_background_color, 0);

            if(backColor == -1) {
                setBackgroundColor(Color.TRANSPARENT);
                setTextColor(getResources().getColorStateList(R.color.black));
            }
            else if(backColor == 0) {
                setBackgroundColor(getResources().getColor(R.color.purple_200));
                setTextColor(Color.WHITE);
            }
            else if(backColor == 1)
            {

            }

        }


        Typeface tf = ResourcesCompat.getFont(context, R.font.calibri_regular);

        setTypeface(tf);
    }
}