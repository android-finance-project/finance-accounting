package ru.rt.finance.features.dictonary.data.localdb
import kotlinx.coroutines.flow.Flow
import ru.rt.finance.core.localdatabase.DaoInterface.DicExpenseDAO
import ru.rt.finance.features.dictonary.data.model.dictionary.DicExpense


class DicExpense(private val DicExpenseDAO : DicExpenseDAO) {


    val DicExpenseInfo: /*LiveData<*/List<DicExpense>? = DicExpenseDAO.getInfo()

    suspend fun addDicExpense(info : DicExpense ){
        DicExpenseDAO.addDicExpense(info)
    }

   suspend fun updateDicExpense(info : DicExpense){
       DicExpenseDAO.updateDicExpense(info)
    }



    suspend fun deleteDicExpense(info: DicExpense){
        DicExpenseDAO.deleteDicExpense(info)
    }


    suspend fun getDicExpenseById(id : Int) {
        DicExpenseDAO.getDicExpenseById(id)
    }



    suspend fun deleteAllDicExpense(){
        DicExpenseDAO.deleteAllDicExpense()
    }

    fun searchDatabase(searchQuery: String): Flow<List<DicExpense>?> {
        return DicExpenseDAO.searchDatabase(searchQuery)
    }

}
