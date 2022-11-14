package ru.rt.finance.features.dictonary.presentation

import ru.rt.finance.features.dictonary.data.model.dictionary.DicExpenseEntity

class DicExpenseContract {

    sealed class State {
        object Loading : State()
        data class Error(val errorModel: ErrorModel) : State()
        data class Content(val dicExpenses: List<DicExpenseEntity>? = emptyList()) : State()
    }

    sealed class Action {
        data class DialogMessage(val message: String) : Action()
        data class DialogError(val error: ErrorModel) : Action()
        object NavigateToAddDicExpense : Action()
        object NavigateToEditDicExpense : Action()
    }

    sealed class Event {
        object OnViewReady : Event()
        object OnAddDicExpenseClick : Event()
        object OnEditDicExpenseClick : Event()
    }

    data class ErrorModel(val message: String)
}