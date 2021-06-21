package com.kotlin.restful.api.business

import com.kotlin.restful.api.model.Persona

interface IPersonaBusiness {

    fun listAll():List<Persona>
    fun load(idPersona:Long):Persona
    fun save(persona:Persona):Persona
    //Esta vacio el retorno porque retorna un void
    fun remove(idPersona:Long)


}