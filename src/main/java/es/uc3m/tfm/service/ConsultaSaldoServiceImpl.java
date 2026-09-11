package es.uc3m.tfm.service;

import es.uc3m.tfm.consultasaldo.ConsultaSaldoRequest;
import es.uc3m.tfm.consultasaldo.ConsultaSaldoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import java.math.BigDecimal;

@Endpoint
public class ConsultaSaldoServiceImpl {

    private static final String NAMESPACE_URI = "http://www.uc3m.es/tfm/consultasaldo";

    @Autowired
    private ConsultaSaldoBusinessServiceImpl businessService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ConsultaSaldoRequest")
    @ResponsePayload
    public ConsultaSaldoResponse consultar(@RequestPayload ConsultaSaldoRequest parameters) {
        String cuenta = parameters.getNumeroCuenta();

        // Ejecutar lógica de negocio
        BigDecimal saldo = businessService.obtenerSaldo(cuenta);
        String divisa = businessService.obtenerDivisa(cuenta);
        String estado = businessService.obtenerEstadoCuenta(cuenta);

        // Mapear la respuesta usando los stubs Jakarta autogenerados
        ConsultaSaldoResponse response = new ConsultaSaldoResponse();
        response.setSaldo(saldo);
        response.setDivisa(divisa);
        response.setEstado(estado);

        return response;
    }
}