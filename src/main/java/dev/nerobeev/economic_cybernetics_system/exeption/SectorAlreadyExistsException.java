package dev.nerobeev.economic_cybernetics_system.exeption;

public class SectorAlreadyExistsException extends RuntimeException{
    public SectorAlreadyExistsException(String code){
        super("Сектор с кодом '" + code + "' уже существует");
    }
}
