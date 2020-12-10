import java.util.List;

/**
 * Created by Salah Abdinoor
 * 11/30/2020
 * 3:01 PM
 * AOD2Employee
 * Copyright: MIT
 */
public class Department {

    private String name;
    private int numberOfEmployees;
    private String location;





    public Department(){

    }

    public Department(String name, int numberOfEmployees, String location) {
        this.name = name;
        this.numberOfEmployees = numberOfEmployees;
        this.location = location;
    }


    public String getName() {
        return name;
    }

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public String getLocation() {
        return location;
    }


}
