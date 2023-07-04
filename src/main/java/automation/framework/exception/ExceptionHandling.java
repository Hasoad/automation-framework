package automation.framework.exception;

import automation.framework.logs.Log;

public class ExceptionHandling {
    public static void writeException(String message){
        Log.error(message);
    }
}
