/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import twitter.Group;
import twitter.User;

/**
 *
 * @author Colin
 */
public class PositiveMessagesTotalVisitor implements Visitor {
    
    public int total;
    
    private final List<String> positiveWords = Arrays.asList("good");

    @Override
    public void atUser(User e) {
        System.out.println("Counted positive messages from User: " + e.getUniqueID());
        countTweets(e.getTweets());
    } 

    @Override
    public void atGroup(Group e) {
        System.out.println("Counting positive messages of children of Group: " + e.getUniqueID());
    }
    
    private void countTweets(ArrayList<String> tweets){
        for (String tweet : tweets){
            if (containsPositiveWord(tweet)){
                total++;
            }
        }
    }
    
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
