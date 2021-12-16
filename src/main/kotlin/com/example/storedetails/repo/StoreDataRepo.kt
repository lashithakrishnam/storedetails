package com.example.storedetails.repo



import com.example.storedetails.models.StoreData
import com.example.storedetails.models.StoreProjection
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDate


@Repository
interface StoreDataRepo : JpaRepository<StoreData,Long> {

    @Query("SELECT store.name as name ,store.status as status ,store.lastUpdated as updateAt, ap as addressPeriod FROM StoreData store JOIN store.addressPeriod ap WHERE :refDate BETWEEN ap.dateValidFrom AND COALESCE(ap.dateValidUntill,:refDate)  OR ((ap.dateValidFrom > :refDate)AND(:flag=true))   ")
    fun getStores(@Param("refDate")refDate:LocalDate,@Param("flag")flag:Boolean): List<StoreProjection>

    @Query("SELECT store.name FROM StoreData store where (store.name= ?1)and(store.id!=?2)")
    fun storeExistsByName(@Param("name") name:String,@Param("id")id:Long):List<String>
}
//( (ap.dateValidFrom <= :refDate) and ((ap.dateValidUntill = null) or (ap.dateValidUntill >= :refDate)) )