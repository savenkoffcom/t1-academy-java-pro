package com.savenkoff.study.task2;

public class Employee implements Comparable<Employee> {

    String name;
    Integer age;
    Enum<EmployeePosition> position;

    public Employee(String name, Integer age, Enum<EmployeePosition> position) {
        this.name = name;
        this.age = age;
        this.position = position;
    }

    @Override
    public int compareTo(Employee o) {
        return o.age - this.age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", position=" + position +
                '}';
    }
}
