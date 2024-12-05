package ru.fed1v.NauJava.util;

/**
 * Вспомогательный класс для отображения ошибок на странице
 */
public class Exception {

    private String message;
    
    private Exception(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public static Exception create(Throwable e) {
        return new Exception(e.getMessage());
    }
    
    public static Exception create(String message) {
        return new Exception(message);
    }
}
