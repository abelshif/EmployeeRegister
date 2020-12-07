import java.awt.*;

/**
 * Created by Axel Jeansson
 * Date: 2020-11-30
 * Time: 14:57
 * Project: AOD2Employee
 * Copyright: MIT
 */
public class Nurse extends Employee implements GenerateID {
    private String certification;

    public Nurse(String firstName, String lastName, String gender, String birthDate, String department, String phoneNumber, double salary, String certification) {
        super(firstName, lastName, gender, birthDate, department, phoneNumber, salary);
        this.certification = certification;
    }

    public String getCertifications() {
        return certification;
    }

    public void setCertifications(String certifications) {
        this.certification = certification;
    }


    @Override
    public void generateID() {

    }

}
