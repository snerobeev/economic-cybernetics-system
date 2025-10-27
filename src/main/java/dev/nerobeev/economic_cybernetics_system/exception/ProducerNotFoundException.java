package dev.nerobeev.economic_cybernetics_system.exception;

public class ProducerNotFoundException extends RuntimeException {
    public ProducerNotFoundException(Long id) {

        super("Producer with ID " + id + " not found");
    }
}
