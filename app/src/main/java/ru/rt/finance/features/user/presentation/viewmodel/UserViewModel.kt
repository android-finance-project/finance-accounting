package ru.rt.finance.features.user.data.presentation.ui.activity.viewmodel

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
import ru.rt.finance.features.user.domain.LoadUsersUseCase
import ru.rt.finance.features.user.presentation.UsersContract.Action
import ru.rt.finance.features.user.presentation.UsersContract.ErrorModel
import ru.rt.finance.features.user.presentation.UsersContract.Event
import ru.rt.finance.features.user.presentation.UsersContract.State

class UserViewModel(
    private val loadUsersUseCase: LoadUsersUseCase,
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

    private fun showUsers() {
        viewModelScope.launch(ioDispatcher) {
            sendState(State.Loading)
            loadUsersUseCase
                .invoke()
                .onSuccess {
                    sendState(State.Content(users = it))
                }.onFailure { error ->
                    val errorMessage = when (error) {
                        else -> {
                            "Неизвестная ошибка"
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
            is Event.OnViewReady -> showUsers()
            is Event.OnUserClick -> { /* TODO: */
            }
        }
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
