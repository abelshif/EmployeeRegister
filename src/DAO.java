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



    List<Employee>  surgeryList = new ArrayList<>();
    Employee sur1 = new Employee("Salah", "Abdinoor", "Man", "950510-1932", "Surgery", "072-0452176", 78588, "Doctor");

    Employee sur2 = new Employee("Adam", "Nordström", "Man", "890510-8648", "Surgery", "076-7777890", 78588, "Doctor");

    Employee sur3 = new Employee("Peter", "Johansson", "Man", "690629-6576", "Surgery", "070-2447196", 38240, "Nurse");

    List<Employee> anaestheticsList = new ArrayList<>();
    Employee ana1 = new Employee("Sven", "Karlsson", "Man", "820920-6807", "Anaesthetics", "073-9757058", 78588, "Doctor");

    Employee ana2 = new Employee("Johan", "Nilsson", "Man", "621126-9136", "Anaesthetics", "073-5652021", 38240, "Nurse");

    Employee ana3 = new Employee("David", "Eriksson", "Man", "671207-0868", "Anaesthetics", "072-2271560", 38240, "Nurse");

    List<Employee> cardiologyList = new ArrayList<>();
    Employee car1 = new Employee("Astrid", "Lindberg", "Kvinna", "770319-9148", "Cardiology", "072-2271565", 78588, "Doctor");

    Employee car2 = new Employee("Olivia", "Olsson", "Kvinna", "960530-0715", "Cardiology", "072-2293320", 38240, "Nurse");

    Employee car3 = new Employee("Alice", "Wallin", "Kvinna", "860517-8170", "Cardiology", "070-8858887", 38240, "Nurse");

    List<Employee> criticalCareList = new ArrayList<>();
    Employee cri1 = new Employee("Maja", "Dahlqvist", "Kvinna", "720908-3414", "Critical care", "076-8802553", 78588, "Doctor");

    Employee cri2 = new Employee("Vera", "Bergström", "Kvinna", "580215-1877", "Critical care", "073-0573673", 38240, "Nurse");

    Employee cri3 = new Employee("Ebba", "Elofsson", "Kvinna", "640701-8420", "Critical care", "072-2221527", 38240, "Nurse");

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