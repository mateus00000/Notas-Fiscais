package com.example.Notas.util;

public abstract  class ValidaEmail {
    public static boolean validarCaracterArroba(String input){
        return input != null && input.contains("@");
    }

    public static boolean isValid(String email){
        if (email == null) {
            return false;
        }

        //Expressão regular basica para validar o email.
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
}
