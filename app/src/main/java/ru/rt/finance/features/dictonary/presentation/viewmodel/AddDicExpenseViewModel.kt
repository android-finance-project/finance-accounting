package ru.rt.finance.features.dictonary.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.rt.finance.core.MainApplication.Companion.TAG
import ru.rt.finance.features.dictonary.DicExpenseRepository
import ru.rt.finance.features.dictonary.data.model.dictionary.DicExpenseEntity
import ru.rt.finance.features.dictonary.presentation.AddDicExpenseContract
import ru.rt.finance.features.dictonary.presentation.AddDicExpenseContract.Action
import ru.rt.finance.features.dictonary.presentation.AddDicExpenseContract.Event
import ru.rt.finance.features.dictonary.presentation.AddDicExpenseContract.State

class AddDicExpenseViewModel(private val dicExpenseRepository: DicExpenseRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<State> = MutableStateFlow(State.Initial)
    val uiState = _uiState.asStateFlow()

    private val _action: Channel<Action> = Channel()
    val action = _action.receiveAsFlow()

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    val event = _event.asSharedFlow()

    init {
        subscribeEvents()
    }

    private fun addDicExpense(
        nameDicExpense: String,

        ) {
        Log.d(TAG, "addDicExpense(")
        viewModelScope.launch {
            sendState(State.Loading)
            val lastKey = dicExpenseRepository.getLastKey()
            dicExpenseRepository.addDicExpenseResult(
                DicExpenseEntity(
                    idDicExpenseEntity = lastKey,
                    nameDicExpenseEntity = nameDicExpense
                )
            )
                .onSuccess {
                    Log.d(TAG, ".onSuccess { ")
                    navigateToDicExpense()
                }
                .onFailure {
                    sendState(
                        State.Error(
                            AddDicExpenseContract.ErrorModel(
                                message = it.message ?: "Не могу добавить категорию расхода"
                            )
                        )
                    )
                }
        }
    }

    private fun navigateToDicExpense() {
        sendEffect(Action.NavigateToDicExpense)
    }

    private fun subscribeEvents() {
        viewModelScope.launch {
            event.collect {
                handleEvent(it)
            }
        }
    }

    private fun sendState(state: State) {
        viewModelScope.launch { _uiState.emit(state) }
    }

    private fun sendEffect(action: Action) {
        viewModelScope.launch { _action.send(action) }
    }

    fun setEvent(event: Event) {
        viewModelScope.launch { _event.emit(event) }
    }

    private fun handleEvent(event: Event) {
        Log.d(TAG, "handleEvent(event: Event)")
        when (event) {
            is Event.OnSaveDicExpenseClick -> {
                Log.d(TAG, " Event.OnSaveDicExpenseClick")
                addDicExpense(event.nameDicExpense)
            }
        }
    }
}
