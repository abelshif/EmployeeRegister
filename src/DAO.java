import java.util.ArrayList;
import java.util.List;

/**
 * Created by Salah Abdinoor
 * 12/7/2020
 * 4:13 PM
 * AOD2Employee
 * Copyright: MIT
 */
public class DAO {

    List<Employee> surgeryList = new ArrayList<>();
    Employee sur1 = new Doctor("Salah", "Abdinoor", "Man", "950510-1932", "Surgery", "072-0452176", 78588,
            "Doctor of Medicine", "Surgeon", false);

    Employee sur2 = new Doctor("Adam", "Nordström", "Man", "890510-8648", "Surgery", "076-7777890", 78588,
            "Bachelor of Surgery", "Surgeon", true);

    Employee sur3 = new Nurse("Peter", "Johansson", "Man", "690629-6576", "Surgery", "070-2447196", 38240,
            "Certified Ambulatory Surgery Nurse");

    List<Employee> anaestheticsList = new ArrayList<>();
    Employee ana1 = new Doctor("Sven", "Karlsson", "Man", "820920-6807", "Anaesthetics", "073-9757058", 78588,
            "Doctor of Medicine", "Anesthesiologist",false);

    Employee ana2 = new Nurse("Johan", "Nilsson", "Man", "621126-9136", "Anaesthetics", "073-5652021", 38240,
            "Certified Registered Nurse Anesthetist");

    Employee ana3 = new Nurse("David", "Eriksson", "Man", "671207-0868", "Anaesthetics", "072-2271560", 38240,
            "Certified Registered Nurse Anesthetist");

    List<Employee> cardiologyList = new ArrayList<>();
    Employee car1 = new Doctor("Astrid", "Lindberg", "Kvinna", "770319-9148", "Cardiology", "072-2271565", 78588,
            "Doctor of Medicine", "Cardiologist", false);

    Employee car2 = new Nurse("Olivia", "Olsson", "Kvinna", "960530-0715", "Cardiology", "072-2293320", 38240,
            "Certified Cardiac-Vascular Nurse");

    Employee car3 = new Nurse("Alice", "Wallin", "Kvinna", "860517-8170", "Cardiology", "070-8858887", 38240,
            "Certified Cardiac-Vascular Nurse");

    List<Employee> criticalCareList = new ArrayList<>();
    Employee cri1 = new Doctor("Maja", "Dahlqvist", "Kvinna", "720908-3414", "Critical care", "076-8802553", 78588,
            "Doctor of Medicine", "Emergency Medical Specialist", false);

    Employee cri2 = new Nurse("Vera", "Bergström", "Kvinna", "580215-1877", "Critical care", "073-0573673", 38240,
            "Certified Emergency Nurse");

    Employee cri3 = new Nurse("Ebba", "Elofsson", "Kvinna", "640701-8420", "Critical care", "072-2221527", 38240,
            "Certified Emergency Nurse");

    public DAO() {

        surgeryList.add(sur1);
        surgeryList.add(sur2);
        surgeryList.add(sur3);

        anaestheticsList.add(ana1);
        anaestheticsList.add(ana2);
        anaestheticsList.add(ana3);

        cardiologyList.add(car1);
        cardiologyList.add(car2);
        cardiologyList.add(car3);

        criticalCareList.add(cri1);
        criticalCareList.add(cri2);
        criticalCareList.add(cri3);
    }

    public List<Employee> getAnaestheticsList() {
        return anaestheticsList;
    }



    private void run() {





    }

    public static void main(String[] args) {
        new DAO().run();
    }

}