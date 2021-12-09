package com.example.storedetails.exceptioncontroller

import com.example.storedetails.exception.DataAlreadyPresentException
import com.example.storedetails.exception.InputFieldsAreNullException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.format.DateTimeParseException

@ControllerAdvice
class GobalExceptionController {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNoSuchElementException(): ResponseEntity<String> {
        return ResponseEntity("No value is present in DB ,Please change the request", HttpStatus.NOT_FOUND)
    }



    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(): ResponseEntity<String> {
        return ResponseEntity("Input field of date field is not correct ,Please look into it", HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(DateTimeParseException::class)
    fun handleDateTimeParseException(): ResponseEntity<String> {
        return ResponseEntity("Input field is not correct of query ,Please look into date format give it in yyyy-mm-dd format", HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(DataAlreadyPresentException::class)
    fun handleDataAlreadyPresentException( ex:DataAlreadyPresentException ): ResponseEntity<String>{
        return ResponseEntity(ex.message,HttpStatus.BAD_REQUEST)

    }
    @ExceptionHandler(InputFieldsAreNullException::class)
    fun handleInputFieldsAreNullException( ex:InputFieldsAreNullException): ResponseEntity<String>{
        return ResponseEntity(ex.message,HttpStatus.BAD_REQUEST)

    }


    @ExceptionHandler(EmptyResultDataAccessException::class)
    fun handleInputFieldsAreNullException( ): ResponseEntity<String>{
        return ResponseEntity("Data with given id is not present in DataBase ",HttpStatus.BAD_REQUEST)

    }


}