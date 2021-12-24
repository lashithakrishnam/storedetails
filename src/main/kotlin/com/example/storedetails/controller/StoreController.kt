package com.example.storedetails.controller


import com.example.storedetails.configuration.ALL_STORES_END_POINT
import com.example.storedetails.configuration.BASE_URI
import com.example.storedetails.configuration.STORE_BY_ID_END_POINT


import com.example.storedetails.models.StoreData

import com.example.storedetails.service.StoreDataService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping(BASE_URI)
@Api(description="CRUD Operations on Stores ")
class StoreController (var  storeDataService: StoreDataService){



    @ApiOperation(value = "To get a list of available Stores By using refDate and FutureFlag values")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved list of Stores"),
        ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    ]
    )
    @GetMapping(ALL_STORES_END_POINT)
    fun getAllStores(@RequestParam(required = false)refDate:String?=null,@RequestParam(required = false)flag:Boolean=false
    ): List<StoreData> {

        return storeDataService.getStores(refDate,flag)

    }




    @ApiOperation(value="To Search the Store with the ID ")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved  Store by ID"),
        ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    ]
    )
    @GetMapping(STORE_BY_ID_END_POINT)

    fun getStoreById(@PathVariable storeId: Long): StoreData{

        return storeDataService.getStore(storeId)
    }



   @ApiOperation(value="To add a Store")
   @ApiResponses(value = [
       ApiResponse(code = 200, message = "Successfully Posted the  Store data"),
       ApiResponse(code = 401, message = "You are not authorized to post the resource"),
       ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
       ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
   ]
   )
    @PostMapping(ALL_STORES_END_POINT)
    fun addStoreData(@RequestBody storeData: StoreData): ResponseEntity<String> {
        return storeDataService.addStore(storeData)
    }



    @ApiOperation(value="To Update Store Details")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully Updated the  Store data"),
        ApiResponse(code = 201, message = "Store with the given Id is Created"),
        ApiResponse(code = 401, message = "You are not authorized to Update the resource"),
        ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    ]
    )
    @PutMapping(STORE_BY_ID_END_POINT)
    fun updateStoreData(@PathVariable storeId: Long,@RequestBody storeData: StoreData): ResponseEntity<String> {
        return storeDataService.updateStore(storeData,storeId)
    }



    @ApiOperation(value="To Delete the Store with the ID ")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully Deleted the  Store By ID"),
        ApiResponse(code = 204,message="Successfully Deleted the  Store By ID"),
        ApiResponse(code = 401, message = "You are not authorized to Delete the resource"),
        ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),

    ]
    )
    @DeleteMapping(STORE_BY_ID_END_POINT)
    fun deleteStore(@PathVariable storeId: Long):ResponseEntity<String>{
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
