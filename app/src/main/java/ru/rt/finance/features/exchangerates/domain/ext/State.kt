package ru.rt.finance.features.exchangerates.domain.ext

sealed class State<out R> {
    data class Success<out T>(val data: T) : State<T>()
    data class Error(val message: String) : State<Nothing>()
}