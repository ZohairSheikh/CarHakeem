package info.umer.carhakeem.UI.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import info.umer.carhakeem.Helpers.Entities.Constants;
import info.umer.carhakeem.Helpers.Entities.Utils;
import info.umer.carhakeem.Helpers.FragmentHandler;
import info.umer.carhakeem.Interfaces.IBase;
import info.umer.carhakeem.Interfaces.OnFragmentInteractionListener;
import info.umer.carhakeem.Models.ApiCalls.apiCallSignUpRequest;
import info.umer.carhakeem.R;
import info.umer.carhakeem.UI.Activities.JoinNConnect;
import info.umer.carhakeem.UI.Custom.Button_N;
import info.umer.carhakeem.UI.Custom.EditText_N;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUp#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUp extends Base implements IBase, View.OnClickListener {


    Context mContext;

    private OnFragmentInteractionListener mListener;
    FragmentHandler fragmentHandler;

    private EditText_N etUserName;
    private EditText_N etMobileNo;
    private EditText_N etEmail;
    private EditText_N etPassword;
    private EditText_N etRetypePassword;
    private EditText_N etUserType;
    private Button_N btnSignUp;
    private Button btnUserType;


    String TAG = "firebase";
    private String mVerificationId;

    public FirebaseAuth mAuth;
    public PhoneAuthProvider.ForceResendingToken mResendToken;
    public PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    public SignUp() {
    }

    public SignUp(FragmentHandler _fragmentHandler) {
        fragmentHandler = _fragmentHandler;
    }


    public static SignUp newInstance(String param1, String param2) {
        SignUp fragment = new SignUp();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View _view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        callInitializer(this, _view);

        return _view;


    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void initializeControls() {

    }


    @Override
    public void apiCallBack(Object obj, String type) {

        if(type.equals(Constants.apiCallSignUp))
        {
            showToast("Sign Up Successfully");
            fragmentHandler.popStack();
        }

    }

    @Override
    public void initializeControls(View view) {
        ((JoinNConnect) getActivity()).AppBar(true);
        ((JoinNConnect) getActivity()).showActionButtonAppBar(true, false);
        ((JoinNConnect) getActivity()).AppBarTitle("Sign Up");


        etUserName = (EditText_N) view.findViewById(R.id.etUserName);
        etMobileNo = (EditText_N) view.findViewById(R.id.etMobileNo);
        etEmail = (EditText_N) view.findViewById(R.id.etEmail);
        etPassword = (EditText_N) view.findViewById(R.id.etPassword);
        etRetypePassword = (EditText_N) view.findViewById(R.id.etRetypePassword);
        etUserType = (EditText_N) view.findViewById(R.id.etUserType);
        btnSignUp = (Button_N) view.findViewById(R.id.btnSignUp);
        btnUserType = (Button) view.findViewById(R.id.btnUserType);


        btnSignUp.setOnClickListener(this);
        btnUserType.setOnClickListener(this);


        mAuth = FirebaseAuth.getInstance();
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                Log.d(TAG, "onVerificationCompleted:" + credential);

                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.w(TAG, "onVerificationFailed", e);

                showToast("Failed to Sign Up");
                fragmentHandler.popStack();

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                }

                // Show a message and update the UI
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d(TAG, "onCodeSent:" + verificationId);

                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;
            }
        };
        // [END phone_auth_callbacks]


    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (view == btnUserType) {
            PopupMenu pmUserType = new PopupMenu(_context, etUserType);
            pmUserType.getMenu().add("Customer");
            pmUserType.getMenu().add("Provider");
            pmUserType.show();
            pmUserType.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {

                    etUserType.setText(menuItem.getTitle());
                    return false;
                }
            });
        } else if (view == btnSignUp) {
            if (!etMobileNo.getFieldText().equals("") && !etEmail.getFieldText().equals("")
                    && !etPassword.getFieldText().equals("") && !etRetypePassword.getFieldText().equals("")
                    && !etUserName.getFieldText().equals("") && !etUserType.getFieldText().equals("")) {

                if (Utils.validateMobileNo(etMobileNo.getFieldText())) {
                    if (Utils.validateEmail(etEmail.getFieldText())) {

                        if (etPassword.getFieldText().equals(etRetypePassword.getFieldText())) {



                            apiCallSignUpRequest _req = new apiCallSignUpRequest(etMobileNo.getFieldText(), etEmail.getFieldText(), etUserName.getFieldText(), etPassword.getFieldText(), etUserType.getFieldText().toLowerCase());
                            restCall(Constants.apiCallSignUp, _req, true);
                        } else {
                            showToast("Password and Confirm password should be same");
                        }

                    } else {
                        showToast("Invalid Email Address");

                    }

                } else {
                    showToast("Invalid Mobile no");
                }
            } else {
                showToast("All Fields are mandatory");
            }
        }

    }

    @Override
    public void apiCallBackFailed(Object obj, String type) {

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(((JoinNConnect) getActivity()), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();

                            apiCallSignUpRequest _req = new apiCallSignUpRequest(etMobileNo.getFieldText(), etEmail.getFieldText(), etUserName.getFieldText(), etPassword.getFieldText(), etUserType.getFieldText());
                            restCall(Constants.apiCallSignUp, _req, true);

                            // Update UI
                        } else {

                            showToast("Failed to Sign Up");
                            fragmentHandler.popStack();
                           Log.w(TAG, "signInWithCredential:failure", task.getException());
                        }
                    }
                });
    }

}