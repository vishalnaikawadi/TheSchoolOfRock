package com.vmn.theschoolofrock.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.vmn.theschoolofrock.BR

class MoviesPlayingBindingModel : BaseObservable() {

    @get:Bindable
    var showProgress: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.showProgress)
        }
}