package com.example.zagvozkings.test2.ui.fragment;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.example.zagvozkings.test2.customView.CustomTableView;
import com.example.zagvozkings.test2.storage.TableData;
import com.example.zagvozkings.test2.ui.activity.BaseActivity;
import com.example.zagvozkings.test2.utility.TypeTable;
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
    protected BaseActivity base;

    @Bean(CreateTableModelImp.class)
    protected CreateTableModel tableModel;
    @Bean
    protected TableData tableData;

    private AddTableView view;

    public void init(AddTableView view) {

        this.view = view;

        String name = tableModel.getName();
        if (name == null || name.equals("")){
            showToast("Укажите наименование стола");
            return;
        }

        TypeTable type = tableModel.getType();
        if (type == null){
            showToast("Выбирите тим стола");
            return;
        }

        Integer width = tableModel.getWidth();
        Integer height = tableModel.getHeight();
        if (width == null || height == null){
            showToast("Укажите размер стола");
            return;
        }

        CustomTableView tableView = tableData.getChangeTable();
        if (tableView == null)
            tableView = new CustomTableView(context);

        tableView.setText(name);
        tableView.setType(type);
        tableView.setSize(width, height);
        tableView.setRotation(tableModel.getRotate());
        tableView.setMargin(tableModel.getX(), tableModel.getY());

        final CustomTableView finalTableView = tableView;
        tableView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentAddTable_ dialog = new FragmentAddTable_();
                tableData.setChangeTable(finalTableView);
                base.openDialog(dialog);
            }
        });

        createView(tableView);

    }

    private void showToast(String message) {
        if (context != null && message!= null)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    private void createView(CustomTableView table) {
        view.addTable(table);
    }
}
