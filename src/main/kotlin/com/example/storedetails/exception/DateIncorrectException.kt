package com.example.storedetails.exception

import java.time.LocalDate

class DateIncorrectException(untilDate : LocalDate?, fromDate : LocalDate?):Exception("Given dateValidUntil $untilDate is less than dateValidFrom $fromDate" )
