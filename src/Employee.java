import javax.swing.*;
import java.awt.*;

/**
 * Created by Axel Jeansson
 * Date: 2020-11-30
 * Time: 14:57
 * Project: AOD2Employee
 * Copyright: MIT
 */
public class Employee implements GenerateID {
    private String firstName;
    private String lastName;
    private String gender;
    private String birthDate;
    private double salary;
    private String department;
    //private Department department?;
    private String phoneNumber;

    public Employee(){}

    public Employee(String firstName, String lastName, String gender, String birthDate, String department, String phoneNumber, double salary){
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.department = department;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }


    public String getBirthDate() {
        return birthDate;
    }


    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    @Override
    public void generateID() {

    }
}
