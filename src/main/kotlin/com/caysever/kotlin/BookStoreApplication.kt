package com.caysever.kotlin


import com.caysever.kotlin.model.Author
import com.caysever.kotlin.model.Book
import com.caysever.kotlin.repository.AuthorRepository
import com.caysever.kotlin.repository.BookRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class BookStoreApplication {

    @Bean
    fun init(bookRepository: BookRepository, authorRepository: AuthorRepository) = CommandLineRunner {
        var author = Author(1, "Sabahattin", "Ali", 1930)

        var book = Book(1, "Kürk Mantolu Madonna", 1980, author)
        var book2 = Book(2, "Sırça Köşk", 1981, author)


        authorRepository.save(author)
        bookRepository.save(book)
        bookRepository.save(book2)


        val firstBook = bookRepository.findOne(1).toString()
        println(firstBook)

        bookRepository.findAll().forEach { book -> println(book) }
    }

}

fun main(args: Array<String>) {
    SpringApplication.run(BookStoreApplication::class.java, *args)
}