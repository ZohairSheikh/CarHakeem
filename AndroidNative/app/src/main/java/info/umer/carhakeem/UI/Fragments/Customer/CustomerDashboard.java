package info.umer.carhakeem.UI.Fragments.Customer;

import android.app.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import info.umer.carhakeem.Adapters.ProvidersRecyclerAdapter;
import info.umer.carhakeem.Helpers.Entities.Constants;
import info.umer.carhakeem.Helpers.FragmentHandler;
import info.umer.carhakeem.Helpers.sharedPrefs;
import info.umer.carhakeem.Interfaces.IBase;
import info.umer.carhakeem.Interfaces.IProviderRecycle;
import info.umer.carhakeem.Interfaces.OnFragmentInteractionListener;
import info.umer.carhakeem.Listners.PaginationListener;
import info.umer.carhakeem.Models.ApiCalls.apiCallMakeAppointmentRequest;
import info.umer.carhakeem.Models.ApiCalls.apiCallMakeAppointmentResponse;
import info.umer.carhakeem.Models.ApiCalls.apiCallProvidersRequest;
import info.umer.carhakeem.Models.ApiCalls.apiCallProvidersResponse;
import info.umer.carhakeem.Models.ApiCalls.internals.Provider;
import info.umer.carhakeem.Models.ApiCalls.internals.Providers;
import info.umer.carhakeem.R;
import info.umer.carhakeem.UI.Activities.Home;
import info.umer.carhakeem.UI.Custom.EditText_N;
import info.umer.carhakeem.UI.Fragments.Base;

import static info.umer.carhakeem.Listners.PaginationListener.PAGE_START;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;

public class CustomerDashboard extends Base implements IBase, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {


    Context mContext;

    private OnFragmentInteractionListener mListener;
    FragmentHandler fragmentHandler;

    RecyclerView mRecyclerView;

    SwipeRefreshLayout swipeRefresh;
    private ProvidersRecyclerAdapter adapter;
    private int currentPage = PAGE_START;
    private boolean isLastPage = false;
    private int totalPage = 10;
    private boolean isLoading = false;
    int itemCount = 0;

    EditText_N etSearch;

    public CustomerDashboard() {
    }

    public CustomerDashboard(FragmentHandler _fragmentHandler) {
        fragmentHandler = _fragmentHandler;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View _view = inflater.inflate(R.layout.fragment_customer_dashboard, container, false);
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
        if (type.equals(Constants.apiCallMakeAppointment)) {
            apiCallMakeAppointmentResponse res = (apiCallMakeAppointmentResponse) obj;
            showToast(res.getMessage());


        } else {
            swipeRefresh.setRefreshing(false);
            apiCallProvidersResponse res = (apiCallProvidersResponse) obj;
            totalPage = res.getTotalPages();


            final ArrayList<Provider> items = new ArrayList<>();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < res.getProviders().size(); i++) {
                        itemCount++;
//                    Provider postItem = new Provider();
//                    postItem.set(getString(R.string.text_title) + itemCount);
//                    postItem.setDescription(getString(R.string.text_description));
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
        etSearch = (EditText_N) view.findViewById(R.id.etSearch);
        swipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);


        swipeRefresh.setOnRefreshListener(this);
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new ProvidersRecyclerAdapter(new ArrayList<>(), mContext, new IProviderRecycle() {
            @Override
            public void onClickBookOnline(Providers item) {

                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

                                String date =dayOfMonth+"/"+(++monthOfYear)+"/"+year + " ";

                                TimePickerDialog tod = TimePickerDialog.newInstance(new TimePickerDialog.OnTimeSetListener() {
                                                                                        @Override
                                                                                        public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {

                                                                                            String hourString = hourOfDay < 10 ? "0"+hourOfDay : ""+hourOfDay;
                                                                                            String minuteString = minute < 10 ? "0"+minute : ""+minute;
                                                                                            String secondString = second < 10 ? "0"+second : ""+second;
                                                                                            String time = hourString+":"+minuteString;

                                                                                            Log.i("time", "onTimeSet: "+date + time);



                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage("Are you sure? you want to book appointment with " + item.getProviderName() + " on "+ date + time).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        apiCallMakeAppointmentRequest _req = new apiCallMakeAppointmentRequest();
                        _req.setStatus("pending");
                        _req.setProviderId(item.getId());
                        _req.setUserId(sharedPrefs.getString(Constants.userId));
                        _req.setProviderUserId(item.getUserId());
                        _req.setAppointmentDate(date + time);

                       restCall(Constants.apiCallMakeAppointment,_req,true);
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
                                                                                        }
                                                                                    }, now.get(Calendar.HOUR_OF_DAY),
                                        now.get(Calendar.MINUTE),
                                        now.get(Calendar.SECOND), false);

                                tod.show(getFragmentManager(), "");
                            }
                        },
                        now.get(Calendar.YEAR), // Initial year selection
                        now.get(Calendar.MONTH), // Initial month selection
                        now.get(Calendar.DAY_OF_MONTH) // Inital day selection
                );
// If you're calling this from a support Fragment
                dpd.show(getFragmentManager(), "Datepickerdialog");



            }
        });
        mRecyclerView.setAdapter(adapter);

        swipeRefresh.setRefreshing(true);
        restCall(Constants.apiCallProviders, new apiCallProvidersRequest(10, currentPage, etSearch.getText().toString()), false);

        mRecyclerView.addOnScrollListener(new PaginationListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage++;
                etSearch.setText("");
//                doApiCall();

                swipeRefresh.setRefreshing(true);
                restCall(Constants.apiCallProviders, new apiCallProvidersRequest(10, currentPage, etSearch.getText().toString()), false);

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


        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void doApiCall() {
        final ArrayList<Provider> items = new ArrayList<>();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    itemCount++;
                    Provider postItem = new Provider();
//                    postItem.setTitle(getString(R.string.text_title) + itemCount);
//                    postItem.setDescription(getString(R.string.text_description));
                    items.add(postItem);


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
        }, 500);
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
        etSearch.setText("");
        itemCount = 0;
        currentPage = PAGE_START;
        isLastPage = false;
        adapter.clear();
        restCall(Constants.apiCallProviders, new apiCallProvidersRequest(10, currentPage, etSearch.getText().toString()), false);
    }
}