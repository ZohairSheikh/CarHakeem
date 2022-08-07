package info.umer.carhakeem.UI.Fragments.Provider;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import info.umer.carhakeem.Helpers.Entities.App;
import info.umer.carhakeem.Helpers.Entities.Constants;
import info.umer.carhakeem.Helpers.FragmentHandler;
import info.umer.carhakeem.Helpers.sharedPrefs;
import info.umer.carhakeem.Interfaces.IBase;
import info.umer.carhakeem.Interfaces.OnFragmentInteractionListener;
import info.umer.carhakeem.Models.ApiCalls.apiCallCreateProviderRequest;
import info.umer.carhakeem.Models.ApiCalls.apiCallProviderTypesResponse;
import info.umer.carhakeem.R;
import info.umer.carhakeem.UI.Activities.Home;
import info.umer.carhakeem.UI.Custom.Button_N;
import info.umer.carhakeem.UI.Custom.EditText_N;
import info.umer.carhakeem.UI.Fragments.Base;


public class CreateProvider extends Base implements IBase, View.OnClickListener {


    Context mContext;

    private OnFragmentInteractionListener mListener;
    FragmentHandler fragmentHandler;


    private EditText_N etProviderName;
    private EditText_N etAddress;
    private EditText_N etCategory;
    private Button btnCategory;
    private EditText_N etMobileNo;
    private EditText_N etDistrict;
    private EditText_N etArea;
    private EditText_N etRate;


    Button_N btnCreateProvider;


    public CreateProvider() {
    }

    public CreateProvider(FragmentHandler _fragmentHandler) {
        fragmentHandler = _fragmentHandler;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View _view = inflater.inflate(R.layout.fragment_create_provider, container, false);
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

    apiCallProviderTypesResponse res;
    @Override
    public void apiCallBack(Object obj, String type) {

        if(type.equals(Constants.apiCallProviderType))
        {
             res = (apiCallProviderTypesResponse)obj;

        }
        else if(type.equals(Constants.apiCallCreateProvider))
        {
           fragmentHandler.popStack();

        }

    }
    List<Address> addresses;;

    @Override
    public void initializeControls(View view) {
        ((Home) getActivity()).AppBar(true);
        ((Home) getActivity()).showActionButtonAppBar(true, false);
        ((Home) getActivity()).AppBarTitle("Create Provider");


        etProviderName = (EditText_N) view.findViewById(R.id.etProviderName);
        etAddress = (EditText_N) view.findViewById(R.id.etAddress);
        etCategory = (EditText_N) view.findViewById(R.id.etCategory);
        btnCategory = (Button) view.findViewById(R.id.btnCategory);
        btnCreateProvider = (Button_N) view.findViewById(R.id.btnCreateProvider);
        etMobileNo = (EditText_N) view.findViewById(R.id.etMobileNo);
        etDistrict = (EditText_N) view.findViewById(R.id.etDistrict);
        etArea = (EditText_N) view.findViewById(R.id.etArea);
        etRate = (EditText_N) view.findViewById(R.id.etRate);



        restCall(Constants.apiCallProviderType, null, true);

        Geocoder geocoder = new Geocoder(getActivity(), Locale.ENGLISH);

        try {
           addresses = geocoder.getFromLocation(App.get()._mapHelper.ltlng.latitude, App.get()._mapHelper.ltlng.longitude, 1);



           etAddress.setText(addresses.get(0).getAddressLine(0));
            etArea.setText(addresses.get(0).getSubLocality());


        } catch (IOException e) {
            e.printStackTrace();
        }
        ;





        



        btnCategory.setOnClickListener( this );
        btnCreateProvider.setOnClickListener( this );



    }

    @Override
    public void onClick(View view) {
        super.onClick(view);

        if(view == btnCategory)
        {
            PopupMenu popup = new PopupMenu(getActivity(), btnCategory);
            //Inflating the Popup using xml file


            for (String var : res.getResponse())
            {
                popup.getMenu().add(var);
            }




            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                public boolean onMenuItemClick(MenuItem item) {

                    etCategory.setText(item.getTitle());
                    return true;
                }
            });

            popup.show();//showing popup menu
        }
        else if(view==btnCreateProvider)
        {
            if(!etProviderName.getFieldText().equals("") && !etAddress.getFieldText().equals("")  && !etCategory.getFieldText().equals("")
                    && !etMobileNo.getFieldText().equals("")   && !etDistrict.getFieldText().equals("") && !etArea.getFieldText().equals("")
                    && !etRate.getFieldText().equals("")
            )
            {
                apiCallCreateProviderRequest _req = new apiCallCreateProviderRequest(etProviderName.getFieldText(),etAddress.getFieldText(),etCategory.getFieldText(),
                        etMobileNo.getFieldText(),   sharedPrefs.getString(Constants.userId),etDistrict.getFieldText(),etArea.getFieldText(),App.get()._mapHelper.ltlng.latitude,App.get()._mapHelper.ltlng.longitude,Integer.parseInt(etRate.getFieldText()));
                restCall(Constants.apiCallCreateProvider, _req, true);
            }
            else
            {
                showToast("All Fields are mandatory");
            }
        }

    }



    @Override
    public void apiCallBackFailed(Object obj, String type) {

    }
}