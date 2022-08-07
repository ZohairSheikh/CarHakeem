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
import info.umer.carhakeem.Models.ApiCalls.apiCallLoginResponse;
import info.umer.carhakeem.R;
import info.umer.carhakeem.UI.Activities.Home;
import info.umer.carhakeem.UI.Custom.EditText_N;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile extends Base implements IBase, View.OnClickListener {


    Context mContext;

    private OnFragmentInteractionListener mListener;
    FragmentHandler fragmentHandler;

    private EditText_N etUserName;
    private EditText_N etMobileNo;
    private EditText_N etEmail;
    private EditText_N etUserType;

    public Profile() {
    }

    public Profile(FragmentHandler _fragmentHandler) {
        fragmentHandler = _fragmentHandler;
    }


    public static Profile newInstance(String param1, String param2) {
        Profile fragment = new Profile();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View _view = inflater.inflate(R.layout.fragment_profile, container, false);
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
        ((Home) getActivity()).showActionButtonAppBar(true, false);


        ((Home) getActivity()).AppBar(true);
        ((Home) getActivity()).AppBarTitle("Profile");

        etUserName = (EditText_N) view.findViewById(R.id.etUserName);
        etMobileNo = (EditText_N) view.findViewById(R.id.etMobileNo);
        etEmail = (EditText_N) view.findViewById(R.id.etEmail);
        etUserType = (EditText_N) view.findViewById(R.id.etUserType);



        etUserName.setText(sharedPrefs.getString(Constants.userName));
        etMobileNo.setText(sharedPrefs.getString(Constants.userMobileNo));
        etEmail.setText(sharedPrefs.getString(Constants.userEmail));
        etUserType.setText(sharedPrefs.getString(Constants.userType));

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);




    }

    @Override
    public void apiCallBackFailed(Object obj, String type) {

    }
}