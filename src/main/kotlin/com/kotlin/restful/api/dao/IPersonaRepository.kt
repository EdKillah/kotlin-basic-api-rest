package com.kotlin.restful.api.dao

import com.kotlin.restful.api.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IPersonaRepository: JpaRepository<Persona, Long> {


}