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
    private String role;

    public Nurse(String firstName, String lastName, String gender, String birthDate, String department, String phoneNumber, double salary) {
        super(firstName, lastName, gender, birthDate, department, phoneNumber, salary);
    }

    public String getCertifications() {
        return certifications;
    }

    public void setCertifications(String certifications) {
        this.certifications = certifications;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public void generateID() {

    }

}
