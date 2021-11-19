package com.example.storedetails.repo

import com.example.storedetails.models.StoreAddress

import org.springframework.data.jpa.repository.JpaRepository

interface StoreAddressRepo : JpaRepository<StoreAddress, Long> {
}