package com.app.messageapplication.Service;


public class ConversationIdService {

    public static String getId(String person1, String person2){
        return person1.compareTo(person2)<0 ? person1+"-"+person2 : person2+"-"+person1;
    }

}
