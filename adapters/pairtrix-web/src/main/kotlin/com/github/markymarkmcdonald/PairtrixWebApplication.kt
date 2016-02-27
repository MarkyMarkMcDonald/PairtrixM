package com.github.markymarkmcdonald

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class PairtrixWebApplication

fun main(args: Array<String>) {
    SpringApplication.run(PairtrixWebApplication::class.java, *args)
}
