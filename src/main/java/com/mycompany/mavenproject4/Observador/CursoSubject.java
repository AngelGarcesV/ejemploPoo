package com.mycompany.mavenproject4.Observador;

import java.util.ArrayList;
import java.util.List;

public class CursoSubject {
    private static List<CursoObserver> observers = new ArrayList<>();


    public static void agregarObserver(CursoObserver observer) {
        observers.add(observer);
    }


    public static void quitarObserver(CursoObserver observer) {
        observers.remove(observer);
    }


    public static void notificarObservers() {
        for (CursoObserver observer : observers) {
            observer.updateCursos();
        }
    }
}
