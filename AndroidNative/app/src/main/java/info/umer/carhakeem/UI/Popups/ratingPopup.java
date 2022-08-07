package info.umer.carhakeem.UI.Popups;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RatingBar;
import android.widget.TextView;

import com.mukesh.OnOtpCompletionListener;
import com.mukesh.OtpView;

import info.umer.carhakeem.R;
import info.umer.carhakeem.UI.Custom.Button_N;
import info.umer.carhakeem.UI.Custom.EditText_N;

public class ratingPopup extends Dialog implements View.OnClickListener {

    Context context;
    TextView tvText;
    String msg;

    private RatingBar rating;
    private Button_N btnSetRating;




    public ratingPopup(Context context) {
        super(context);

        this.context = context;

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCanceledOnTouchOutside(true);
        setContentView(R.layout.rating_dialog);
        setCanceledOnTouchOutside(false);

        Window window = getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.CENTER_HORIZONTAL;
        window.setAttributes(wlp);



        Display display = getWindow().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        width = (int) (width * 0.9);
        getWindow()
                .setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT);



        rating = (RatingBar) findViewById(R.id.rating);
        btnSetRating = (Button_N) findViewById(R.id.btnSetRating);


        rating.setNumStars(5);


        btnSetRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        show();
    }

    @Override
    public void onClick(View view) {

    }
}