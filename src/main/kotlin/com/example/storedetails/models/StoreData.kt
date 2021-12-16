package com.example.storedetails.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener

import java.time.LocalDateTime

import javax.persistence.*


@Entity
@EntityListeners(AuditingEntityListener::class)
@JsonIgnoreProperties("createdAt")
 data class StoreData (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long=0,
    var name:String="",
    var status:String="",
    @Column(name = "createdAt",updatable = false)
    @CreatedDate
    var createdAt:LocalDateTime?=null,
    @LastModifiedDate
    @Column(name = "updateAt")
    var lastUpdated: LocalDateTime?=null,
    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "store_id")
    var addressPeriod: List<AddressPeriod> = emptyList(),
)



