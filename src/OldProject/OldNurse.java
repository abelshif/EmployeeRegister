package OldProject;

import java.util.Random;

public class OldNurse extends OldEmployee {

    public OldNurse(String firstName, String lastName, String gender, String birthDate, double salary, String department) {
        super(firstName, lastName, gender, birthDate, salary, department);
    }
    public OldNurse(){

    }

    //different implementation of generating id
    @Override
    public String generateId() {
        Random rand=new Random();
        int random=100+rand.nextInt(899);
        return "NRS"+"_"+random;
    }
}

