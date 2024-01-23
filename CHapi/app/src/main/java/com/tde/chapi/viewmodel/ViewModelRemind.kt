package com.tde.chapi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelRemind: ViewModel() {
    var name = MutableLiveData("")
    var many = MutableLiveData("")
    var unit = MutableLiveData("")
    var type = MutableLiveData("")
    var date = MutableLiveData("")
    var completeHour = MutableLiveData("")
    var hour = MutableLiveData("")
    var frecuently = MutableLiveData("")
}