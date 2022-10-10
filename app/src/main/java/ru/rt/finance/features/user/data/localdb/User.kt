package ru.rt.finance.features.user.data.localdb

import kotlinx.coroutines.flow.Flow
import ru.rt.finance.core.localdatabase.daointerface.UserDAO
import ru.rt.finance.features.user.data.model.User

class User(private val userDAO: UserDAO) {

    suspend fun getInfo() {
        userDAO.getInfo()
    }

    suspend fun addUser(info: User) {
        userDAO.addUser(info)
    }

    suspend fun updateUser(info: User) {
        userDAO.updateUser(info)
    }

    suspend fun deleteUser(info: User) {
        userDAO.deleteUser(info)
    }

    suspend fun deleteAllExpense() {
        userDAO.deleteAllUser()
    }

    suspend fun searchByName(searchQuery: String): List<User>? {
        return userDAO.searchByName(searchQuery)
    }
}
