package com.example.storedetails.service

import com.example.storedetails.models.StoreData
import com.example.storedetails.repo.StoreDataRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class StoreDataService ( ){

//
  @Autowired
lateinit var storeDataRepo: StoreDataRepo

    fun getStores(): List<StoreData>{
        return storeDataRepo.findAll();

    }

    fun getStore(storeId: Long): Optional<StoreData> {
        return storeDataRepo.findById(storeId)

    }

    fun addStore(storeData: StoreData): String {
        storeDataRepo.save(storeData)
        return "datasaved"


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





}