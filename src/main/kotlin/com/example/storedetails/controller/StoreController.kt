package com.example.storedetails.controller

import com.example.storedetails.models.StoreData
import com.example.storedetails.service.StoreDataService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("store-service/v1")

class StoreController (val storeDataService: StoreDataService){

//    @Autowired
//   lateinit var  storeDataService: StoreDataService

    @GetMapping("/stores")
    fun getAllStores(): List<StoreData> {
        return storeDataService.getStores()
    }

    @GetMapping("/stores/{storeId}")
    fun getStoreById(@PathVariable storeId: Long): StoreData {
        if(storeDataService.getStore(storeId).isPresent)
        {
            return storeDataService.getStore(storeId).get()
        }
        else
        {
        return storeDataService.getStore(1).get()
        }

    }

    @PostMapping("/stores")
    fun addStoredata(@RequestBody storeData: StoreData): String {
        return storeDataService.addStore(storeData)
    }
}







//}val b:String?="Optional.empty"
//if(storeDataService?.getStore(storeId)?.equals(b) == true)
//{  print("inside if")
//    return storeDataService!!.getStore(1)
//}
//else{
//    print("inside else")