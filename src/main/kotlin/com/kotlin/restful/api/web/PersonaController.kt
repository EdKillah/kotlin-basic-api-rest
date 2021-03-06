package com.kotlin.restful.api.web

import com.kotlin.restful.api.business.IPersonaBusiness
import com.kotlin.restful.api.exception.BusinessException
import com.kotlin.restful.api.exception.NotFoundException
import com.kotlin.restful.api.model.Persona
import com.kotlin.restful.api.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping(Constants.URL_BASE_PERSONAS)
class PersonaController {

    @Autowired
    val personaBusiness:IPersonaBusiness? = null

    @GetMapping
    fun list():ResponseEntity<List<Persona>>{
        return try {
            return ResponseEntity(personaBusiness!!.listAll(), HttpStatus.OK)
        }catch(e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }

    }


    @GetMapping("/{id}")
    fun load(@PathVariable("id")idPersona:Long):ResponseEntity<Persona>{
        return try{
            ResponseEntity(personaBusiness!!.load(idPersona), HttpStatus.OK)
        }catch(e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e:NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }


    @PostMapping
    fun insert(@RequestBody persona: Persona): ResponseEntity<Any>{
        return try{
            personaBusiness!!.save(persona)
            val responseHeader = HttpHeaders()
            responseHeader.set("location", Constants.URL_BASE_PERSONAS + "/" + persona.id)
            ResponseEntity(responseHeader, HttpStatus.CREATED)
        }catch(e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }


    @PutMapping
    fun update(@RequestBody persona: Persona):ResponseEntity<Any>{
        return try{
            personaBusiness!!.save(persona)
            ResponseEntity(HttpStatus.OK)
        }catch(e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id")id:Long ):ResponseEntity<Any>{
        return try{
            personaBusiness!!.remove(id)
            ResponseEntity(HttpStatus.OK)
        }catch(e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch(e:NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }














}