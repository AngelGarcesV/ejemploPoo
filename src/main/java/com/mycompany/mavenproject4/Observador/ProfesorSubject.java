package com.mycompany.mavenproject4.Observador;

import java.util.ArrayList;
import java.util.List;

public class ProfesorSubject {
    private static List<ProfesorObserver> observers = new ArrayList<>();


    public static void agregarObserver(ProfesorObserver observer) {
        observers.add(observer);
    }


    public static void quitarObserver(ProfesorObserver observer) {
        observers.remove(observer);
    }


    public static void notificarObservers() {
        for (ProfesorObserver observer : observers) {
            observer.updateProfesores();
        }
    }
}
