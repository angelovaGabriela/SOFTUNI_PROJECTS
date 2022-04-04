package singleInheritance;

public class Dog extends Animal {
    //когато не пишем конструктор се използва дефоутен (без параметри)
    public void bark(){
        System.out.println("barking...");
    }
}
