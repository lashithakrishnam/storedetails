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
class TryValidation (val storeDataRepo: StoreDataRepo ){

    fun validData(storeData: StoreData):Boolean
    {  val addressPeriod:List<AddressPeriod> =storeData.addressPeriod
        if(storeData.addressPeriod.isEmpty())

            {
                return false
            }

        addressPeriod.forEach { x ->
            x.dateValidUntill?.let { isDatesGivenValid(it,x.dateValidFrom) }
        }

        isDuplicateExisting(storeData)
        return true

    }



    fun isDatesGivenValid(dateValidUntil: LocalDate, dateValidFrom: LocalDate){

        if (dateValidUntil < dateValidFrom)
        {
            throw DateIncorrectException(dateValidUntil,dateValidFrom)
        }


    }

    fun isDuplicateExisting(storeData: StoreData,id:Long)
    {

       val storeName=storeDataRepo.storeExistsByName(storeData.name,id)
        if(storeName.isNotEmpty())
        {
            throw DataAlreadyPresentException(storeData.name)
        }


    }

     fun isDuplicateExisting(storeData: StoreData)
    {
        val storeName=storeDataRepo.findByName(storeData.name)
        if(storeName.name==storeData.name)
        {
            throw DataAlreadyPresentException(storeData.name)
        }

    }

    fun validDateFormat(refDate: String?): LocalDate {
        return if (refDate != null) { LocalDate.parse(refDate, DateTimeFormatter.ISO_DATE)}
        else { LocalDate.now() }

    }
}

/*
private fun isStoreAddressDetailsNotNull(dateValidFrom: LocalDate?, street: String?, houseNumber: String?, houseNumberSuffix: String?, postalCode: String?, city: String?, country: String?): Boolean {
    if(dateValidFrom==null||street==null||houseNumber==null||houseNumberSuffix==null||postalCode==null||city==null||country==null)
    {
        return true
    }
    return false
}
*/

/*  val result=storeDataRepo.findAll()
       val finalResult=result.filter{ filterData->(filterData.name==storeData.name)}.toList().isNotEmpty()
       if(finalResult)
       {
           throw DataAlreadyPresentException(storeData.name!!)
       }*/
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