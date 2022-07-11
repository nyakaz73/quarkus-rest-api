package com.stackdev.models

import io.quarkus.hibernate.orm.panache.PanacheEntity
import io.quarkus.hibernate.orm.panache.PanacheEntityBase
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class ClientDetails(
    @Id
    @GeneratedValue
    var id:Long?=0,
    var name:String?=null,
    var surname:String?=null,
    var age:Int?=null,
    var email:String?=null,
    var basicSalary:Double?=null,
)