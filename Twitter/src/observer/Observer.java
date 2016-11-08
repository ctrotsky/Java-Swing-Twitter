/***************************************************************
* file: Observer.java
* author: Colin Trotter
* class: CS 356 – Object-Oriented Design and Programming
*
* assignment: Assignment 2 - Twitter
* date last modified: 11/7/2016
*
* purpose: Interface to be used with the Observer design pattern. Implementations require a method which updates
* their subjects.
*
****************************************************************/ 
package observer;

public interface Observer {
    
    /*
    * FUNCTION: update()
    * 
    * Will update this Observer about the Subject.
    */
    public void update(Subject subject);
}