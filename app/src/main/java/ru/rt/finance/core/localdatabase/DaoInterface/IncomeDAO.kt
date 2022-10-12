package ru.rt.finance.core.localdatabase.daointerface

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ru.rt.finance.features.management.data.model.finance.IncomeEntity
import java.util.Date
import ru.rt.finance.features.management.data.model.finance.TableIncome

@Dao
interface IncomeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addIncome(info: IncomeEntity)

    @Update
    suspend fun updateIncome(info: IncomeEntity)

    @Delete
    suspend fun deleteIncome(info: IncomeEntity)

    @Query("Select * From ${TableIncome.tableNameIncome} WHERE ID = :id")
    suspend fun getIncomeById(id: Int): IncomeEntity?

    @Query("DELETE FROM  ${TableIncome.tableNameIncome}")
    suspend fun deleteAllIncome()

    @Query("SELECT inc.* FROM  ${TableIncome.tableNameIncome} As inc ORDER BY  DATE DESC,ID DESC")
    suspend fun getInfo(): List<IncomeEntity>

    @Query("SELECT * FROM   ${TableIncome.tableNameIncome}  WHERE sum LIKE '%' || :searchQuery || '%'")
    suspend fun searchDatabaseSum(searchQuery: String): List<IncomeEntity>?

    @Query("SELECT * FROM   ${TableIncome.tableNameIncome}  WHERE date BETWEEN   :searchDateBegin AND  :searchDateEnd")
    suspend fun getByDates(searchDateBegin: Date, searchDateEnd: Date): List<IncomeEntity>?
}