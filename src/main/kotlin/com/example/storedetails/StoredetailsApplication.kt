package com.example.storedetails

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class StoredetailsApplication

fun main(args: Array<String>) {
	runApplication<StoredetailsApplication>(*args)
}
