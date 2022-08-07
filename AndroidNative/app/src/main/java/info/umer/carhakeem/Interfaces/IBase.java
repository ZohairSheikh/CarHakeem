package info.umer.carhakeem.Interfaces;


import android.view.View;

public interface IBase {
    void initializeControls();
    void apiCallBack(Object obj, String type);
    void initializeControls(View view);
    void apiCallBackFailed(Object obj, String type);

}