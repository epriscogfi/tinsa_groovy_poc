package com.tinsa.groovy_poc.proxy

import com.tinsa.groovy_poc.entity.Notificacion
import com.tinsa.groovy_poc.impl.NotificarFAXImpl
import com.tinsa.groovy_poc.impl.NotificarMailImpl
import com.tinsa.groovy_poc.impl.NotificarSMSImpl
import com.tinsa.groovy_poc.impl.NotificarWhatchaImpl
import com.tinsa.groovy_poc.interfaces.NotificarMensaje
import com.tinsa.groovy_poc.repository.NotificacionRepository
import com.tinsa.groovy_poc.resource.ResultadoNotificar
import com.tinsa.groovy_poc.utils.Constantes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

/**
 * Created by usuario on 20/06/2017.
 * Implementa el interfaz NotificarMensaje y es la parte Proxy, propiamente dicha, dentro del patrón Proxy implementado.
 */
@Service
class NotificarProxy implements NotificarMensaje{

    /** Notificar, representará la implementación elegica para el envío de la notificación. */
    NotificarMensaje notificar

    /** Objeto Mensaje, que representa la información necesaria para llevar a cabo la notificación. */
    Mensaje msg

    @Autowired
    @Qualifier("notificacionRepository")
    NotificacionRepository notificacionRepo

    /**
     * Infiere la implementación que realizará la notificación en función del tipo de mensaje a enviar.
     *
     * @param msg Mensaje
     * @return devuelve a sí mismo.
     */
    NotificarProxy inferImpl(Mensaje msg) {

        this.msg = msg

        switch (msg.getTipoEnvio()){

            case Constantes.MAIL:
                this.notificar = new NotificarMailImpl(msg)
                break

            case Constantes.SMS:
                this.notificar = new NotificarSMSImpl(msg)
                break

            case Constantes.FAX:
                this.notificar = new NotificarFAXImpl(msg)
                break

            case Constantes.WHATCHA:
                this.notificar = new NotificarWhatchaImpl(msg)
                break

            default:
                break

        }

        return this
    }

    @Override
    ResultadoNotificar tratarMensaje() {
        // Envía la notificación por el canal inferido (SMS, mail, FAX...)
        ResultadoNotificar resultadoNotificar = notificar.tratarMensaje()

        //Construimos la entidad Notifación que será repositada
        Notificacion notificacion = new Notificacion(msg.getDestino(), msg.getTipoEnvio(), msg.getMensaje(), resultadoNotificar.getCodEstadoMensaje())
        notificacionRepo.save( notificacion)

        //Informamos la propiedad id
        resultadoNotificar.setIdMensaje(notificacion.getId())

        //Comprobación. Imprimimos por consola el conjunto de todas las notificaciones enviadas.
        System.out.println(notificacionRepo.findAll().toString())

        resultadoNotificar
    }

}
