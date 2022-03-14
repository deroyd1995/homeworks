package com.t1.legendarium.chars;

public class AbstractCharacter {
   private boolean isAlive;
   private Integer health;


   public boolean getAlive(){
       return health>0;
   }

   public AbstractCharacter(){
       health=100;
   }

}
