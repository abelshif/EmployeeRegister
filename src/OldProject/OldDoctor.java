package OldProject;

import java.util.Random;

public class OldDoctor extends OldEmployee {

    public OldDoctor(String firstName, String lastName, String gender, String birthDate, double salary, String department) {
        super(firstName, lastName, gender, birthDate, salary, department);
    }

    public OldDoctor() {
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

