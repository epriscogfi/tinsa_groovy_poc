package com.tinsa.groovy_poc.resource

import com.tinsa.groovy_poc.proxy.Mensaje
import com.tinsa.groovy_poc.proxy.NotificarProxy
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

/**
 * Created by usuario on 20/06/2017.
 * Representa la capa de exposición REST.
 *
 */
@RestController
class TinsaResource {

    @Autowired
    @Qualifier("notificarProxy")
    NotificarProxy notificarProxy


    @RequestMapping(value="/notificar", method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody ResultadoNotificar notificar(
            @RequestParam(value="destino") String destino,
            @RequestParam(value="tipoMensaje") String tipoMensaje,
            @RequestParam(value="mensaje") String mensaje){

        // A partir de los datos de entrada, se construye el objeto Mensaje y que se corresponderá con la notificación a enviar
        Mensaje msg = new Mensaje(destino, tipoMensaje, mensaje)

        // Generación de la notificación
        return notificarProxy.inferImpl(msg).tratarMensaje()

    }

}
