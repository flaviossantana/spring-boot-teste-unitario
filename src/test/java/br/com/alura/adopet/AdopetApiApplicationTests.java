package br.com.alura.adopet;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class AdopetApiApplicationTests {

	@Test
	void contextLoads() {
		assertNotNull("Teste");
	}

}
