package com.vmn.theschoolofrock.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.vmn.theschoolofrock.BR

class MovieDetailsBindingModel : BaseObservable() {


    @get:Bindable
    var genre: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.genre)
        }

    @get:Bindable
    var overview: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.overview)
        }

    @get:Bindable
    var originalLanguage: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.originalLanguage)
        }

    @get:Bindable
    var releaseDate: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.releaseDate)
        }

    @get:Bindable
    var voteCount: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.voteCount)
        }

    @get:Bindable
    var voteAverage: Float = 0f
        set(value) {
            field = value
            notifyPropertyChanged(BR.voteAverage)
        }

    @get:Bindable
    var showProgress: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.showProgress)
        }
}