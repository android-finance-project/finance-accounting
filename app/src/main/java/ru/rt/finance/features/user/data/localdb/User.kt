package ru.rt.finance.features.user.data.localdb

import kotlinx.coroutines.flow.Flow
import ru.rt.finance.core.localdatabase.DaoInterface.UserDAO
import ru.rt.finance.features.user.data.model.User

class User(private val UserDAO : UserDAO) {
 val UserInfo: List<User>? = UserDAO.getInfo()
 suspend fun addUser(info : User){
     UserDAO.addUser(info)
    }

 suspend fun updateUser(info : User){
     UserDAO.updateUser(info)
 }

    suspend fun deleteUser(info: User){
        UserDAO.deleteUser(info)
    }

    suspend fun deleteAllExpense(){
        UserDAO.deleteAllUser()
    }

    fun searchDatabaseUser(searchQuery: String): Flow<List<User>?> {
        return UserDAO.searchDatabase(searchQuery)
    }



}




