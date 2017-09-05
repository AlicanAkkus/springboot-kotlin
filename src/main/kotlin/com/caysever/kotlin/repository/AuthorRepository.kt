package com.caysever.kotlin.repository

import com.caysever.kotlin.model.Author
import org.springframework.data.jpa.repository.JpaRepository

interface AuthorRepository : JpaRepository<Author, Long> {
}