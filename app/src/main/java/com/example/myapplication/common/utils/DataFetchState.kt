package com.example.myapplication.common.utils

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.common.error.StandardError

typealias StateMachine = MutableLiveData<DataFetchState>
fun StateMachine.loading() {
    postValue(DataFetchState.Loading)
}
fun StateMachine.success() {
    postValue(DataFetchState.Success)
}
fun StateMachine.error(error: StandardError) {
    postValue(DataFetchState.Error(error))
}
typealias IncrementalStateMachine = MutableLiveData<IncrementalDataFetchState>

sealed class DataFetchState {
    object Loading: DataFetchState()
    object Success: DataFetchState()
    class Error(val error: StandardError): DataFetchState()
}

sealed class IncrementalDataFetchState: DataFetchState() {
    object Loading : IncrementalDataFetchState()
    object LoadMore: IncrementalDataFetchState()
    object Success : IncrementalDataFetchState()
    object Completed: IncrementalDataFetchState()
    class Error(val error: StandardError) : IncrementalDataFetchState()
}

