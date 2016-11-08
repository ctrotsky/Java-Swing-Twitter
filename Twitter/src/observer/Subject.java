/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package observer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Colin
 */
public abstract class Subject {
    
    private List<Observer> observers = new ArrayList();
    
    public void attach(Observer observer) {
        observers.add(observer);
    }
    
    public void detach(Observer observer) {
        observers.remove(observer);
    }
    
    protected void notifyObservers(){
        for (Observer observer : observers){
            observer.update(this);
        }
    }
    
    protected int getObserverCount() {
        return observers.size();
    }
    
}
