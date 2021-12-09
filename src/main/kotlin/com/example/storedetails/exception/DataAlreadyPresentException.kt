package com.example.storedetails.exception

import org.springframework.stereotype.Component


class DataAlreadyPresentException(val name:String ) :Exception("Information already exists with the given store name $name ")



