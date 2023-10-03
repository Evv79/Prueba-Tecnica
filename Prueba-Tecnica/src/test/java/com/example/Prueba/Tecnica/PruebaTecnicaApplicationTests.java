package com.example.Prueba.Tecnica;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
class PruebaTecnicaApplicationTests {
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
@SpringBootTest
public class TokenServiceTest {

    @InjectMocks
    private TokenService tokenService;

    @Mock
    private CreditCardRepository creditCardRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateToken() {
        CreditCard creditCard = new CreditCard("ValidCardNumber", 123, 10, 2023, "example@gmail.com");
        when(creditCardRepository.save(creditCard)).thenReturn(creditCard);

        String token = tokenService.createToken(creditCard);

        assertEquals(16, token.length()); // Verifica que el token tenga la longitud adecuada
    }

    //Para verificar el comportamiento de TokenService
}
	}

}
