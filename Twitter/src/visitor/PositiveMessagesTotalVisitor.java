/***************************************************************
* file: PositiveMessagesTotalVisitor.java
* author: Colin Trotter
* class: CS 356 – Object-Oriented Design and Programming
*
* assignment: Assignment 2 - Twitter
* date last modified: 11/7/2016
*
* purpose: Visitor implementation to count the total number of positive messages posted by Users in a UserElementTreeModel starting from a specified
* parent node, and working down into it's children. Positive messages include 'positive' words such as "good", "great", and "wonderful".
****************************************************************/ 
package visitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import twitter.Group;
import twitter.User;

public class PositiveMessagesTotalVisitor implements Visitor {
    
    public int total; //The total number of positive messages counted.
    
    //Words which classify a 'positive' tweet. Tweets containing these words will be counted.
    private final List<String> positiveWords = Arrays.asList("good", "great", "excellent", "dope", "bodacious", "sublime", "happy", "amazing", "wonderful");

    /*
    * FUNCTION: atUser()
    * 
    * Counts the number of positive tweets posted by this User, and posts it's uniqueID to the console.
    */
    @Override
    public void atUser(User e) {
        System.out.println("Counted positive messages from User: " + e.getUniqueID());
        countPositiveTweets(e.getTweets());
    } 

    
    /*
    * FUNCTION: atGroup()
    * 
    * Posts this Group's uniqueID to the console.
    */
    @Override
    public void atGroup(Group e) {
        System.out.println("Counting positive messages of children of Group: " + e.getUniqueID());
    }
    
    /*
    * FUNCTION: countPositiveTweets()
    * 
    * Iterates through the tweets by this User. If the tweet contains a positive word, count this tweet.
    */
    private void countPositiveTweets(ArrayList<String> tweets){
        for (String tweet : tweets){
            if (containsPositiveWord(tweet)){
                total++;
            }
        }
    }
    
    /*
    * FUNCTION: containsPositiveWord()
    * 
    * Returns true if this tweet contains a positive word.
    */
    private boolean containsPositiveWord(String tweet){
        String lowercase = tweet.toLowerCase();   
        for (String word : lowercase.split(" ")){
            if (positiveWords.contains(word)){
                return true;
            }
        }  
        return false;
    }
    
}
