package java_advanced_training.bakery;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Bakery {
    private String name;
    private int capacity;
    private List<Employee> employees;

    public Bakery(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.employees = new ArrayList<>();
    }
    public void add(Employee employee) {
        if (this.employees.size() < this.capacity){
            this.employees.add(employee);
        }
    }
    public boolean remove(String name){
        int size = employees.size();
        employees.removeIf(employee -> employee.getName().equals(name));
        return size != employees.size();
    }

    public Employee getOldestEmployee(){
        return this.employees.stream().max(Comparator.comparingInt(Employee::getAge)).get();
    }
    public Employee getEmployee(String name){
      return employees.stream().filter(employee -> employee.getName().equals(name)).findAny().get();
    }
    public int getCount(){
        return employees.size();
    }

    public String report(){
        StringBuilder builder = new StringBuilder();
        builder.append("Employees working at Bakery ").append(this.name).append(":").append(System.lineSeparator());

        employees.stream().forEach(employee -> builder.append(employee).append(System.lineSeparator()));
    return builder.toString();

    }

}
