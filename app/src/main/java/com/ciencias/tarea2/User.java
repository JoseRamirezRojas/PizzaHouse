package com.ciencias.tarea2;


// Modelo para la base de datos con persistencia
public class User {

    private int id;
    private String nombre;
    private String password;
    private long tarjeta;
    private int genero; // 0 men, 1 women, 2 other 

    

    public User(){ }

    public User(String nombre, long tarjeta, int genero) {
        this.nombre = nombre;
        this.tarjeta = tarjeta;
        this.genero = genero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(long tarjeta) {
        this.tarjeta = tarjeta;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }
}
