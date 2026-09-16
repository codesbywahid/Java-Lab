import java.util.ArrayList;
import java.util.Scanner;
abstract class Person {
    private int id;
    private String name;
    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public int getId() { return id; }
    public String getName() { return name; }
    public abstract void display();
}
class Patient extends Person {
    private int age;
    public Patient(int id, String name, int age) {
        super(id, name);
        setAge(age);
    }
    public int getAge() { return age; }
    public void setAge(int age) {
        if (age > 0) this.age = age;
        else throw new IllegalArgumentException("Age must be greater than 0");
    }
    public void display() {
        System.out.println("Patient -> ID: " + getId() + ", Name: " + getName() + ", Age: " + age);
    }
}
class Doctor extends Person {
    private String specialization;
    public Doctor(int id, String name, String specialization) {
        super(id, name);
        this.specialization = specialization;
    }
    public String getSpecialization() { return specialization; }
    public void display() {
        System.out.println("Doctor -> ID: " + getId() + ", Name: " + getName() + ", Specialization: " + specialization);
    }
}
class Appointment {
    private Patient patient;
    private Doctor doctor;
    private String dateTime;
    public Appointment(Patient patient, Doctor doctor, String dateTime) {
        this.patient = patient;
        this.doctor = doctor;
        this.dateTime = dateTime;
    }
    public void displayDetails() {
        System.out.println("\n--- Appointment Details ---");
        patient.display();
        doctor.display();
        System.out.println("Scheduled Date & Time: " + dateTime);
        System.out.println("--------------------------");
    }
}
public class Hospital {
    private static ArrayList<Patient> patients = new ArrayList<>();
    private static ArrayList<Doctor> doctors = new ArrayList<>();
    private static ArrayList<Appointment> appointments = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        doctors.add(new Doctor(1, "Dr. Waseem", "Physician"));
        doctors.add(new Doctor(2, "Dr. Ali", "General"));
        doctors.add(new Doctor(3, "Dr. Armaghan", "Specialist"));
        boolean running = true;
        while (running) {
            System.out.println("\n--- Hospital Management System ---");
            System.out.println("1. Add Patient");
            System.out.println("2. Add Doctor");
            System.out.println("3. Schedule Appointment");
            System.out.println("4. Display All Appointments");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); 
            switch (choice) {
                case 1 -> addPatient();
                case 2 -> addDoctor();
                case 3 -> scheduleAppointment();
                case 4 -> displayAppointments();
                case 5 -> {
                    running = false;
                    System.out.println("Exiting system.");
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
        sc.close();
    }
    private static void addPatient() {
        System.out.print("Enter Patient Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        sc.nextLine();
        int id = patients.size() + 1;
        patients.add(new Patient(id, name, age));
        System.out.println("Patient added successfully with ID " + id);
    }
    private static void addDoctor() {
        System.out.print("Enter Doctor Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Specialization: ");
        String specialization = sc.nextLine();
        int id = doctors.size() + 1;
        doctors.add(new Doctor(id, name, specialization));
        System.out.println("Doctor added successfully with ID " + id);
    }
    private static void scheduleAppointment() {
        if (patients.isEmpty()) {
            System.out.println("Add patients first.");
            return;
        }
        System.out.println("Select Patient by ID:");
        for (Patient p : patients) p.display();
        int pid = sc.nextInt();
        sc.nextLine();
        Patient selectedPatient = patients.stream().filter(p -> p.getId() == pid).findFirst().orElse(null);
        if (selectedPatient == null) {
            System.out.println("Invalid Patient ID.");
            return;
        }
        System.out.println("Select Doctor by ID:");
        for (Doctor d : doctors) d.display();
        int did = sc.nextInt();
        sc.nextLine();
        Doctor selectedDoctor = doctors.stream().filter(d -> d.getId() == did).findFirst().orElse(null);
        if (selectedDoctor == null) {
            System.out.println("Invalid Doctor ID.");
            return;
        }
        System.out.print("Enter Appointment Date & Time (e.g., 2025-12-20 10:00 AM): ");
        String dateTime = sc.nextLine();
        appointments.add(new Appointment(selectedPatient, selectedDoctor, dateTime));
        System.out.println("Appointment scheduled successfully!");
    }
    private static void displayAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments scheduled.");
            return;
        }
        for (Appointment app : appointments) app.displayDetails();
    }
}