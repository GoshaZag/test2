package com.example.zagvozkings.test2.ui.fragment;

import android.content.Context;
import android.widget.Toast;

import com.example.zagvozkings.test2.storage.TableData;
import com.example.zagvozkings.test2.ui.activity.main.MainActivity;
import com.example.zagvozkings.test2.storage.Table;
import com.example.zagvozkings.test2.ui.fragment.interfaces.AddTableView;
import com.example.zagvozkings.test2.ui.fragment.interfaces.CreateTableModel;
import com.example.zagvozkings.test2.ui.fragment.interfaces.CreateViewPresenter;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean
public class CreateViewPresenterImp implements CreateViewPresenter{

    @RootContext
    protected Context context;
    @RootContext
    protected MainActivity mainActivity;

    @Bean(CreateTableModelImp.class)
    protected CreateTableModel tableModel;
    @Bean
    protected TableData tableData;

    public void init(AddTableView view, Integer id) {

        String name = tableModel.getName();
        if (name == null || name.equals("")){
            showToast("Укажите название стола");
            return;
        }

        Integer resId = tableModel.getResId();
        if (resId == null){
            showToast("Выбирите тим стола");
            return;
        }

        Integer width = tableModel.getWidth();
        Integer height = tableModel.getHeight();
        if (width == null || height == null){
            showToast("Укажите размер стола");
            return;
        }

        if (id == null) {
            Table table = new Table(tableData.getID(), name, resId, width, height, tableModel.getX(), tableModel.getY(), tableModel.getRotate());
            tableData.setTableView(table);
            mainActivity.addTable(table);
        } else {
            Table table = new Table(id, name, resId, width, height, tableModel.getX(), tableModel.getY(), tableModel.getRotate());
            tableData.setChangeTable(table);
            mainActivity.ChangeTable(table);
        }
    }

    //аа-аа-а что-то не так!!
    private void showToast(String message) {
        if (context != null && message!= null)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
