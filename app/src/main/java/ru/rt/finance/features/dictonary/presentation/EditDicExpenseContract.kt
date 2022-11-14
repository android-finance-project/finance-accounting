package ru.rt.finance.features.dictonary.presentation

class EditDicExpenseContract {

    sealed class State {
        object Initial : State()
        object Loading : State()
        data class Error(val errorModel: ErrorModel) : State()
    }

    sealed class Action {
        data class DialogMessage(val message: String) : Action()
        data class DialogError(val error: ErrorModel) : Action()
        object NavigateToDicExpense : Action()
    }

    sealed class Event {
        data class OnSaveDicExpenseClick(
            val nameDicExpense: String,
            val idDicExpense: Int
        ) : Event()
    }

    data class ErrorModel(val message: String)
}