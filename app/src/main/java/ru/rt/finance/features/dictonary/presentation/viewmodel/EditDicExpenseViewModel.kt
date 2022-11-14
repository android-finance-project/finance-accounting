package ru.rt.finance.features.dictonary.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import ru.rt.finance.features.dictonary.presentation.EditDicExpenseContract
import ru.rt.finance.features.dictonary.presentation.EditDicExpenseContract.Action
import ru.rt.finance.features.dictonary.presentation.EditDicExpenseContract.Event
import ru.rt.finance.features.dictonary.presentation.EditDicExpenseContract.State

class EditDicExpenseViewModel(private val dicExpenseRepository: DicExpenseRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<State> = MutableStateFlow(State.Initial)
    val uiState = _uiState.asStateFlow()

    private val _action: Channel<Action> = Channel()
    val action = _action.receiveAsFlow()

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    val event = _event.asSharedFlow()

    init {
        subscribeEvents()
    }

    private fun editDicExpense(
        idDicExpenseEntity: Int,
        nameDicExpenseEntity: String,
    ) {
        Log.d(TAG, "addDicExpense(")
        viewModelScope.launch {
            sendState(State.Loading)
            dicExpenseRepository.updateDicExpenseResult(
                DicExpenseEntity(
                    idDicExpenseEntity = idDicExpenseEntity,
                    nameDicExpenseEntity = nameDicExpenseEntity
                )
            ).onSuccess {
                navigateToDicExpense()
            }
                .onFailure {
                    sendState(
                        State.Error(
                            EditDicExpenseContract.ErrorModel(
                                message = it.message ?: "Не могу отредактироват категорию расхода"
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
                editDicExpense(event.idDicExpense, event.nameDicExpense)
            }
        }
    }
}
