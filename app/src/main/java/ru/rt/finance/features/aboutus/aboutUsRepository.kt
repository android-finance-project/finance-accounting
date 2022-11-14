package ru.rt.finance.features.aboutus

import kotlinx.coroutines.withContext
import kotlinx.coroutines.CoroutineDispatcher
import ru.rt.finance.core.localdatabase.daointerface.AboutUsData
import ru.rt.finance.core.utils.safeUnitCall
import ru.rt.finance.features.aboutus.presentation.data.AboutUsModel

class AboutUsRepository(private val aboutUsSource: AboutUsData, private val dispatcher: CoroutineDispatcher) {

    suspend fun getInfo(): ArrayList<AboutUsModel>? =
        withContext(dispatcher) {
            aboutUsSource.getInfo()
        }

    suspend fun getInfoResult(): Result<Unit> = withContext(dispatcher) { safeUnitCall { aboutUsSource.getInfo() } }
}
