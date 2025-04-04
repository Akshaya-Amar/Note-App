package com.amar.mynotes.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object AppUtils {
     fun getFormattedDateTime(): String {
          val currentDateTime = LocalDateTime.now()
          val formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy hh:mm:ss a")
          return currentDateTime.format(formatter)
     }
}