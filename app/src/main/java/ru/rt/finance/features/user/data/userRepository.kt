package ru.rt.finance.features.user.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.rt.finance.core.localdatabase.daointerface.UserDAO
import ru.rt.finance.features.user.data.model.User

class UserRepository(private val userSource: UserDAO, private val dispatcher: CoroutineDispatcher) {

    suspend fun addUser(info: User) {
        withContext(dispatcher) {
            userSource.addUser(info)
        }
    }

    suspend fun updateUser(info: User) {
        withContext(dispatcher) {
            userSource.updateUser(info)
        }
    }

    suspend fun deleteUser(info: User) {
        withContext(dispatcher) {
            userSource.deleteUser(info)
        }
    }
}
