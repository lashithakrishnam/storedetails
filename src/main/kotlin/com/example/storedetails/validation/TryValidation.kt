package com.example.storedetails.validation

import com.example.storedetails.models.AddressPeriod
import com.example.storedetails.models.StoreData
import com.example.storedetails.repo.StoreDataRepo
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import com.example.storedetails.exception.DataAlreadyPresentException
import com.example.storedetails.exception.DateIncorrectException
import java.util.*
import java.util.stream.Collectors
import kotlin.streams.toList

@Service
class TryValidation (val storeDataRepo: StoreDataRepo,){

    fun validData(storeData: StoreData):Boolean
    {  val addressPeriod:List<AddressPeriod>? =storeData.addressPeriod
        if(storeData.name==null||storeData.status==null||storeData.addressPeriod!!.isEmpty())

            {
                return false
            }

        addressPeriod!!.forEach {
            if(it.storeAddress==null)
            {
                return false
            }


            if(isStoreAddressDetailsNotNull(it.dateValidFrom,it.storeAddress!!.street,it.storeAddress!!.houseNumber,it.storeAddress!!.houseNumberSuffix,it.storeAddress!!.postalCode,it.storeAddress!!.city,it.storeAddress!!.country))
            {
                return false
            }

            isDatesGivenValid(it.dateValidUntill,it.dateValidFrom)

        }
           isDuplicateExisting(storeData)
        return true

    }

    private fun isStoreAddressDetailsNotNull(dateValidFrom: LocalDate?, street: String?, houseNumber: String?, houseNumberSuffix: String?, postalCode: String?, city: String?, country: String?): Boolean {
        if(dateValidFrom==null||street==null||houseNumber==null||houseNumberSuffix==null||postalCode==null||city==null||country==null)
        {
            return true
        }
        return false
    }

    private fun isDatesGivenValid(dateValidUntill: LocalDate?, dateValidFrom: LocalDate?) {

        if((dateValidUntill != null) && (dateValidUntill < dateValidFrom))
        {
            throw DateIncorrectException(dateValidUntill,dateValidFrom)
        }

    }

   private fun isDuplicateExisting(storeData: StoreData)
    {
        val result=storeDataRepo.findAll()
        val finalResult=result.filter{ filterdata->(filterdata.name==storeData.name)}.toList().isNotEmpty()
        if(finalResult)
        {
            throw DataAlreadyPresentException(storeData.name!!)
        }
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
//}  /* for(data in result)
//        {
//            if(data.name==storeData.name)
//            {
//                throw DataAlreadyPresentException(storeData.name!!)
//
//            }
//        }*/