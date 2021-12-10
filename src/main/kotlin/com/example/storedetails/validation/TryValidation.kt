package com.example.storedetails.validation

import com.example.storedetails.models.AddressPeriod
import com.example.storedetails.models.StoreData
import com.example.storedetails.repo.StoreDataRepo
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import com.example.storedetails.exception.DataAlreadyPresentException
import com.example.storedetails.exception.DateIncorrectException

@Service
class TryValidation (val storeDataRepo: StoreDataRepo,){

    fun validData(storeData: StoreData):Boolean
    {  val addressPeriod:List<AddressPeriod>? =storeData.addressPeriod
        if(storeData.name==null||storeData.status==null||storeData.addressPeriod!!.isEmpty())

            {
                return false
            }
        for(data in addressPeriod!!) {
            if(data.storeAddress==null)
            {
                return false
            }

            if(data.dateValidFrom==null||data.storeAddress!!.street==null||data.storeAddress!!.houseNumber==null||data.storeAddress!!.houseNumberSuffix==null||data.storeAddress!!.postalCode==null||data.storeAddress!!.city==null||data.storeAddress!!.country==null)
            {
                return false
            }
            if(data.dateValidUntill!=null&&(data.dateValidUntill!!<data.dateValidFrom))
            {
                throw DateIncorrectException(data.dateValidUntill!!,data.dateValidFrom!!)
            }

        }
        var result=storeDataRepo.findAll()
        for(data in result)
        {
            if(data.name==storeData.name)
            {
                throw DataAlreadyPresentException(storeData.name!!)

            }
        }

        return true




    }



    fun validDateformat(refDate: String?): LocalDate? {
        var date: LocalDate? = null
        if (refDate != null) {
            date = LocalDate.parse(refDate, DateTimeFormatter.ISO_DATE)
            return date
        }
        return date
    }
}
//
//var storeAddress: StoreAddress? =addressPeriod?.get(0)?.storeAddress
//
//if(storeData.name==null||storeData.status==null|| addressPeriod?.get(0)?.dateValidFrom ==null||storeAddress?.city==null||storeAddress?.country==null||storeAddress?.postalCode==null||storeAddress?.street==null||storeAddress?.houseNumberSuffix==null||storeAddress?.houseNumber==null)
//{
//    return false
//}