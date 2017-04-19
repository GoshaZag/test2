package com.example.zagvozkings.test2.ui.fragment;

import android.app.DialogFragment;
import android.widget.LinearLayout;

import com.example.zagvozkings.test2.R;
import com.example.zagvozkings.test2.customView.CustomTableView;
import com.example.zagvozkings.test2.ui.fragment.interfaces.AddTableView;
import com.example.zagvozkings.test2.ui.fragment.interfaces.CreateViewPresenter;
import com.example.zagvozkings.test2.ui.activity.main.MainActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EFragment(R.layout.dialog_add_table)
public class FragmentAddTable extends DialogFragment implements AddTableView{

    @Bean(CreateViewPresenterImp.class)
    protected CreateViewPresenter createViewPre;
    @Bean
    protected ChangeDataOnView changeViewPre;

    @ViewById
    protected LinearLayout FRAddMain;

    List<CustomTableView> listTable = new ArrayList<>();

    @AfterViews
    void init(){
        FRAddMain.post(new Runnable() {
            @Override
            public void run() {
                changeViewPre.init();
            }
        });
    }

    @Click(R.id.FragmentAddTable)
    protected void add(){
        createViewPre.init(this);
    }

    public void addTable(CustomTableView table){
        if (table != null){
            listTable.add(table);
            MainActivity mainActivity = (MainActivity)getActivity();
            if (mainActivity != null)
                mainActivity.addView(table);
            this.dismiss();
        }
    }

}
