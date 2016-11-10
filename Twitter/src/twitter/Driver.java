/***************************************************************
* file: Driver.java
* author: Colin Trotter
* class: CS 356 – Object-Oriented Design and Programming
*
* assignment: Assignment 2 - Twitter
* date last modified: 11/7/2016
*
* purpose: Main class to be used to test the Twitter program. Simply calls the init() method of @AdminPanel.
*
****************************************************************/ 
package twitter;

public class Driver {
    public static void main(String[] args) {
        AdminPanel.getInstance().init();
    }
}
