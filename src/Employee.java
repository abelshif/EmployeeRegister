import java.util.Random;

/**
 * Created by Axel Jeansson
 * Date: 2020-11-30
 * Time: 14:57
 * Project: AOD2Employee
 * Copyright: MIT
 */
public class Employee implements GenerateID, PrintInfo {
    private String firstName;
    private String lastName;
    private String gender;
    private String birthDate;
    private double salary;
    private String department;
    private String phoneNumber;
    private String role;
    private String info;
    private String userID;



    public Employee(String firstName, String lastName, String gender, String birthDate, String department, String phoneNumber, double salary, String specialization){
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.department = department;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.role = specialization;
    }

    public Employee(String firstName, String lastName, String gender, String birthDate, String department, String phoneNumber, double salary, String specialization, String userID){
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.department = department;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.role = specialization;
        this.userID = userID;
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

    public String getRole() {
        return role;
    }

    public void setUserID(String userID){
        this.userID = this.userID;
    }
    public String getUserID(){
        return userID;
    }


    @Override
    public void printEmployeeInfo() {
        System.out.println("Namn: "+getFirstName()+" "+getLastName()+
                "\nKön: "+getGender()+
                "\nFödelsedag: "+getBirthDate()+
                "\nAvdelning: "+getDepartment()+
                "\nTelefonnummer: "+getPhoneNumber()+
                "\nLön: "+getSalary()+
                "\nÖvrigt: "+ getRole());
    }

    public String writeInfo(){
        info = getFirstName()+","+getLastName()+","+getGender()+","+getBirthDate()+","+getDepartment()+","+getPhoneNumber()+","+getSalary()+","+ getRole()+","+getUserID();
        return info;
    }

    @Override
    public String generateID() {

        Random rand = new Random();
        int random = 100 + rand.nextInt(899);
        return getFirstName().toUpperCase() + "_" + random;

    }
}
