package ru.rt.finance.core.localdatabase.daointerface

import ru.rt.finance.features.aboutus.presentation.data.AboutUsModel

class AboutUsData {

    fun getInfo(): ArrayList<AboutUsModel>? {

        var aboutUsModel = AboutUsModel()
        var arrayList: ArrayList<AboutUsModel> = ArrayList<AboutUsModel>()
        with(aboutUsModel) {
            textHeader = "Он нас"
            textLine1 = "Попытка сделать в рамках обучения на стэке MVI Coroutine Koin"
            textLine2 = "по отслеживанию личных доходов и расходов."
            textLine3 = "Подобие финансового калькулятора"
            textFooter = "© 2022"
        }
        arrayList.add(aboutUsModel)
        return (arrayList)
    }
}