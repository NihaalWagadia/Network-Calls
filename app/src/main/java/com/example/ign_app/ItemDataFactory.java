package com.example.ign_app;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.example.ign_app.model.data.Data;

public class ItemDataFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, Data>> itemLiveDataSource = new MutableLiveData<>();



    @Override
    public DataSource create() {
        ItemDataSource itemDataSource = new ItemDataSource();
        itemLiveDataSource.postValue(itemDataSource);
        return itemDataSource;

    }

    public MutableLiveData<PageKeyedDataSource<Integer, Data>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }
}
