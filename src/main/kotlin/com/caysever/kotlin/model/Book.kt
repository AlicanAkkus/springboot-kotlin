package com.caysever.kotlin.model

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "book")
class Book(id: Long = -1, name: String = "", year: Long = 2099, author: Author = Author()) : Serializable {

    @Id
    @GeneratedValue
    @Column(name = "book_id")
    var id: Long? = id
    var name: String? = name
    var year: Long? = year
    @ManyToOne
    @JoinColumn(name = "author_id")
    var author: Author = author

    override fun toString(): String {
        return "Book(id=$id, name=$name, year=$year, author=$author)"
    }
}