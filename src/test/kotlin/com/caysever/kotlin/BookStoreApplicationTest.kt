package com.caysever.kotlin

import com.caysever.kotlin.model.Author
import com.caysever.kotlin.model.Book
import com.caysever.kotlin.repository.AuthorRepository
import com.caysever.kotlin.repository.BookRepository
import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldEqual
import io.kotlintest.specs.FeatureSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestContextManager

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = "test")
@DirtiesContext
class BookStoreApplicationTest : FeatureSpec() {

    @Autowired
    lateinit var bookRepository: BookRepository
    @Autowired
    lateinit var authorRepository: AuthorRepository
    @Autowired
    lateinit var testRestTemplate: TestRestTemplate


    init {
        TestContextManager(this.javaClass).prepareTestInstance(this)

        feature("crud book") {
            scenario("should insert book") {
                var author = Author(1, "Sabahattin", "Ali", 1930)
                var book = Book(1, "Kürk Mantolu Madonna", 1980, author)

                bookRepository.save(book)
            }
            scenario("should retrieve book") {
                var author = Author(1, "Sabahattin", "Ali", 1930)
                var book = Book(1, "Kürk Mantolu Madonna", 1980, author)

                bookRepository.save(book)

                val retrievedBook = bookRepository.findOne(1)
                retrievedBook?.id shouldBe 1L
                retrievedBook?.name shouldBe "Kürk Mantolu Madonna"
                retrievedBook?.year?.toInt() shouldEqual 1980
            }
        }
        feature("crud author") {
            scenario("should insert author") {
                var author = Author(1, "Sabahattin", "Ali", 1930)

                authorRepository.save(author)
            }
            scenario("should retrieve author") {
                var author = Author(1, "Sabahattin", "Ali", 1930)

                authorRepository.save(author)

                val retrievedAuthor = authorRepository.findOne(1)
                retrievedAuthor?.id shouldBe 1L
                retrievedAuthor?.name shouldBe "Sabahattin"
                retrievedAuthor?.surname shouldBe "Ali"
                retrievedAuthor?.age?.toInt() shouldEqual 1930
            }
        }
        feature("controller test") {
            scenario("should get author") {
                var author = Author(1, "Sabahattin", "Ali", 1930)

                authorRepository.save(author)

                val retrievedAuthor = testRestTemplate.getForEntity("/authors/{id}", Author::class.java, 1)
                retrievedAuthor.statusCode.value() shouldBe 200
                retrievedAuthor.body?.id shouldBe 1L
                retrievedAuthor.body?.name shouldBe "Sabahattin"
                retrievedAuthor.body?.surname shouldBe "Ali"
                retrievedAuthor.body?.age shouldEqual 1930
            }
            scenario("should retrieve books") {
                var author = Author(1, "Sabahattin", "Ali", 1930)
                var book = Book(1, "Kürk Mantolu Madonna", 1980, author)
                var book2 = Book(1, "Sırça Köşk", 1981, author)

                authorRepository.save(author)
                bookRepository.save(book)
                bookRepository.save(book2)

                val retrievedBooks = testRestTemplate.getForEntity("/books", List::class.java)
                retrievedBooks.statusCode.value() shouldBe 200
                retrievedBooks.body.size shouldBe 2
            }
        }
    }
}