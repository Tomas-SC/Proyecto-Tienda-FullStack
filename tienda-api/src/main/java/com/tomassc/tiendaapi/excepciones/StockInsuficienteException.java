package com.tomassc.tiendaapi.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//  esta excepción llega hasta el controlador,
// la API devuelva automáticamente un error 400 (Bad Request) (creo)
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class StockInsuficienteException extends Exception {

    // Este constructor permite crear la excepción con un mensaje específico.
    public StockInsuficienteException(String message) {
        // Llama al constructor de la clase padre "Exception" para guardar el mensaje.
        super(message);
    }
}