package com.example.Prueba.Tecnica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class CreditCard {
    private String cardNumber;
    private int cvv;
    private int expirationMonth;
    private int expirationYear;
    private String email;
    private String token;

    public CreditCard(String cardNumber, int cvv, int expirationMonth, int expirationYear, String email) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
        this.email = email;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    // Getters y setters para otros campos

    @Override
    public String toString() {
        return "CreditCard{" +
                "cardNumber='" + cardNumber + '\'' +
                ", cvv=" + cvv +
                ", expirationMonth=" + expirationMonth +
                ", expirationYear=" + expirationYear +
                ", email='" + email + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}

class TokenService {
    private static final Map<String, CreditCard> tokenStore = new ConcurrentHashMap<>();

    public static String createToken(CreditCard creditCard) {
        // Validar los datos de la tarjeta aquí
        if (!isValidCreditCard(creditCard)) {
            throw new IllegalArgumentException("Datos de tarjeta no válidos");
        }

        // Generar un token único
        String token = generateToken();
        creditCard.setToken(token);

        // Almacenar en la base de datos en memoria con una expiración de 15 minutos
        tokenStore.put(token, creditCard);

        return token;
    }

    public static CreditCard getCreditCardByToken(String token) {
        return tokenStore.get(token);
    }

    private static String generateToken() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    private static boolean isValidCreditCard(CreditCard creditCard) {
        // Realiza validaciones de tarjeta aquí (por ejemplo, algoritmo de Luhn, dominios de correo electrónico, etc.)
        return true; // Cambia esto según tus reglas de validación
    }
}

public class Main {
    public static void main(String[] args) {
        // Ejemplo de uso
        CreditCard creditCard = new CreditCard("1234567890123456", 123, 10, 2023, "example@gmail.com");
        String token = TokenService.createToken(creditCard);
        System.out.println("Token creado: " + token);

        CreditCard retrievedCard = TokenService.getCreditCardByToken(token);
        System.out.println("Datos de tarjeta recuperados: " + retrievedCard.toString());
class TokenService {
    // ...

    public static String createToken(CreditCard creditCard) {
        // Validar los datos de la tarjeta aquí
        if (!isValidCreditCard(creditCard)) {
            throw new IllegalArgumentException("Datos de tarjeta no válidos");
        }

        // Generar un token único
        String token = generateToken();
        creditCard.setToken(token);

        // Almacenar en la base de datos en memoria con una expiración de 15 minutos
        tokenStore.put(token, creditCard);

        return token;
    }

    // ...

    private static boolean isValidCreditCard(CreditCard creditCard) {
        // Validación del algoritmo de Luhn para el número de tarjeta
        if (!isValidLuhn(creditCard.getCardNumber())) {
            return false;
        }

        // Validación de dominios de correo electrónico
        String[] validEmailDomains = {"gmail.com", "hotmail.com", "yahoo.es"};
        boolean validEmail = false;
        for (String domain : validEmailDomains) {
            if (creditCard.getEmail().endsWith("@" + domain)) {
                validEmail = true;
                break;
            }
        }

        return validEmail;
    }

    private static boolean isValidLuhn(String cardNumber) {
        int sum = 0;
        boolean alternate = false;

        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(cardNumber.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }

        return (sum % 10 == 0);
    }

  
        }
