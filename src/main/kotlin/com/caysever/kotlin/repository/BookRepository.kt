package com.caysever.kotlin.repository

import com.caysever.kotlin.model.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<Book, Long> {

    fun findByName(name: String) : Book

    fun findByYearBetween(from: Int, to: Int) : List<Book>

}