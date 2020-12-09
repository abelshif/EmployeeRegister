import java.awt.*;

/**
 * Created by Axel Jeansson
 * Date: 2020-11-30
 * Time: 14:57
 * Project: AOD2Employee
 * Copyright: MIT
 */
public class Nurse extends Employee implements GenerateID {
    private String certifications;


    public Nurse(String firstName, String lastName, String gender, String birthDate, String department, String phoneNumber, double salary, String role, String certifications) {
        super(firstName, lastName, gender, birthDate, department, phoneNumber, salary, role);
        this.certifications = certifications;
    }


    public String getCertifications() {
        return certifications;
    }

    public void setCertifications(String certifications) {
        this.certifications = certifications;
    }

    @Override
    public void generateID() {

    }

}
