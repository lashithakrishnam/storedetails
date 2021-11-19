package com.example.storedetails.service

import com.example.storedetails.repo.AddressPeriodRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AddressPeriodService {
    @Autowired
    lateinit var  addressPeriodRepo: AddressPeriodRepo
}