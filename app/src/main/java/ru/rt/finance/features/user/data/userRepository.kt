package ru.rt.finance.features.user.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.rt.finance.core.localdatabase.daointerface.UserDao
import ru.rt.finance.features.user.data.model.UserEntity

class UserRepository(private val userSource: UserDao, private val dispatcher: CoroutineDispatcher) {

    suspend fun addUser(info: UserEntity) {
        withContext(dispatcher) {
            userSource.addUser(info)
        }
    }

    suspend fun updateUser(info: UserEntity) {
        withContext(dispatcher) {
            userSource.updateUser(info)
        }
    }

    suspend fun deleteUser(info: UserEntity) {
        withContext(dispatcher) {
            userSource.deleteUser(info)
        }
    }
}
