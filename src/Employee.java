import java.util.Random;

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
    private String salary;
    private String department;
    private String phoneNumber;
    private String role;
    private String info;
    private String userID;


    public Employee(){};

    public Employee(String firstName, String lastName, String gender, String birthDate, String department, String phoneNumber, String salary, String specialization, String userID){
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

    public String getGender() {
        return gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getSalary() {
        return salary;
    }

    public String getDepartment() {
        return department;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public String getUserID(){
        return userID;
    }


    public String writeInfo(){
        info = getFirstName()+","+getLastName()+","+getGender()+","+getBirthDate()+","+getDepartment()+","+getPhoneNumber()+","+getSalary()+","+ getRole()+","+getUserID();
        return info;
    }

    @Override
    public String generateID(String firstName) {

        Random rand = new Random();
        int random = 100 + rand.nextInt(899);
        return firstName.toUpperCase() + "_" + random;

    }
}
