package info.umer.carhakeem.UI.Fragments.Customer;

import android.content.Context;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import info.umer.carhakeem.Adapters.MyBookingRecycleAdapter;
import info.umer.carhakeem.Helpers.Entities.Constants;
import info.umer.carhakeem.Helpers.FragmentHandler;
import info.umer.carhakeem.Helpers.sharedPrefs;
import info.umer.carhakeem.Interfaces.IBase;
import info.umer.carhakeem.Interfaces.IMyBooking;
import info.umer.carhakeem.Interfaces.OnFragmentInteractionListener;
import info.umer.carhakeem.Models.ApiCalls.apiCallAppointmentStatusRequest;
import info.umer.carhakeem.Models.ApiCalls.apiCallMyAppointmentResponse;
import info.umer.carhakeem.Models.ApiCalls.apiCallMyAppointmentsRequest;
import info.umer.carhakeem.Models.ApiCalls.internals.apiCallMyAppointmentResponseOfReponse;
import info.umer.carhakeem.R;
import info.umer.carhakeem.UI.Activities.Home;
import info.umer.carhakeem.UI.Custom.TextView_N;
import info.umer.carhakeem.UI.Fragments.Base;
import info.umer.carhakeem.UI.Popups.invoice;
import info.umer.carhakeem.UI.Popups.ratingPopup;


public class MyBookings extends Base implements IBase, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {


    Context mContext;

    private OnFragmentInteractionListener mListener;
    FragmentHandler fragmentHandler;

    private TextView_N tvHeaderServices;
    private SwipeRefreshLayout swipeRefresh;
    private RecyclerView recyclerView;

    MyBookingRecycleAdapter adapter;


    public MyBookings() {
    }

    public MyBookings(FragmentHandler _fragmentHandler) {
        fragmentHandler = _fragmentHandler;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View _view = inflater.inflate(R.layout.fragment_my_bookings, container, false);
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
        swipeRefresh.setRefreshing(false);
        if (type.equals(Constants.apiCallMyAppointment)) {
            apiCallMyAppointmentResponse _res = (apiCallMyAppointmentResponse) obj;
            adapter = new MyBookingRecycleAdapter(mContext, _res.getResponse(), new IMyBooking() {
                @Override
                public void onClickStatusChange(apiCallMyAppointmentResponseOfReponse item) {

                    if (item.getStatus().equals("pending")) {
                        apiCallAppointmentStatusRequest _req = new apiCallAppointmentStatusRequest();
                        _req.setAppId(item.getId());
                        _req.setStatus("ongoing");
                        restCall(Constants.apiCallAppointmentStatus, _req, true);
                    } else if (item.getStatus().equals("ongoing")) {
                        apiCallAppointmentStatusRequest _req = new apiCallAppointmentStatusRequest();
                        _req.setAppId(item.getId());
                        _req.setStatus("completed");
                        restCall(Constants.apiCallAppointmentStatus, _req, true);
                    }
                    else if (item.getStatus().equals("completed")) {
                        if (sharedPrefs.getString(Constants.userType).equals("provider")) {
                            new invoice(getActivity());
                        } else {

                            new ratingPopup(getActivity());


                        }
                    }
                }
            });
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            recyclerView.setAdapter(adapter);
        } else if (type.equals(Constants.apiCallAppointmentStatus)) {
            apiCallMyAppointmentsRequest _req = new apiCallMyAppointmentsRequest();
            _req.setUserId(sharedPrefs.getString(Constants.userId));
            _req.setUserType(sharedPrefs.getString(Constants.userType));
            restCall(Constants.apiCallMyAppointment, _req, false);
            swipeRefresh.setOnRefreshListener(this);
            swipeRefresh.setRefreshing(true);
        }
    }

    @Override
    public void initializeControls(View view) {
        ((Home) getActivity()).showActionButtonAppBar(true, false);


        ((Home) getActivity()).AppBar(true);
        ((Home) getActivity()).AppBarTitle("My Bookings");

        tvHeaderServices = (TextView_N) view.findViewById(R.id.tvHeaderServices);
        swipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);


        apiCallMyAppointmentsRequest _req = new apiCallMyAppointmentsRequest();
        _req.setUserId(sharedPrefs.getString(Constants.userId));
        _req.setUserType(sharedPrefs.getString(Constants.userType));
        restCall(Constants.apiCallMyAppointment, _req, false);
        swipeRefresh.setOnRefreshListener(this);
        swipeRefresh.setRefreshing(true);

    }


    @Override
    public void onClick(View view) {
        super.onClick(view);


    }

    @Override
    public void apiCallBackFailed(Object obj, String type) {
        swipeRefresh.setRefreshing(false);

    }

    @Override
    public void onRefresh() {
        apiCallMyAppointmentsRequest _req = new apiCallMyAppointmentsRequest();
        _req.setUserId(sharedPrefs.getString(Constants.userId));
        _req.setUserType(sharedPrefs.getString(Constants.userType));
        restCall(Constants.apiCallMyAppointment, _req, false);
    }
}