package info.umer.carhakeem.UI.Custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.res.ResourcesCompat;

import info.umer.carhakeem.R;



public class EditText_N extends AppCompatEditText {

    public EditText_N(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray taEditText = context.obtainStyledAttributes(attrs, R.styleable.EditText);
        init(context,taEditText);

    }
    public EditText_N(Context context) {
        super(context);


    }

    public EditText_N(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray taEditText= context.obtainStyledAttributes(attrs, R.styleable.EditText);
        init(context,taEditText);
    }
    private void init(Context context, TypedArray taEditText) {
        Typeface tf = ResourcesCompat.getFont(context, R.font.calibri_regular);

        boolean border = taEditText.getBoolean(R.styleable.EditText_border, true) ;


        setMaxLines(1);
        setTypeface(tf);

        if (border) {
            setBackground(getResources().getDrawable(R.drawable.shape_for_edittext));
            setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            setPadding(10,0,10,0);
        }

        setTextColor(getResources().getColor(R.color.black));





    }

   public String getFieldText()
    {
        return this.getText().toString();
    }
}
