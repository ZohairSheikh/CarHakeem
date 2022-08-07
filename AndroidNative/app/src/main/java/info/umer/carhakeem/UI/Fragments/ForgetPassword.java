package info.umer.carhakeem.UI.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import info.umer.carhakeem.Helpers.FragmentHandler;
import info.umer.carhakeem.Interfaces.IBase;
import info.umer.carhakeem.Interfaces.OnFragmentInteractionListener;
import info.umer.carhakeem.R;
import info.umer.carhakeem.UI.Activities.JoinNConnect;
import info.umer.carhakeem.UI.Custom.Button_N;
import info.umer.carhakeem.UI.Custom.EditText_N;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ForgetPassword#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ForgetPassword extends Base implements IBase, View.OnClickListener {


    Context mContext;

    private OnFragmentInteractionListener mListener;
    FragmentHandler fragmentHandler;

    private EditText_N etMobileNo;
    private Button_N btnForgetPassword;






    public ForgetPassword() {
    }

    public ForgetPassword(FragmentHandler _fragmentHandler) {
        fragmentHandler = _fragmentHandler;
    }


    public static ForgetPassword newInstance(String param1, String param2) {
        ForgetPassword fragment = new ForgetPassword();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View _view = inflater.inflate(R.layout.fragment_forget_password, container, false);
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


    }

    @Override
    public void initializeControls(View view) {
        ((JoinNConnect) getActivity()).AppBar(true);
        ((JoinNConnect) getActivity()).showActionButtonAppBar(true,false);
        ((JoinNConnect) getActivity()).AppBarTitle("Forget Password");


        etMobileNo = (EditText_N) view.findViewById(R.id.etMobileNo);
        btnForgetPassword = (Button_N) view.findViewById(R.id.btnForgetPassword);


        btnForgetPassword.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        super.onClick(view);

        if(view== btnForgetPassword)
        {
            info.umer.carhakeem.UI.Popups.ForgetPassword custom = new info.umer.carhakeem.UI.Popups.ForgetPassword(getContext());
            custom.show();

        }
    }

    @Override
    public void apiCallBackFailed(Object obj, String type) {

    }
}