package LoginSystem;

import java.util.Random;

public class Doctor extends Employee {

    public Doctor(String firstName, String lastName, String gender, String birthDate, double salary, String department) {
        super(firstName, lastName, gender, birthDate, salary, department);
    }

    public Doctor() {
        super();
    }

    //different implementation of generating id
    @Override
    public String generateId() {
        Random rand = new Random();
        int random = 100 + rand.nextInt(899);
        return "DOC" + "_" + random;
    }

}

