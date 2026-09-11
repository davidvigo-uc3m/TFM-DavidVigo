package es.uc3m.tfm.service;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class ConsultaSaldoBusinessServiceImpl {

    public BigDecimal obtenerSaldo(String numeroCuenta) {
        // Simulación de negocio que valida el numero de cuenta sea correcto
        if ("ES1234567890".equals(numeroCuenta)) {
            return new BigDecimal("1500.50");
        } else {
            return BigDecimal.ZERO;
        }
    }

    public String obtenerDivisa(String numeroCuenta) {
        return "EUR";
    }

    public String obtenerEstadoCuenta(String numeroCuenta) {
        if ("ES1234567890".equals(numeroCuenta)) {
            return "ACTIVA";
        }
        return "INEXISTENTE";
    }
}