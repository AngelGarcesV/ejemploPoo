package com.mycompany.mavenproject4.Observador;

import java.util.ArrayList;
import java.util.List;

public class InscripcionSubject {
    private static List<InscripcionObserver> observers = new ArrayList<>();


    public static void agregarObserver(InscripcionObserver observer) {
        observers.add(observer);
    }


    public static void quitarObserver(InscripcionObserver observer) {
        observers.remove(observer);
    }


    public static void notificarObservers() {
        for (InscripcionObserver observer : observers) {
            observer.updateInscripciones();
        }
    }
}
