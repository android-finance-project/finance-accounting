package ru.rt.finance.core.utils

inline fun safeUnitCall(action: () -> Unit): Result<Unit> {
    return try {

        action.invoke()
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }
}

inline fun safeVoidCall(action: () -> Void): Result<Unit> {
    return try {

        action.invoke()
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }
}

inline fun <T> safeCall(action: () -> Result<T>): Result<T> {
    return try {

        action.invoke()
    } catch (e: Exception) {
        Result.failure(e)
    }
}
