package info.umer.carhakeem.UI.Activities;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

import info.umer.carhakeem.Helpers.AppLog;
import info.umer.carhakeem.Helpers.FragmentHandler;
import info.umer.carhakeem.Interfaces.IBase;
import info.umer.carhakeem.Interfaces.OnFragmentInteractionListener;
import info.umer.carhakeem.R;
import info.umer.carhakeem.UI.Fragments.Login;


public class JoinNConnect extends BaseActivity implements IBase, OnFragmentInteractionListener, View.OnClickListener{





    public FragmentHandler fragmentHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
          FrameLayout content = (FrameLayout) findViewById(R.id.content_frame);
          getLayoutInflater().inflate(R.layout.activity_join_n_connect, content);
        callInitializer(this);


    }


    @Override
    public void initializeControls() {



        fragmentHandler = new FragmentHandler(R.id.rlFragmentHandler, this);
        fragmentHandler.replaceFragment(new Login(fragmentHandler), false);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppLog.i("stack",fragmentHandler.getBackStackEntryCount()+"");
                if(fragmentHandler.getBackStackEntryCount()> 0)
                {
                    fragmentHandler.popStack();
                }

            }
        });


        Dexter.withContext(this)
                .withPermissions(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                ).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
            }
        }).check();




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