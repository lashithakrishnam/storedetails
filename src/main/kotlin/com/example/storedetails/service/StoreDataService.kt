package com.example.storedetails.service

import com.example.storedetails.models.StoreData
import com.example.storedetails.repo.StoreDataRepo
import com.example.storedetails.validation.TryValidation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate
import com.example.storedetails.exception.InputFieldsAreNullException

@Service
class StoreDataService ( var storeDataRepo: StoreDataRepo) {


    @Autowired
    lateinit var validation: TryValidation


    fun getStores(refDate: String?, flag: Boolean): List<StoreData> {

        val date: LocalDate = validation.validDateFormat(refDate)
        val result = storeDataRepo.findAll()
        if (result.isEmpty()) {
            throw NoSuchElementException()
        }
        return getRecords(result, date, flag)

    }

    private fun getRecords(result: List<StoreData>, date: LocalDate, flag: Boolean): List<StoreData> {

        result.forEach { data ->
            data.addressPeriod =
                data.addressPeriod!!.filter { filterData -> ((filterData.dateValidFrom!! <= date) && (filterData.dateValidUntill == null || filterData.dateValidUntill!! >= date) || (filterData.dateValidUntill!! >= date && flag)) }
        }
        return result.filter { filterData -> (filterData.addressPeriod!!.isNotEmpty()) }

    }


    fun getStore(storeId: Long): StoreData {
        return storeDataRepo.findById(storeId).get()

    }

    fun addStore(storeData: StoreData): String {
        val typeOFInput = "Post"
        if (validation.validData(storeData, typeOFInput)) {
            storeDataRepo.save(storeData)
            return "dataSaved"
        } else {
            throw InputFieldsAreNullException()
        }
    }

    fun deleteStore(storeId: Long): String {
        storeDataRepo.deleteById(storeId)
        return "Store data of given id $storeId is deleted"
    }

    fun updateStore(storeData: StoreData, storeId: Long): String {
        val typeOFInput = "Put"
        val storeDataValue = storeDataRepo.findById(storeId).get()

        if (validation.validData(storeDataValue, typeOFInput)) {

            storeDataRepo.save(storeData)
            return "Store data of given id $storeId is updated"
        }
        return "Store data of given id $storeId is updated"

    }
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
//latent var storeDataRepo: StoreDataRepo
    /*  for (data in result) {
             data.addressPeriod =
                 data.addressPeriod!!.filter { filterData -> (filterData.dateValidFrom!! <= refDate && (filterData.dateValidUntil == null || filterData.dateValidUntil!! >= refDate)) }
         }*/
    /*    val finalResult= result.stream().map{ filterData ->
            (filterData.addressPeriod!!.stream()
                .filter { filterData1 -> (filterData1.dateValidFrom!! <= refDate && (filterData1.dateValidUntil == null || filterData1.dateValidUntil!! >= refDate))}.toList())
        }.toList()*/


//        val finalResult= result.stream().map{ filterData -> (filterData.addressPeriod.getDateValid
    //println(finalResult)
    // finalResult =finalResult.stream().filter{ filterData->(filterData.addressPeriod!!.isNotEmpty()) }.collect(Collectors.toList())
    // return result.stream().filter { filterData -> (filterData.addressPeriod!!.isEmpty()) }.toList()


    //val finalResult= result.map{ data->data.addressPeriod!!.filter{ filterData -> (filterData.dateValidFrom!! <= refDate && (filterData.dateValidUntil == null || filterData.dateValidUntil!! >= refDate)) }}.toList()
    //println(finalResult)
    /* private fun presentRecords(result: List<StoreData>, refDate: LocalDate): List<StoreData> {

         result.forEach { data ->
             data.addressPeriod =
                 data.addressPeriod!!.filter { filterData -> (filterData.dateValidFrom!! <= refDate && (filterData.dateValidUntil == null || filterData.dateValidUntil!! >= refDate)) }
         }

         return result.filter { filterData -> (filterData.addressPeriod!!.isNotEmpty()) }
     }
 */




