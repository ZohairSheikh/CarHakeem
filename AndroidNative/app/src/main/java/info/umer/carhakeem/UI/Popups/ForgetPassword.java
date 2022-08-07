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

import com.mukesh.OnOtpCompletionListener;
import com.mukesh.OtpView;

import info.umer.carhakeem.R;
import info.umer.carhakeem.UI.Custom.Button_N;
import info.umer.carhakeem.UI.Custom.EditText_N;

public class ForgetPassword extends Dialog implements View.OnClickListener {

    Context context;

    private OtpView otpCode;
    private EditText_N etPassword;
    private EditText_N etRetypePassword;
    private Button_N btnChangePassword;
    public ForgetPassword(Context context) {
      super(context);

        this.context = context;

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCanceledOnTouchOutside(true);
        setContentView(R.layout.dialog_forget_password);
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
            etPassword = (EditText_N) findViewById(R.id.etPassword);
            etRetypePassword = (EditText_N) findViewById(R.id.etRetypePassword);
            btnChangePassword = (Button_N) findViewById(R.id.btnChangePassword);

        otpCode.setOtpCompletionListener(new OnOtpCompletionListener() {
            @Override
            public void onOtpCompleted(String otp) {

            }
        });




        show();
    }

    @Override
    public void onClick(View view) {

    }
}
