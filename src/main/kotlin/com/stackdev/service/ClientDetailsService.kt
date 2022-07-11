package com.stackdev.service

import com.stackdev.models.ClientDetails
import com.stackdev.repositories.ClientDetailsRepository
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.transaction.Transactional

@ApplicationScoped
@Transactional
class ClientDetailsService {

    @Inject
    lateinit var clientDetailsRepository: ClientDetailsRepository

    fun saveClient(clientDetails:ClientDetails){clientDetailsRepository.persist(clientDetails)}

    fun findAll():List<ClientDetails>{ return clientDetailsRepository.listAll()}

    fun findById(id:Long):ClientDetails?{ return clientDetailsRepository.findByIdOptional(id).orElse(null)}

    fun findByName(name:String):ClientDetails?{return clientDetailsRepository.findByNameOptional(name)}

    fun deleteClient(id:Long){clientDetailsRepository.deleteById(id)}

    fun updateClient(clientDetails:ClientDetails){
        clientDetailsRepository.update(
            ""+
                    "name = '${clientDetails.name}', "+
                    "surname = '${clientDetails.surname}', "+
                    "age = ${clientDetails.age}, "+
                    "email = '${clientDetails.email}', "+
                    "basicSalary = ${clientDetails.basicSalary}"+
                    "where id = ${clientDetails.id}"
        )
    }
}