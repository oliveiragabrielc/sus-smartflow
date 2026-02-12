package br.com.sus;

import br.com.sus.service.TriagemService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class TriagemServiceTest {

    @Inject
    TriagemService triagemService;

    @Test
    public void testClassificarVermelho() {
        String res = triagemService.classificar("dor no peito", 30);
        assertEquals("VERMELHO", res);
    }

    @Test
    public void testClassificarAmareloPorIdadeEFever() {
        String res = triagemService.classificar("febre", 65);
        assertEquals("AMARELO", res);
    }

    @Test
    public void testClassificarAmareloPorDor() {
        String res = triagemService.classificar("dor de cabeça", 25);
        assertEquals("AMARELO", res);
    }

    @Test
    public void testClassificarVerdeDefault() {
        String res = triagemService.classificar("coceira no braço", 20);
        assertEquals("VERDE", res);
    }

}
