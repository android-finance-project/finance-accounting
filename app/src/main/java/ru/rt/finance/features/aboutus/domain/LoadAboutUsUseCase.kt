package ru.rt.finance.features.aboutus.domain

import ru.rt.finance.features.aboutus.AboutUsRepository
import ru.rt.finance.features.aboutus.presentation.data.AboutUsModel

class LoadAboutUsUseCase(private val aboutUsRepository: AboutUsRepository) {

    suspend fun invoke(): Result<ArrayList<AboutUsModel>?> {
        return try {
            Result.Companion.success(aboutUsRepository.getInfo())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}