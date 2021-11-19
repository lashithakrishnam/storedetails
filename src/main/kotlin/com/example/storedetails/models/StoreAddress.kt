package com.example.storedetails.models

import javax.persistence.*

@Entity

class StoreAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long=0

    @Column
    var street : String?=null
    @Column
    var houseNumber : String?=null
    @Column
    var houseNumberSuffix : String?=null
    @Column
    var postalCode : String?=null
    @Column
    var city : String?=null
    @Column
    var country : String?=null


}