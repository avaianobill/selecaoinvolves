package br.com.involves.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Entidade não encontrada por id.")
public class EntityNotFoundException extends Exception
{
    private static final long serialVersionUID = -3387516993334229948L;

    public EntityNotFoundException(String message)
    {
        super(message);
    }

}
