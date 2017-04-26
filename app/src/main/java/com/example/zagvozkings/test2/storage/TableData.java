package com.example.zagvozkings.test2.storage;

import com.example.zagvozkings.test2.R;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

@EBean(scope = EBean.Scope.Singleton)
public class TableData {

    private float scale;
    private List<Table> listTable;
    private Integer startId = 1000000;

    public void setChangeTable(Table table) {
        for (int i = 0 ; i < listTable.size(); ++i){
            if (listTable.get(i).getId().equals(table.getId())){
                listTable.set(i, table);
                break;
            }
        }
    }

    public void setScale(float scale) {
        this.scale = scale;
    }
    public float getScale() {
        return scale;
    }

    public void setTableView(Table table) {
        if (listTable == null)
            createListTable();
        if (table != null)
            listTable.add(table);
    }

    public List<Table> getListTable() {
        if (listTable == null)
            createListTable();
        return listTable;
    }

    private void createListTable() {
        listTable = new ArrayList<>();
        listTable.add(new Table(1, "Большой Стол", R.drawable.table_rectangle, 340, 340, 0, 0, 180f));
        listTable.add(new Table(2, "Круглый Стол", R.drawable.table_cicle, 200, 200, 340, 340, 0f));
        listTable.add(new Table(3, "Малый Стол", R.drawable.table_rectangle, 120, 120, 530, 530, 45f));
    }

    public Integer getID() {
        //набы получать с сервера
        ++startId;
        return startId;
    }

    public void removeTable(Integer idTable) {
        for (int i = 0 ; i < listTable.size(); ++i){
            if (listTable.get(i).getId().equals(idTable)){
                listTable.remove(i);
                break;
            }
        }
    }

    public Table getTableForId(Integer idTable) {
        for (int i = 0 ; i < listTable.size(); ++i){
            if (listTable.get(i).getId().equals(idTable)){
                return listTable.get(i);
            }
        }
        return null;
    }
}
