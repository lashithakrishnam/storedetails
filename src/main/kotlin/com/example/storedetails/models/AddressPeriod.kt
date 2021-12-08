package com.example.storedetails.models

import java.time.LocalDate
import javax.persistence.*

@Entity

data class AddressPeriod (



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long?=null,
    @Column
    var dateValidFrom: LocalDate?=null,
    @Column
    var dateValidUntill:LocalDate?=null,
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "fk_storeAddress_id")
    var storeAddress : StoreAddress?=null
)