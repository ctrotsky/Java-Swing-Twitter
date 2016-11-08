/***************************************************************
* file: Subject.java
* author: Colin Trotter
* class: CS 356 – Object-Oriented Design and Programming
*
* assignment: Assignment 2 - Twitter
* date last modified: 11/7/2016
*
* purpose: Abstract class to be used with the Observer design pattern. Implementations allow their instances to 
* attach and detatch observers, as well notify their observers by calling their update method.
*
****************************************************************/ 
package observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    
    private List<Observer> observers = new ArrayList(); //Contains observers that will receive updates about this Subject.
    
    /*
    * FUNCTION: attach()
    * 
    * Will attach the given observer to this Subject. This observer will receive updates from this Subject.
    */
    public void attach(Observer observer) {
        observers.add(observer);
    }
    
    /*
    * FUNCTION: detach()
    * 
    * Will detach the given observer from this Subject. This observer will no longer receive updates from this Subject.
    */
    public void detach(Observer observer) {
        observers.remove(observer);
    }
    
    /*
    * FUNCTION: attach()
    * 
    * Will call update on every observer of this Subject. Passes itself to the observer to receive information from this Subject.
    */
    protected void notifyObservers(){
        for (Observer observer : observers){
            observer.update(this);
        }
    }
    
    /*
    * FUNCTION: getObserverCount()
    * 
    * Returns the number of observers attached to this Subject.
    */
    protected int getObserverCount() {
        return observers.size();
    }
    
}
