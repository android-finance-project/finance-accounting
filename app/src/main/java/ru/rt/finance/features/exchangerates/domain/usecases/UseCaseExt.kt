package ru.rt.finance.features.exchangerates.domain.usecases

import android.util.Log
import ru.rt.finance.features.exchangerates.domain.ext.State

inline fun <T> safeCall(action: () -> T): State<T> {
    return try {
        State.Success(action.invoke())
    } catch (throwable: Throwable) {
        Log.d("TAG", throwable.message.orEmpty())
        State.Error(throwable.message.orEmpty())
    }
}