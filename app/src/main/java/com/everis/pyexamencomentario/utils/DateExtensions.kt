package com.everis.pyexamencomentario.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun LocalDateTime.formatChangeNote() : String
= this.format(DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm:ss"))
