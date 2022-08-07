package info.umer.carhakeem.UI.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import info.umer.carhakeem.Helpers.AppLog;
import info.umer.carhakeem.Helpers.Http.callApi;
import info.umer.carhakeem.Interfaces.IBase;
import info.umer.carhakeem.Interfaces.OnFragmentInteractionListener;

public class Base extends Fragment implements View.OnClickListener {

    private static final String TAG = Base.class.getSimpleName();
    private OnFragmentInteractionListener mListener;
    private boolean doLog = false;
    IBase ibase;
    Context _context;

    public Base() {

    }


    public boolean onBackPressed() {

        return false;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (doLog) {
            AppLog.d(TAG, "onCreate: " + this.getClass().getName());
        }
    }


    protected void callInitializer(IBase ibase_, View view) {
        ibase = ibase_;
        ibase.initializeControls(view);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (doLog)
            AppLog.d(TAG, "onCreateView: " + this.getClass().getName());
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onClick(View view) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        _context = context;
        if (doLog) {
            AppLog.d(TAG, "onAttach: " + this.getClass().getName());
        }
    }



    public void restCall(final String type,  Object cv, boolean loader)
    {
        closeKeyBoard();
        callApi callObj = new callApi(ibase,_context);
        callObj.apiCal(type,cv,loader);
    }

    public void closeKeyBoard() {


        final InputMethodManager imm;
        try {
            imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (doLog)
            AppLog.d(TAG, "onResume: " + this.getClass().getName());

    }

    @Override
    public void onPause() {
        super.onPause();
        if (doLog)
            AppLog.d(TAG, "onPause: " + this.getClass().getName());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (doLog)
            AppLog.d(TAG, "onDestroyView: " + this.getClass().getName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (doLog)
            AppLog.d(TAG, "onDestroy: " + this.getClass().getName());
    }

    public void showToast(String Msg)
    {
        Toast.makeText(_context, Msg, Toast.LENGTH_SHORT).show();
    }




    /*
     * detach fragment interaction listener
     * */
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        if (doLog)
            AppLog.d(TAG, "onDetach: " + this.getClass().getName());
    }




}