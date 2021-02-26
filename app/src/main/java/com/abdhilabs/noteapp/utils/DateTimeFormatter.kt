package com.abdhilabs.noteapp.utils

import java.text.SimpleDateFormat
import java.util.*

object DateTimeFormatter {
    private val dateOutput = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale("in", "ID"))

    fun getDateFromString(dateString: String): String {
        val date = parseDate(dateString)
        return dateOutput.format(date)
    }

    private fun parseDate(dateString: String): Date {
        val dateInput = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
        return dateInput.parse(dateString)!!
    }
}