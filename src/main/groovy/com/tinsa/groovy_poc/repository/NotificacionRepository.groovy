package com.tinsa.groovy_poc.repository

import com.tinsa.groovy_poc.entity.Notificacion
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * Created by usuario on 20/06/2017.
 * Interfaz NotificacionRepository, que extiende de CrudRepository y provee las operaciones CRUD básicas de la entidad Notificación
 */
@Repository("notificacionRepository")
interface NotificacionRepository extends CrudRepository<Notificacion, Long>{

}