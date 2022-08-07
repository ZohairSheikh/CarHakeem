package info.umer.carhakeem.UI.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import info.umer.carhakeem.Helpers.Entities.Constants;
import info.umer.carhakeem.Helpers.FragmentHandler;
import info.umer.carhakeem.Helpers.sharedPrefs;
import info.umer.carhakeem.Interfaces.IBase;
import info.umer.carhakeem.Interfaces.OnFragmentInteractionListener;
import info.umer.carhakeem.Models.ApiCalls.apiCallLoginRequest;
import info.umer.carhakeem.Models.ApiCalls.apiCallLoginResponse;
import info.umer.carhakeem.R;
import info.umer.carhakeem.UI.Activities.Home;
import info.umer.carhakeem.UI.Activities.JoinNConnect;
import info.umer.carhakeem.UI.Custom.Button_N;
import info.umer.carhakeem.UI.Custom.EditText_N;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Login#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Login extends Base implements IBase, View.OnClickListener {


    Context mContext;

    private OnFragmentInteractionListener mListener;
    FragmentHandler fragmentHandler;

    private EditText_N etMobileNo;
    private EditText_N etPassword;
    private Button_N btnForgetPassword;
    private Button_N btnSignIn;
    private Button_N btnSignUp;


    public Login() {
    }

    public Login(FragmentHandler _fragmentHandler) {
        fragmentHandler = _fragmentHandler;
    }


    public static Login newInstance(String param1, String param2) {
        Login fragment = new Login();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View _view = inflater.inflate(R.layout.fragment_login, container, false);
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

        if(type.equals(Constants.apiCallLogin))
        {
            apiCallLoginResponse res = (apiCallLoginResponse) obj;



            sharedPrefs.putString(Constants.userName, res.getResponse().getUserName());

            sharedPrefs.putString(Constants.userMobileNo, res.getResponse().getMobileNo());
            sharedPrefs.putString(Constants.userEmail, res.getResponse().getEmailAddress());
            sharedPrefs.putString(Constants.userAccessToken,res.getResponse().getAccessToken());

            sharedPrefs.putString(Constants.userId,res.getResponse().getId());
            sharedPrefs.putLong(Constants.userLoginTime, System.currentTimeMillis());
            sharedPrefs.putString(Constants.userType, res.getResponse().getUserType());


            sharedPrefs.putBool(Constants.userLoggedIn, true);
            startActivity(new Intent(getContext(), Home.class));
            getActivity().finish();
        }

    }

    @Override
    public void initializeControls(View view) {
        ((JoinNConnect) getActivity()).showActionButtonAppBar(true, false);

        etMobileNo = (EditText_N) view.findViewById(R.id.etMobileNo);
        etPassword = (EditText_N) view.findViewById(R.id.etPassword);
        btnForgetPassword = (Button_N) view.findViewById(R.id.btnForgetPassword);
        btnSignIn = (Button_N) view.findViewById(R.id.btnSignIn);
        btnSignUp = (Button_N) view.findViewById(R.id.btnSignUp);


        btnSignUp.setOnClickListener(this);
        btnSignIn.setOnClickListener(this);
        btnForgetPassword.setOnClickListener(this);

        ((JoinNConnect) getActivity()).AppBar(false);


    }

    @Override
    public void onClick(View view) {
        super.onClick(view);

        if (view == btnSignUp) {
            fragmentHandler.addReplaceFragmentWithAnimation(new SignUp(fragmentHandler), true, true, R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);

        } else if (view == btnForgetPassword) {
            fragmentHandler.addReplaceFragmentWithAnimation(new ForgetPassword(fragmentHandler), true, true, R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);

        } else if (view == btnSignIn) {
            if (!etMobileNo.getFieldText().equals("") && !etPassword.getFieldText().equals("")) {

                apiCallLoginRequest _req = new apiCallLoginRequest(etMobileNo.getFieldText(), etPassword.getFieldText());
                restCall(Constants.apiCallLogin, _req, true);

            } else {
                showToast("All Fields are mandatory");
            }

        }

    }

    @Override
    public void apiCallBackFailed(Object obj, String type) {

    }
}