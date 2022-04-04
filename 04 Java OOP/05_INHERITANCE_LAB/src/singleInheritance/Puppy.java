package singleInheritance;

public class Puppy extends Dog { // винаги трябва да наследява DOG, защото иначе няма да получи bark method
    public void weep(){
        System.out.println("weeping...");
    }
}
