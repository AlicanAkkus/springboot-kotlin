package com.caysever.kotlin.controller

import com.caysever.kotlin.model.Author
import com.caysever.kotlin.repository.AuthorRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthorController(private val authorRepository: AuthorRepository) {

    @GetMapping("/authors")
    fun authors(): List<Author> = authorRepository.findAll()

    @GetMapping("/authors/{id}")
    fun authors(@PathVariable id: Long): Author = authorRepository.findOne(id)
}