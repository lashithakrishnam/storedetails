package com.example.storedetails.models

import javax.persistence.*

@Entity

data class StoreAddress (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long?=null,

    @Column
    val street : String?=null,
    @Column
    var houseNumber : String?=null,
    @Column
    var houseNumberSuffix : String?=null,
    @Column
    var postalCode : String?=null,
    @Column
    var city : String?=null,
    @Column
    var country : String?=null


    )