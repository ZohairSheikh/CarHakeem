package info.umer.carhakeem.UI.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import info.umer.carhakeem.Adapters.RVDrawerMenu;
import info.umer.carhakeem.Helpers.AppLog;
import info.umer.carhakeem.Helpers.Entities.Constants;
import info.umer.carhakeem.Helpers.FragmentHandler;
import info.umer.carhakeem.Helpers.sharedPrefs;
import info.umer.carhakeem.Interfaces.IBase;
import info.umer.carhakeem.Interfaces.OnFragmentInteractionListener;
import info.umer.carhakeem.R;
import info.umer.carhakeem.UI.Fragments.Customer.CustomerDashboard;
import info.umer.carhakeem.UI.Fragments.Customer.MyBookings;
import info.umer.carhakeem.UI.Fragments.Profile;
import info.umer.carhakeem.UI.Fragments.Provider.ProviderDashboard;

public class Home extends BaseActivity implements IBase, OnFragmentInteractionListener, View.OnClickListener {



    public FragmentHandler fragmentHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout content = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_home, content);
        callInitializer(this);


    }


    @Override
    public void initializeControls() {


        if (sharedPrefs.getString(Constants.userType).equals("customer")) {

            fragmentHandler = new FragmentHandler(R.id.rlFragmentHandler, this);
            fragmentHandler.replaceFragment(new CustomerDashboard(fragmentHandler), false);
        } else {
            fragmentHandler = new FragmentHandler(R.id.rlFragmentHandler, this);
            fragmentHandler.replaceFragment(new ProviderDashboard(fragmentHandler), false);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isDrawer) {
                    drawerLayout.openDrawer(Gravity.LEFT);
                } else {
                    AppLog.i("stack", fragmentHandler.getBackStackEntryCount() + "");
                    if (fragmentHandler.getBackStackEntryCount() > 0) {
                        fragmentHandler.popStack();
                    }
                }


            }
        });


        adapter.setClickListener(new RVDrawerMenu.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                if (sharedPrefs.getString(Constants.userType).equals("customer")) {


                    if (position == 0) {
                        fragmentHandler.addReplaceFragmentWithAnimation(new Profile(fragmentHandler), true, true, R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);

                    } else if (position == 2) {
                        sharedPrefs.putBool(Constants.userLoggedIn, false);
                        Intent activitySignRegister = new Intent(Home.this, Splash.class);
                        startActivity(activitySignRegister);
                        finish();

                    } else if (position == 1) {
                        fragmentHandler.addReplaceFragmentWithAnimation(new MyBookings(fragmentHandler), true, true, R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);


                    }
                } else {
                    if (position == 0) {
                        fragmentHandler.addReplaceFragmentWithAnimation(new Profile(fragmentHandler), true, true, R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);

                    } else if (position == 2) {
                        sharedPrefs.putBool(Constants.userLoggedIn, false);
                        Intent activitySignRegister = new Intent(Home.this, Splash.class);
                        startActivity(activitySignRegister);
                        finish();

                    }
                    else if (position == 1) {
                        fragmentHandler.addReplaceFragmentWithAnimation(new MyBookings(fragmentHandler), true, true, R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);


                    }
                }
            }
        });
//


    }


    @Override
    public void apiCallBack(Object obj, String type) {

    }

    @Override
    public void initializeControls(View view) {

    }

    @Override
    public void apiCallBackFailed(Object obj, String type) {

    }

    @Override
    public void onFragmentInteraction(String from, String action) {

    }


}