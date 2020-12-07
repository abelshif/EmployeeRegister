package LoginSystem;

import java.util.Random;

public class Nurse extends Employee {

    public Nurse(String firstName, String lastName, String gender, String birthDate, double salary, String department) {
        super(firstName, lastName, gender, birthDate, salary, department);
    }
    public Nurse(){

    }

    //different implementation of generating id
    @Override
    public String generateId() {
        Random rand=new Random();
        int random=100+rand.nextInt(899);
        return "NRS"+"_"+random;
    }
}

