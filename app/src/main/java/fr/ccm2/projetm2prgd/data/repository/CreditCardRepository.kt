package fr.ccm2.projetm2prgd.data.repository

import fr.ccm2.projetm2prgd.architecture.CustomApplication
import fr.ccm2.projetm2prgd.architecture.RetrofitBuilder
import fr.ccm2.projetm2prgd.data.model.CreditCardObject
import fr.ccm2.projetm2prgd.data.model.toRoom
import fr.ccm2.projetm2prgd.data.model.toUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CreditCardRepository {
    private val creditCardDao = CustomApplication.instance.mApplicationDatabase.creditCardDao()


    suspend fun fetchData() {
        creditCardDao.insert(RetrofitBuilder.getCreditCard().getRandomCreditCard().toRoom())
    }

    fun deleteAll() {
        creditCardDao.deleteAll()
    }

    fun selectAll(): Flow<List<CreditCardObject>> {
        return creditCardDao.selectAll().map { list ->
            list.toUi()
        }
    }


}