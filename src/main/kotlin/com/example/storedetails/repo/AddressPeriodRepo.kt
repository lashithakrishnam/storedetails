package com.example.storedetails.repo

import com.example.storedetails.models.AddressPeriod
import org.springframework.data.jpa.repository.JpaRepository

interface AddressPeriodRepo : JpaRepository<AddressPeriod, Long> {
}