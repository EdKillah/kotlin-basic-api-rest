package com.kotlin.restful.api

import com.kotlin.restful.api.dao.IPersonaRepository
import com.kotlin.restful.api.model.Persona
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@SpringBootApplication
class ApiApplication:CommandLineRunner{

	@Autowired
	val personaRepository:IPersonaRepository? = null

	override fun run(vararg args: String?){

		val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
		val persona01 = Persona(12345, "Eduard","Jimenez", LocalDate.parse("22-05-1998", formatter))
		personaRepository!!.save(persona01)

	}

}

fun main(args: Array<String>) {
	runApplication<ApiApplication>(*args)
}
