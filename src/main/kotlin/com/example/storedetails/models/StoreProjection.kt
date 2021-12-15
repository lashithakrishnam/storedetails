package com.example.storedetails.models

import java.time.LocalDateTime


interface StoreProjection {
    fun getName(): String
     fun getStatus():String
     fun getUpdateAt(): LocalDateTime
    fun getAddressPeriod():List<AddressPeriod>






}
   /* fun getName():String
    fun getStatus():String
    fun getAddressPeriod():List<AddressPeriod>*/





/*
val updatedAt: String
val createdAt: String,*/
