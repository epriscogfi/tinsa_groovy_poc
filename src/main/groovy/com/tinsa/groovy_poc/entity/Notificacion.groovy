package com.tinsa.groovy_poc.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * Created by usuario on 20/06/2017.
 * Clase Notificacion, es una entidad que se corresponde con la tabla NOTIFICACION
 */
@Entity
class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String destino;
    String tipoEnvio;
    String mensaje;
    Integer estado;
}
