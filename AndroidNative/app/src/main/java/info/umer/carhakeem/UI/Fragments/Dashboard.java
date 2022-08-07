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
import info.umer.carhakeem.UI.Activities.Home;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Dashboard#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Dashboard extends Base implements IBase, View.OnClickListener {


    Context mContext;

    private OnFragmentInteractionListener mListener;
    FragmentHandler fragmentHandler;





    public Dashboard() {
    }


    public static Dashboard newInstance(String param1, String param2) {
        Dashboard fragment = new Dashboard();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View _view = inflater.inflate(R.layout.fragment_dashboard, container, false);
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
        ((Home) getActivity()).AppBar(true);
        ((Home) getActivity()).showActionButtonAppBar(true,true);
        ((Home) getActivity()).AppBarTitle("Dashboard");





    }

    @Override
    public void onClick(View view) {
        super.onClick(view);


    }

    @Override
    public void apiCallBackFailed(Object obj, String type) {

    }
}