package com.example.myapplication.core

import androidx.lifecycle.ViewModel
import com.example.myapplication.common.appController.AppControllerContract
import com.example.myapplication.common.cache.CacheManager
import com.example.myapplication.common.utils.DataFetchState
import com.example.myapplication.common.utils.StateMachine
import com.example.myapplication.core.list.RecyclerViewListItem


abstract class BaseViewModel: ViewModel() {
    protected val appController: AppControllerContract = AppControllerContract.get()
    protected val appResources = appController.properContext.resources
    val listItems = mutableListOf<RecyclerViewListItem>()
    protected val cache = CacheManager
    private var isFirstTime : Boolean = true

    open fun loadData(stateMachine: StateMachine){
        if (isFirstTime){
            stateMachine.postValue(DataFetchState.Loading)
            isFirstTime = true
        }
    }
}