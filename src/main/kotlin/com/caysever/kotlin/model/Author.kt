package com.caysever.kotlin.model

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "author")
class Author(id: Long = -1, name: String = "", surname: String = "", age: Int = 2099) : Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    var id: Long? = id
    var name: String? = name
    var surname: String? = surname
    var age: Int? = age

    override fun toString(): String {
        return "Author(id=$id, name=$name, surname=$surname, age=$age)"
    }
}