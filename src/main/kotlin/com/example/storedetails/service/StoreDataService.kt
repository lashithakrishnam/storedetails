package com.example.storedetails.service

import com.example.storedetails.models.StoreData
import com.example.storedetails.repo.StoreDataRepo
import com.example.storedetails.validation.TryValidation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate
import com.example.storedetails.exception.InputFieldsAreNullException
import org.apache.catalina.Store
import java.util.Arrays.stream
import java.util.stream.StreamSupport.stream
import kotlin.streams.toList

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
        result.forEach{ data-> data.addressPeriod=data.addressPeriod!!.filter{filterData->(filterData.dateValidFrom!! <=date&&(filterData.dateValidUntill==null||filterData.dateValidUntill!!>=date)||(filterData.dateValidFrom!! >=date&&(filterData.dateValidUntill==null||filterData.dateValidUntill!!>=date)))} }
        val finalResult =result.filter { filterData->(filterData.addressPeriod!!.isNotEmpty()) }
        return finalResult
    }


    private fun presentRecords(result: List<StoreData>, refDate: LocalDate): List<StoreData> {

        result.forEach{data->data.addressPeriod=data.addressPeriod!!.filter{ filterData -> (filterData.dateValidFrom!! <= refDate && (filterData.dateValidUntill == null || filterData.dateValidUntill!! >= refDate)) }}

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

    fun updateStore(storeData: StoreData, storeId: Long): String {
        if( validation.validData(storeData))
        {
        storeDataRepo.save(storeData)}
        return "Store data of given id $storeId is updated"

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
    /*  for (data in result) {
             data.addressPeriod =
                 data.addressPeriod!!.filter { filterData -> (filterData.dateValidFrom!! <= refDate && (filterData.dateValidUntill == null || filterData.dateValidUntill!! >= refDate)) }
         }*/
    /*    val finalResult= result.stream().map{ filterData ->
            (filterData.addressPeriod!!.stream()
                .filter { filterData1 -> (filterData1.dateValidFrom!! <= refDate && (filterData1.dateValidUntill == null || filterData1.dateValidUntill!! >= refDate))}.toList())
        }.toList()*/


//        val finalResult= result.stream().map{ filterData -> (filterData.addressPeriod.getDateValid
    //println(finalResult)
    // finalResult =finalResult.stream().filter{ filterData->(filterData.addressPeriod!!.isNotEmpty()) }.collect(Collectors.toList())
    // return result.stream().filter { filterData -> (filterData.addressPeriod!!.isEmpty()) }.toList()


    //val finalresult= result.map{ data->data.addressPeriod!!.filter{ filterData -> (filterData.dateValidFrom!! <= refDate && (filterData.dateValidUntill == null || filterData.dateValidUntill!! >= refDate)) }}.toList()
    //println(finalresult)

}



