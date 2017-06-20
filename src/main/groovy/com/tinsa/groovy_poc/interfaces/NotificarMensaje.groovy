package com.tinsa.groovy_poc.interfaces

import com.tinsa.groovy_poc.resource.ResultadoNotificar

/**
 * Created by usuario on 20/06/2017.
 * Interfaz NotificarMensaje del patrón Proxy, implementado para el tratamiento de los mensajes, cuyas acciones serán:
 *
 *  - Enviar la notificación. Cada clase que implemente este interfaz deberá implementar su propia manera de enviar una notificación.
 *  - Repositar en BDD. Se hará como añadido al envío de la notificación.
 *
 */
interface NotificarMensaje {

    /**
     * Tratar mensaje.
     *
     * @return ResultadoNotificar
     */
    ResultadoNotificar tratarMensaje()

}