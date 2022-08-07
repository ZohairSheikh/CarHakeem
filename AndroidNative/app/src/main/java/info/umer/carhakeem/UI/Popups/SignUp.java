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
import android.widget.TextView;
import android.widget.Toast;

import com.mukesh.OnOtpCompletionListener;
import com.mukesh.OtpView;

import info.umer.carhakeem.Interfaces.ISignUp;
import info.umer.carhakeem.R;
import info.umer.carhakeem.UI.Custom.Button_N;

public class SignUp extends Dialog implements View.OnClickListener {

    Context context;
    TextView tvText;
    String msg;
    ISignUp _ISignUp;

    private OtpView otpCode;

    private Button_N btnSignUp;
    public SignUp(Context context, ISignUp _ISignUp ) {
        super(context);
        this._ISignUp = _ISignUp;

        this.context = context;

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCanceledOnTouchOutside(true);
        setContentView(R.layout.dialog_sign_up);
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




        otpCode = (OtpView) findViewById(R.id.otpCode);

        btnSignUp = (Button_N) findViewById(R.id.btnSignUp);

        otpCode.setOtpCompletionListener(new OnOtpCompletionListener() {
            @Override
            public void onOtpCompleted(String otp) {

            }
        });


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(otpCode.getText().toString().length() == 6)
                {
                    _ISignUp.onClickSubmit(otpCode.getText().toString());
                }
                else
                {
                    Toast.makeText(context, "Incomplete OTP", Toast.LENGTH_SHORT).show();

                }            }
        });


        show();
    }

    @Override
    public void onClick(View view) {

    }
}