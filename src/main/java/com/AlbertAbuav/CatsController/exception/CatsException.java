package com.AlbertAbuav.CatsController.exception;

public class CatsException extends Exception{

    private static int COUNT = 1;

    public CatsException(String message) {
        super("This is a cat exception number: " + COUNT++ + "\n" + message);
    }

}
