package modelo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {
    @Test
    void testGettersEstandard() {
        Cliente c = new ClienteEstandard("Ana", "C/Estevez 1", "ana@gmail.com", "44488765J");
        Assertions.assertAll(
                () -> Assertions.assertEquals("Ana", c.getNombre()),
                () -> Assertions.assertEquals("C/Estevez 1", c.getDomicilio()),
                () -> Assertions.assertEquals("ana@gmail.com", c.getEmail()),
                () -> Assertions.assertEquals("44488765J", c.getNif()),
                () -> Assertions.assertEquals(1, c.getTipoCliente())
        );
    }
    @Test
    void testGettersPremium() {
        Cliente p = new ClientePremium("Pere", "C/Cardona 7", "pere@gmail.com", "11188765L");
        Assertions.assertAll(
                () -> Assertions.assertEquals("Pere", p.getNombre()),
                () -> Assertions.assertEquals("C/Cardona 7", p.getDomicilio()),
                () -> Assertions.assertEquals("pere@gmail.com", p.getEmail()),
                () -> Assertions.assertEquals("11188765L", p.getNif()),
                () -> Assertions.assertEquals(2, p.getTipoCliente())
        );
    }
}