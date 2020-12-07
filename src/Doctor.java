import javax.print.Doc;

/**
 * Created by Axel Jeansson
 * Date: 2020-11-30
 * Time: 14:57
 * Project: AOD2Employee
 * Copyright: MIT
 */
public class Doctor extends Employee implements GenerateID{
    private String degree;
    private String specializtion;

    public Doctor(String firstName, String lastName, String gender, String birthDate, String department, String phoneNumber, double salary, String degree, String specializtion) {
        super(firstName, lastName, gender, birthDate, department, phoneNumber, salary);
        this.degree = degree;
        this.specializtion = specializtion;
    }

    public String getDegree() {
        return degree;
    }

    public String getSpecializtion() {
        return specializtion;
    }

    public void setSpecializtion(String specializtion) {
        this.specializtion = specializtion;
    }


    @Override
    public void generateID() {

    }

}
