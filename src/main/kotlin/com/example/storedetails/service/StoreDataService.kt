package com.example.storedetails.service

import com.example.storedetails.models.StoreData
import com.example.storedetails.repo.StoreDataRepo
import com.example.storedetails.validation.TryValidation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate
import com.example.storedetails.exception.InputFieldsAreNullException
import com.example.storedetails.models.StoreProjection
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity


@Service
class StoreDataService ( var storeDataRepo: StoreDataRepo) {


    @Autowired
    lateinit var validation: TryValidation



    fun getStores(refDate: String?, flag: Boolean): List<StoreData> {

        val date: LocalDate = validation.validDateFormat(refDate)
    /*    return storeDataRepo.getStores(date,flag)
    }*/
        val result = storeDataRepo.findAll()
        if (result.isEmpty()) {
            throw NoSuchElementException()
        }
        return getRecords(result, date, flag)

    }

    private fun getRecords(result: List<StoreData>, date: LocalDate, flag: Boolean): List<StoreData> {

        result.forEach { data ->
            data.addressPeriod =
                data.addressPeriod.filter { filterData -> ((filterData.dateValidFrom <= date) && (filterData.dateValidUntill == null || filterData.dateValidUntill!! >= date) || (filterData.dateValidFrom < date && flag)) }
        }
        return result.filter { filterData -> (filterData.addressPeriod.isNotEmpty()) }
    }




    fun getStore(storeId: Long): StoreData {
        return storeDataRepo.findById(storeId).get()

    }

    fun addStore(storeData: StoreData): ResponseEntity<String> {
        if (validation.validData(storeData)) {
            storeDataRepo.save(storeData)
            return ResponseEntity("StoreData is Successfully Saved ", HttpStatus.OK)
        }
        throw InputFieldsAreNullException()

    }

    fun deleteStore(storeId: Long): ResponseEntity<String> {
        storeDataRepo.deleteById(storeId)
        return ResponseEntity("Store data of given id $storeId is deleted", HttpStatus.OK)
    }

    fun updateStore(storeData: StoreData, storeId: Long): ResponseEntity<String> {
        storeData.addressPeriod.forEach { x->x.dateValidUntill?.let { validation.isDatesGivenValid(it,x.dateValidFrom) }}
        return if(!storeDataRepo.existsById(storeId)) {
            validation.isDuplicateExisting(storeData)
            storeDataRepo.save(storeData)
            ResponseEntity("Store with given data is Created",HttpStatus.CREATED)
        } else {

            validation.isDuplicateExisting(storeData,storeId)
            val newStoreData=storeDataRepo.findById(storeId).get()
            newStoreData.name=storeData.name
            newStoreData.status=storeData.status
            newStoreData.addressPeriod=storeData.addressPeriod
            storeDataRepo.save(newStoreData)
            ResponseEntity("Store data of given id $storeId is updated", HttpStatus.OK)
        }

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
    /*    val finalResult= result.map{ filterData ->
            (filterData.addressPeriod!!
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




