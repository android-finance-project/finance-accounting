package ru.rt.finance.features.dictonary.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.rt.finance.R
import ru.rt.finance.features.dictonary.domain.LoadDicExpensesUseCase
import ru.rt.finance.features.dictonary.presentation.DicExpenseContract.State
import ru.rt.finance.features.dictonary.presentation.DicExpenseContract.Action
import ru.rt.finance.features.dictonary.presentation.DicExpenseContract.ErrorModel
import ru.rt.finance.features.dictonary.presentation.DicExpenseContract.Event

class DicExpenseViewModel(
    private val loadDicExpensesUseCase: LoadDicExpensesUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState: MutableStateFlow<State> = MutableStateFlow(State.Loading)

    val uiState = _uiState.asStateFlow()

    private val _action: Channel<Action> = Channel()
    val action = _action.receiveAsFlow()

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    val event = _event.asSharedFlow()

    init {
        subscribeEvents()
    }

    private fun showDicExpenses() {
        viewModelScope.launch(ioDispatcher) {
            sendState(State.Loading)
            loadDicExpensesUseCase
                .invoke()
                .onSuccess {
                    sendState(State.Content(dicExpenses = it))
                }.onFailure { error ->
                    val errorMessage = when (error) {
                        else -> {
                            "Неизвестная ошибка:" + error.message.toString()
                        }
                    }
                    sendState(State.Error(ErrorModel(message = errorMessage)))
                }
        }
    }

    private fun subscribeEvents() {
        viewModelScope.launch {
            event.collect {
                handleEvent(it)
            }
        }
    }

    private fun handleEvent(event: Event) {
        when (event) {
            is Event.OnViewReady -> showDicExpenses()
            is Event.OnAddDicExpenseClick -> {
                navigateToAddDicExpense()
            }
            is Event.OnEditDicExpenseClick -> {
                navigateToEditDicExpense()
            }
        }
    }

    private fun navigateToAddDicExpense() {
        sendAction(Action.NavigateToAddDicExpense)
    }

    private fun navigateToEditDicExpense() {
        sendAction(Action.NavigateToEditDicExpense)
    }

    private fun sendAction(action: Action) {
        viewModelScope.launch { _action.send(action) }
    }

    private fun sendState(state: State) {
        viewModelScope.launch { _uiState.emit(state) }
    }

    fun setEvent(event: Event) {
        viewModelScope.launch { _event.emit(event) }
    }
}