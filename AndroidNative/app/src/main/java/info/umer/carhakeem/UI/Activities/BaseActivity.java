package info.umer.carhakeem.UI.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import info.umer.carhakeem.Adapters.RVDrawerMenu;
import info.umer.carhakeem.Helpers.Entities.Constants;
import info.umer.carhakeem.Helpers.Http.callApi;
import info.umer.carhakeem.Helpers.sharedPrefs;
import info.umer.carhakeem.Interfaces.IBase;
import info.umer.carhakeem.R;
import info.umer.carhakeem.UI.Custom.Loading_Dialog;
import info.umer.carhakeem.UI.Custom.TextView_N;


public class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    IBase ibase;
    ProgressDialog loadingDialog;

    boolean isDrawer;
    DrawerLayout drawerLayout;


    AppBarLayout apbHeader;

    Toolbar toolbar;

    public CircleImageView profile_image;
    public TextView_N tvUserName;
    public RecyclerView rlMenu;

    RVDrawerMenu adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);


        apbHeader = (AppBarLayout) findViewById(R.id.in1);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        profile_image = (CircleImageView) findViewById(R.id.profile_image);
        tvUserName = (TextView_N) findViewById(R.id.tvUserName);
        rlMenu = (RecyclerView) findViewById(R.id.rlMenu);


        tvUserName.setText(sharedPrefs.getString(Constants.userName));


        ArrayList<String> menuItem = new ArrayList<>();


        if (sharedPrefs.getString(Constants.userType).equals("customer")) {
            menuItem.add("Profile");
            menuItem.add("My Bookings");
            menuItem.add("Sign Out");
        }
        else
        {
            menuItem.add("Profile");
            menuItem.add("My Bookings");
            menuItem.add("Sign Out");
        }


        if(sharedPrefs.getString(Constants.userType).equals("customer"))
        {

        }
        else
        {

        }



        // set up the RecyclerView

        rlMenu.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RVDrawerMenu(this, menuItem);

        rlMenu.setAdapter(adapter);

    }




    public void showActionButtonAppBar(boolean show,boolean isDrawer)
    {
        if(show)
        {
           this. isDrawer = isDrawer;
            if(isDrawer)
            {

                toolbar.setNavigationIcon( getResources().getDrawable(R.drawable.icn_dehaze));

            }
            else
            {
                toolbar.setNavigationIcon( getResources().getDrawable(R.drawable.icn_back));
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            }
        }
        else
        {
            toolbar.setNavigationIcon( null);

        }
    }

    public void AppBarTitle(String title)
    {
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle(title);
    }

    public void AppBar(Boolean visible)
    {
        if (visible) {
            apbHeader.setVisibility(View.VISIBLE);
        } else {
            apbHeader.setVisibility(View.GONE);
        }
    }

    public void closeKeyBoard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }



    @Override
    public void onClick(View view) {
}



    protected void callInitializer(IBase ibase_) {
        ibase = ibase_;
        ibase.initializeControls();
    }



}
