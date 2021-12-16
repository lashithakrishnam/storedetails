package com.example.storedetails.models

import javax.persistence.*

@Entity

data class StoreAddress (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long=0,
    var street : String="",
    var houseNumber : String="",
    var houseNumberSuffix : String="",
    var postalCode : String="",
    var city : String="",
    var country : String=""


    )