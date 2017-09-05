package com.caysever.kotlin.controller

import com.caysever.kotlin.model.Book
import com.caysever.kotlin.repository.BookRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class BookController(private val bookRepository: BookRepository) {

    @GetMapping("/books")
    fun books(): List<Book> = bookRepository.findAll()

    @GetMapping("/books/{id}")
    fun books(@PathVariable id: Long): Book = bookRepository.findOne(id)
}