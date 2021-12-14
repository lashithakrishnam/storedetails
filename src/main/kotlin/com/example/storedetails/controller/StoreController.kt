package com.example.storedetails.controller


import com.example.storedetails.configuration.ALL_STORES_END_POINT
import com.example.storedetails.configuration.BASE_URI
import com.example.storedetails.configuration.STORE_BY_ID_END_POINT
import com.example.storedetails.models.StoreData
import com.example.storedetails.service.StoreDataService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(BASE_URI)

class StoreController (var  storeDataService: StoreDataService){



    @GetMapping(ALL_STORES_END_POINT)
    fun getAllStores(@RequestParam(required = false)refDate:String?=null,@RequestParam(required = false)flag:Boolean=false
    ): List<Any> {

        return storeDataService.getStores(refDate,flag)

    }

    @GetMapping(STORE_BY_ID_END_POINT)

    fun getStoreById(@PathVariable storeId: Long): StoreData{

        return storeDataService.getStore(storeId)
    }

    @PostMapping(ALL_STORES_END_POINT)
    fun addStoreData(@RequestBody storeData: StoreData): String {
        return storeDataService.addStore(storeData)
    }
    @PutMapping(STORE_BY_ID_END_POINT)
    fun updateStoreData(@PathVariable storeId: Long,@RequestBody storeData: StoreData):String{
        return storeDataService.updateStore(storeData,storeId)
    }
    @DeleteMapping(STORE_BY_ID_END_POINT)
    fun deleteStore(@PathVariable storeId: Long):String{
        return storeDataService.deleteStore(storeId)
    }
}







//}val b:String?="Optional.empty"
//if(storeDataService?.getStore(storeId)?.equals(b) == true)
//{  print("inside if")
//    return storeDataService!!.getStore(1)
//}
//else{
//    print("inside else")

//
//if(storeDataService.getStore(storeId).isPresent)
//{
//    return storeDataService.getStore(storeId).get()
//}
//else
//{
//    return "The given id $storeId is not present"
//    //storeDataService.getStore(1).get()
//}
//   @Autowired
//   lateinit var  storeDataService: StoreDataService
//println("$refDate  $futureFlag")
