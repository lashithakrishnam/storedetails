package com.example.storedetails.repo

import com.example.storedetails.models.StoreData
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDate


@Repository
interface StoreDataRepo : JpaRepository<StoreData,Long> {

    @Query("SELECT store.name , store.status,ap  FROM StoreData store Join store.addressPeriod ap where (  ( (ap.dateValidFrom <= :refDate) and ((ap.dateValidUntill = null) or (ap.dateValidUntill >= :refDate)) )or ((ap.dateValidUntill >= :refDate)and(:flag=true))  ) ")
    fun getStores(@Param("refDate")refDate:LocalDate,@Param("flag")flag:Boolean): List<Any>



}
//(ap.dateValidFrom <= :refDate) and ((ap.dateValidUntill = null) or (ap.dateValidUntill >= :refDate))or

//(false=:flag) and(ap.dateValidUntill >= :refDate)
//
