package com.example.storedetails.models

import javax.persistence.*

@Entity

data class StoreAddress (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long?=null,
    val street : String?=null,
    var houseNumber : String?=null,
    var houseNumberSuffix : String?=null,
    var postalCode : String?=null,
    var city : String?=null,
    var country : String?=null


    )