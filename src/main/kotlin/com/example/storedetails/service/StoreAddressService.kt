package com.example.storedetails.service

import com.example.storedetails.repo.StoreAddressRepo
import org.springframework.stereotype.Service

@Service
class StoreAddressService {
    lateinit var storeAddresseRepo:StoreAddressRepo
}