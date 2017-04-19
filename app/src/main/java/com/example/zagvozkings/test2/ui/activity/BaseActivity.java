package com.example.zagvozkings.test2.ui.activity;

import android.app.Activity;
import android.app.DialogFragment;

import org.androidannotations.annotations.EActivity;

@EActivity
public class BaseActivity extends Activity{

    public void openDialog(DialogFragment dialog) {
        dialog.show(getFragmentManager(),"selectTable");
    }
}
