package com.example.storedetails.service

import com.example.storedetails.models.AddressPeriod
import com.example.storedetails.models.StoreData
import com.example.storedetails.repo.StoreDataRepo
import com.example.storedetails.validation.TryValidation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate
import com.example.storedetails.exception.InputFieldsAreNullException

@Service
class StoreDataService ( var storeDataRepo: StoreDataRepo){



    @Autowired
     lateinit var validation: TryValidation



    fun getStores(refDate:String?,FutureFlage:Boolean): List<StoreData>{

      val date:LocalDate?=validation.validDateformat(refDate)
        var result=storeDataRepo.findAll()
        if(result.isEmpty())
        {
            throw NoSuchElementException()
        }
        return if(date==null) {
            result
        } else if (FutureFlage) {

            presentAndFutureRecords(result, date)
        } else {

            presentRecords(result, date)
        }

    }

    private fun presentAndFutureRecords(result: List<StoreData>, date: LocalDate): List<StoreData> {
        for(data in result)
        {
            data.addressPeriod=data.addressPeriod!!.filter{filterData->(filterData.dateValidFrom!! <=date&&(filterData.dateValidUntill==null||filterData.dateValidUntill!!>=date)||(filterData.dateValidFrom!! >=date&&(filterData.dateValidUntill==null||filterData.dateValidUntill!!>=date)))}
        }
        val finalResult =result.filter { filterData->(filterData.addressPeriod!!.isNotEmpty()) }
        return finalResult

    }



    private fun presentRecords(result: List<StoreData>, refDate: LocalDate): List<StoreData> {
        for(data in result){
            data.addressPeriod=data.addressPeriod!!.filter{filterData->(filterData.dateValidFrom!! <=refDate&&(filterData.dateValidUntill==null||filterData.dateValidUntill!!>=refDate) )}
        }
        val finalResult =result.filter { filterData->(filterData.addressPeriod!!.isNotEmpty()) }
        return finalResult
        }





    fun getStore(storeId: Long): StoreData {
        return storeDataRepo.findById(storeId).get()

    }

    fun addStore(storeData: StoreData): String {
       if( validation.validData(storeData))
       {
        storeDataRepo.save(storeData)
        return "datasaved"}
        else
       {  throw InputFieldsAreNullException()
       }


    }

    fun deleteStore(storeId: Long): String {
        storeDataRepo.deleteById(storeId)
        return "Store data of given id $storeId is deleted"

    }
//        if(storeDataRepo?.findById(storeId)== "empty")
//        { print("inside if")
//            print(storeDataRepo?.findById(storeId))
//            return storeDataRepo!!.findById(1)}
//        else
//        {print("inside else")
//            return storeDataRepo!!.findById(storeId)
//        }

//       return storeDataRepo?.findById(storeId) ?: storeDataRepo!!.findById(1)

    //
//  @Autowired
//lateinit var storeDataRepo: StoreDataRepo





}



