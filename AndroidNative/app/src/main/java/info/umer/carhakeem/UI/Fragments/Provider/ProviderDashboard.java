package info.umer.carhakeem.UI.Fragments.Provider;

import static info.umer.carhakeem.Listners.PaginationListener.PAGE_START;

import android.content.Context;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import info.umer.carhakeem.Adapters.MyServicesRecycleAdapter;
import info.umer.carhakeem.Helpers.Entities.Constants;
import info.umer.carhakeem.Helpers.FragmentHandler;
import info.umer.carhakeem.Helpers.sharedPrefs;
import info.umer.carhakeem.Interfaces.IBase;
import info.umer.carhakeem.Interfaces.OnFragmentInteractionListener;
import info.umer.carhakeem.Listners.PaginationListener;
import info.umer.carhakeem.Models.ApiCalls.apiCallMakeAppointmentResponse;
import info.umer.carhakeem.Models.ApiCalls.apiCallMyServicesRequest;
import info.umer.carhakeem.Models.ApiCalls.apiCallMyServicesResponse;
import info.umer.carhakeem.Models.ApiCalls.internals.Providers;
import info.umer.carhakeem.R;
import info.umer.carhakeem.UI.Activities.Home;
import info.umer.carhakeem.UI.Fragments.Base;


public class ProviderDashboard extends Base implements IBase, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {



    Context mContext;

    private OnFragmentInteractionListener mListener;
    FragmentHandler fragmentHandler;

    RecyclerView mRecyclerView;

    SwipeRefreshLayout swipeRefresh;
    private MyServicesRecycleAdapter adapter;
    private int currentPage = PAGE_START;
    private boolean isLastPage = false;
    private int totalPage = 10;
    private boolean isLoading = false;
    int itemCount = 0;

    FloatingActionButton btnAddProvider;


    public ProviderDashboard() {
    }

    public ProviderDashboard(FragmentHandler _fragmentHandler) {
        fragmentHandler = _fragmentHandler;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View _view = inflater.inflate(R.layout.fragment_provider_dashboard, container, false);
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
        if(type.equals(Constants.apiCallMakeAppointment)){
            apiCallMakeAppointmentResponse res= (apiCallMakeAppointmentResponse)obj;
            showToast(res.getMessage());


        }
        else {
            swipeRefresh.setRefreshing(false);
            apiCallMyServicesResponse res = (apiCallMyServicesResponse) obj;
            totalPage = res.getTotalPages();


            final ArrayList<Providers> items = new ArrayList<>();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < res.getProviders().size(); i++) {
                        itemCount++;
                        items.add(res.getProviders().get(i));
                    }
                    /**
                     * manage progress view
                     */
                    if (currentPage != PAGE_START) adapter.removeLoading();
                    adapter.addItems(items);
                    swipeRefresh.setRefreshing(false);
                    // check weather is last page or not
                    if (currentPage < totalPage) {
                        adapter.addLoading();
                    } else {
                        isLastPage = true;
                    }
                    isLoading = false;
                }
            }, 1);

        }

    }

    @Override
    public void initializeControls(View view) {
        ((Home) getActivity()).AppBar(true);
        ((Home) getActivity()).showActionButtonAppBar(true, true);
        ((Home) getActivity()).AppBarTitle("Dashboard");


        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        swipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);
        btnAddProvider = (FloatingActionButton) view.findViewById(R.id.btnAddProvider);


        swipeRefresh.setOnRefreshListener(this);
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new MyServicesRecycleAdapter(new ArrayList<>(), mContext,null);
        mRecyclerView.setAdapter(adapter);

        swipeRefresh.setRefreshing(true);
        restCall(Constants.apiCallMyServices, new apiCallMyServicesRequest(sharedPrefs.getString(Constants.userId),currentPage,10), false);

        mRecyclerView.addOnScrollListener(new PaginationListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage++;

                swipeRefresh.setRefreshing(true);
                restCall(Constants.apiCallMyServices, new apiCallMyServicesRequest(sharedPrefs.getString(Constants.userId),currentPage,10), false);

            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });



        btnAddProvider.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);

        if(view == btnAddProvider)
        {
            fragmentHandler.addReplaceFragmentWithAnimation(new CreateProvider(fragmentHandler), true, true, R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);

        }



    }

    @Override
    public void apiCallBackFailed(Object obj, String type) {
        swipeRefresh.setRefreshing(false);

    }

    @Override
    public void onRefresh() {
        itemCount = 0;
        currentPage = PAGE_START;
        isLastPage = false;
        adapter.clear();
        restCall(Constants.apiCallMyServices, new apiCallMyServicesRequest(sharedPrefs.getString(Constants.userId),currentPage,10 ), false);

    }
}