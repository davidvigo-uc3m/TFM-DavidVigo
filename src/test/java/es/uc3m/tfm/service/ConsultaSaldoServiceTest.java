package es.uc3m.tfm.service;

import es.uc3m.tfm.consultasaldo.ConsultaSaldoRequest;
import es.uc3m.tfm.consultasaldo.ConsultaSaldoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ConsultaSaldoServiceTest {

    private ConsultaSaldoServiceImpl consultaSaldoService;
    private ConsultaSaldoBusinessServiceImpl businessService;

    @BeforeEach
    public void setUp() {
        // Se instancia los objetos reales (endpoint y capa de negocio) manualmente 
        consultaSaldoService = new ConsultaSaldoServiceImpl();
        businessService = new ConsultaSaldoBusinessServiceImpl();

        ReflectionTestUtils.setField(consultaSaldoService, "businessService", businessService);
    }

    @Test
    public void testConsultarSaldoExitoso() {
        // 1. Datos de Entrada de Peticion
        String cuentaPrueba = "ES1234567890";
        ConsultaSaldoRequest request = new ConsultaSaldoRequest();
        request.setNumeroCuenta(cuentaPrueba);

        // 2. Ejecución
        ConsultaSaldoResponse response = consultaSaldoService.consultar(request);

        // 3. Validaciones de respuesta esperada
        assertNotNull(response, "La respuesta no debería ser nula");
        assertEquals(new BigDecimal("1500.50"), response.getSaldo(), "El saldo devuelto no es correcto");
        assertEquals("EUR", response.getDivisa(), "La divisa devuelta no es correcta");
        assertEquals("ACTIVA", response.getEstado(), "El estado de la cuenta no es correcto");
    }
}