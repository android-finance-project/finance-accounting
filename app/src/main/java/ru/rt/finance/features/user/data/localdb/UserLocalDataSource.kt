package ru.rt.finance.features.user.data.localdb

import androidx.room.Query
import ru.rt.finance.core.localdatabase.daointerface.UserDao
import ru.rt.finance.features.user.data.model.TableUser
import ru.rt.finance.features.user.data.model.UserEntity

class UserLocalDataSource(private val userDao: UserDao) {

    suspend fun getInfo(): List<UserEntity>? {
        return userDao.getInfo()
    }

    suspend fun addUser(info: UserEntity) {
        userDao.addUser(info)
    }

    suspend fun updateUser(info: UserEntity) {
        userDao.updateUser(info)
    }

    suspend fun deleteUser(info: UserEntity) {
        userDao.deleteUser(info)
    }

    suspend fun getUserById(id: Int): UserEntity? {
        return userDao.getUserById(id)
    }

    suspend fun deleteAllUser() {
        userDao.deleteAllUser()
    }

    suspend fun searchByName(searchQuery: String): List<UserEntity>? {
        return userDao.searchByName(searchQuery)
    }

    suspend fun getRowCount(): Int {
        return userDao.getRowCount()
    }
}