package com.tinsa.groovy_poc.proxy

/**
 * Created by usuario on 20/06/2017.
 * Representa el mensaje de entrada al proceso de notificación.
 */
class Mensaje {
    String destino;
    String tipoEnvio;
    String mensaje;

    Mensaje(String destino, String tipoEnvio, String mensaje) {
        this.destino = destino
        this.tipoEnvio = tipoEnvio
        this.mensaje = mensaje
    }
}
