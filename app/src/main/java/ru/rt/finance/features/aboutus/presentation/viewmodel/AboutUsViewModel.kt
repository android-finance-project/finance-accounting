package ru.rt.finance.features.aboutus.presentation.viewmodel

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
import ru.rt.finance.features.aboutus.domain.LoadAboutUsUseCase
import ru.rt.finance.features.aboutus.presentation.AboutUsContract.State
import ru.rt.finance.features.aboutus.presentation.AboutUsContract.Action
import ru.rt.finance.features.aboutus.presentation.AboutUsContract.ErrorModel
import ru.rt.finance.features.aboutus.presentation.AboutUsContract.Event

class AboutUsViewModel(
    private val loadAboutUsUseCase: LoadAboutUsUseCase,
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

    private fun showAboutUs() {
        viewModelScope.launch(ioDispatcher) {
            sendState(State.Loading)
            loadAboutUsUseCase
                .invoke()
                .onSuccess {
                    sendState(State.Content(aboutUsModel = it))
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
            is Event.OnViewReady -> showAboutUs()
            is Event.OnCloseAboutUsClick -> {
                navigateToMainFragment()
            }

        }
    }

    private fun navigateToMainFragment() {
        sendAction(Action.NavigateToMainFragment)
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