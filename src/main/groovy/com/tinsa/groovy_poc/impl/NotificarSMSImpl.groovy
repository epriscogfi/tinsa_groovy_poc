package com.tinsa.groovy_poc.impl

import com.tinsa.groovy_poc.interfaces.NotificarMensaje
import com.tinsa.groovy_poc.proxy.Mensaje
import com.tinsa.groovy_poc.resource.ResultadoNotificar
import org.apache.http.HttpResponse
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients
import com.tinsa.groovy_poc.utils.Constantes


/**
 * Created by usuario on 20/06/2017.
 */
class NotificarSMSImpl implements NotificarMensaje{

    /** Mensaje a notificar. */
    Mensaje msg

    @Override
    ResultadoNotificar tratarMensaje() {

        HttpClient httpClient = HttpClients.createDefault()

        String url  = Constantes.SERVICE_SMS_URL + "?" + Constantes.PARAM_PHONE + "=" + this.msg.getDestino() + "&" + Constantes.PARAM_MESSAGE + "=" + this.msg.getMensaje()

        HttpGet request = new HttpGet(url)
        request.setHeader("Accept", "application/json")

        HttpResponse response = httpClient.execute(request)

        int codEstMsg = response.getStatusLine().getStatusCode() == Constantes.RESULT_OK ? Constantes.ESTADO_ENVIADO : Constantes.ESTADO_NO_ENVIADO
        String descEstMsg = codEstMsg == Constantes.ESTADO_ENVIADO ? Constantes.DESC_ESTADO_ENVIADO : Constantes.DESC_ESTADO_NO_ENVIADO

        ResultadoNotificar resultNotify = new ResultadoNotificar()
        resultNotify.setCodEstadoMensaje(codEstMsg)
        resultNotify.setDescEstadoMensaje(descEstMsg)

        resturn resultNotify
    }

}
