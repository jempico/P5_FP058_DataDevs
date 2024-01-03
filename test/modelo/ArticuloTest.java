package modelo;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;

public class ArticuloTest {
    @Test
    void testGetters() {
        Articulo a = new Articulo(111, "mesa", 40.5F, 10.5F, 5);
        Assertions.assertAll(
                () -> Assertions.assertEquals("mesa", a.getDescripcion()),
                () -> Assertions.assertEquals(111, a.getId()),
                () -> Assertions.assertEquals(40.5, a.getPvp()),
                () -> Assertions.assertEquals(10.5, a.getGastosEnvio()),
                () -> Assertions.assertEquals(5, a.getPreparacion())
        );
    }
}