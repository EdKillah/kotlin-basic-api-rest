package com.kotlin.restful.api.business.impl

import com.kotlin.restful.api.business.IPersonaBusiness
import com.kotlin.restful.api.dao.IPersonaRepository
import com.kotlin.restful.api.exception.BusinessException
import com.kotlin.restful.api.exception.NotFoundException
import com.kotlin.restful.api.model.Persona
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import kotlin.jvm.Throws


@Service
class PersonaBusiness: IPersonaBusiness {

    @Autowired
    val personaRespository: IPersonaRepository? = null

    @Throws(BusinessException::class)
    override fun listAll(): List<Persona> {
        try{
            return personaRespository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun load(idPersona: Long): Persona {
        val op: Optional<Persona>
        try{
            op = personaRespository!!.findById(idPersona)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
        if(!op.isPresent){
            throw NotFoundException("No se encontró la persona con id ${idPersona}")
        }
        return op.get()
    }

    @Throws(BusinessException::class)
    override fun save(persona: Persona): Persona {
        try{
            return personaRespository!!.save(persona)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class)
    override fun remove(idPersona: Long) {
        val op: Optional<Persona>
        try{
            op = personaRespository!!.findById(idPersona)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!op.isPresent){
            throw NotFoundException("No se encontró la persona con el id ${idPersona}")
        } else {
            try{
                personaRespository!!.delete(op.get())
            }catch(e:Exception){
                throw BusinessException(e.message)
            }
        }

    }


}