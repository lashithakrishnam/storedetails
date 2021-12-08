package com.example.storedetails.validation

import com.example.storedetails.models.AddressPeriod
import com.example.storedetails.models.StoreAddress
import com.example.storedetails.models.StoreData
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Service
class TryValidation {

    fun validData(storeData: StoreData):Boolean
    {  var addressPeriod:List<AddressPeriod>? =storeData.addressPeriod
        var storeAddress: StoreAddress? =addressPeriod?.get(0)?.storeAddress

        if(storeData.name==null||storeData.status==null|| addressPeriod?.get(0)?.dateValidFrom ==null||storeAddress?.city==null||storeAddress?.country==null||storeAddress?.postalCode==null||storeAddress?.street==null||storeAddress?.houseNumberSuffix==null||storeAddress?.houseNumber==null)
        {
            return false
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