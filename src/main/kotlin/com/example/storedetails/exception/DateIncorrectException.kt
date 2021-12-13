package com.example.storedetails.exception

import java.time.LocalDate

class DateIncorrectException(untillDate : LocalDate?, fromDate : LocalDate?):Exception("Given dateValidUntill $untillDate is less than dateValidFrom $fromDate" )
