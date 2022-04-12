package com.child.box.beautycode.observer;

public interface Observerable {
    void registerObserver(Observer o);

    void removeObserver(Observer o);

    void notifyObserver();
}
