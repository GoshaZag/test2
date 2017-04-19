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

@EFragment(R.layout.dialog_add_table)
public class FragmentAddTable extends DialogFragment implements AddTableView{

    @Bean(CreateViewPresenterImp.class)
    protected CreateViewPresenter createViewPre;
    @Bean
    protected ChangeDataOnView changeViewPre;

    @ViewById
    protected LinearLayout FRAddMain;

    @AfterViews
    void init(){
        //подождем, когда создадуться все view после повесим слушатели,
        // при необходимости (редактировании) заполним старые данные для ввода
        FRAddMain.post(new Runnable() {
            @Override
            public void run() {
                changeViewPre.init();
            }
        });
    }

    @Click(R.id.FragmentAddTable)
    protected void add(){
        //да будет новый стол!
        //эх, а где же стулья?
        createViewPre.init(this);
    }

    public void addTable(CustomTableView table){
        if (table != null){
            //можно обращаться сразу на активность и там добалять стол
            //но мы заносим столы через хол (MVP надо что-то возвращать)
            MainActivity mainActivity = (MainActivity)getActivity();
            if (mainActivity != null)
                mainActivity.addView(table);
            this.dismiss();
        }
    }

}
