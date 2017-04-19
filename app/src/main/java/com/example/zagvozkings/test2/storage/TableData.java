package com.example.zagvozkings.test2.storage;

import com.example.zagvozkings.test2.customView.CustomTableView;

import org.androidannotations.annotations.EBean;

@EBean(scope = EBean.Scope.Singleton)
public class TableData {

    private CustomTableView changeTable;

    public CustomTableView getChangeTable() {
        return changeTable;
    }
    public void setChangeTable(CustomTableView changeTable) {
        this.changeTable = changeTable;
    }
}
