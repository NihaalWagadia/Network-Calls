package com.example.ign_app;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.example.ign_app.model.data.Data;

public class ItemViewModel extends ViewModel {

    LiveData<PagedList<Data>> itemPagedList;
    LiveData<PageKeyedDataSource<Integer, Data>> liveDataSource;

    public ItemViewModel(){

        ItemDataFactory itemDataFactory = new ItemDataFactory();
        liveDataSource = itemDataFactory.getItemLiveDataSource();

        PagedList.Config config =
                (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setPageSize(ItemDataSource.PAGE_SIZE)
                .build();

        itemPagedList= (new LivePagedListBuilder(itemDataFactory, config)).build();
    }

}
