package com.example.storedetails.exception




class DataAlreadyPresentException(private val name:String ) :Exception("Information already exists with the given store name $name ")



