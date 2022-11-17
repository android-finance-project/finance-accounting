package ru.rt.finance.features.aboutus.presentation

import ru.rt.finance.features.aboutus.presentation.data.AboutUsModel

class AboutUsContract {

    sealed class State {
        object Loading : State()
        data class Error(val errorModel: ErrorModel) : State()
        data class Content(val aboutUsModel: ArrayList<AboutUsModel>? = arrayListOf()) : State()
    }

    sealed class Action {
        object NavigateToMainFragment : Action()
    }

    sealed class Event {
        object OnViewReady : Event()
        object OnCloseAboutUsClick : Event()
    }

    data class ErrorModel(val message: String)
}