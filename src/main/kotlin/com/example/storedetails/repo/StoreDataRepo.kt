package com.example.storedetails.repo

import com.example.storedetails.models.StoreData
import org.springframework.data.jpa.repository.JpaRepository

interface StoreDataRepo : JpaRepository<StoreData,Long> {
}