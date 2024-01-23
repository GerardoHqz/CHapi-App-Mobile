package com.tde.chapi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelConsultation: ViewModel() {
    var completeHour = MutableLiveData("")
    var hospital = MutableLiveData("")
    var direction = MutableLiveData("")
    var medical = MutableLiveData("")
    var date = MutableLiveData("")
    var hour = MutableLiveData("")
    var activation = MutableLiveData("")
    var username = MutableLiveData("")
}