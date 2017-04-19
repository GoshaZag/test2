package com.example.zagvozkings.test2.storage;

import com.example.zagvozkings.test2.customView.CustomTableView;
import com.example.zagvozkings.test2.utility.Table;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

@EBean(scope = EBean.Scope.Singleton)
public class TableData {

    private CustomTableView changeTable;
    private float scale;
    private List<Table> listTable;
    private Integer startId = 1000000;

    public CustomTableView getChangeTable() {
        return changeTable;
    }
    public void setChangeTable(CustomTableView changeTable) {
        this.changeTable = changeTable;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }
    public float getScale() {
        return scale;
    }

    public void setTableView(Table table) {
        if (listTable == null) listTable = new ArrayList<>();
        listTable.add(table);
    }

    public List<Table> getListTable() {
        return listTable;
    }

    public Integer getID() {
        //набы получать с сервера
        ++startId;
        return startId;
    }

    public void removeTable(Integer idTable) {
        for (int i = 0 ; i < listTable.size(); ++i){
            if (listTable.get(i).id.equals(idTable)){
                listTable.remove(i);
                break;
            }
        }
    }
}
