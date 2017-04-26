package com.example.zagvozkings.test2.ui.activity.main;

import com.example.zagvozkings.test2.storage.Table;
import com.example.zagvozkings.test2.storage.TableData;
import com.example.zagvozkings.test2.ui.activity.main.interfaces.CreateTableListModel;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.List;

@EBean
public class CreateTableListModelImp implements CreateTableListModel {

    @Bean
    protected TableData tableData;

    @Override
    public List<Table> getList() {
        return tableData.getListTable();
    }
}
