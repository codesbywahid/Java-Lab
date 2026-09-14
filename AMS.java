//This project is based on an Academy Management System developed using Java Swing GUI.
//It allows users to register students and teachers and display their information through a graphical interface.
//The project demonstrates core Object-Oriented Programming concepts such as inheritance, polymorphism, abstraction, and interfaces.
//The interface is simple, organized, and user-friendly for easy interaction.
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*; 
interface Manageable {
    void register();
    void showRole();
}
class Course {
    private String title;
    private double fee;
    public Course(String title, double fee) {
        this.title = title;
        this.fee = fee;
    }
    public String getTitle() { return title; }
    public double getFee() { return fee; }
}
abstract class Person implements Manageable, Serializable {
    private String name;
    private int age;
    private String phone;
    private String email;
    public void setName(String n) { name = n; }
    public void setAge(int a) { age = a; }
    public void setContact(String p, String e) { phone = p; email = e; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public abstract String displayInfo();
}
class Student extends Person {
    private String id;
    private String course;
    public void setId(String i) { id = i; }
    public void setCourse(String c) { course = c; }
    public String getId() { return id; }
    public String getCourse() { return course; }
    public void register() {}
    public void showRole() {}
    public String displayInfo() {
        return "Role: Student\n" +
               "Name: " + getName() + "\n" +
               "Age: " + getAge() + "\n" +
               "ID: " + id + "\n" +
               "Course: " + course + "\n" +
               "Phone: " + getPhone() + "\n" +
               "Email: " + getEmail() + "\n";
    }}
class Teacher extends Person {
    private String subject;
    private double salary;
    private String courseTeaching;
    private String timing;
    public void setSubject(String s) { subject = s; }
    public void setSalary(double s) { salary = s; }
    public void setCourseTeaching(String c) { courseTeaching = c; }
    public void setTiming(String t) { timing = t; }
    public String getSubject() { return subject; }
    public double getSalary() { return salary; }
    public String getCourseTeaching() { return courseTeaching; }
    public String getTiming() { return timing; }
    public void register() {}
    public void showRole() {}
    public String displayInfo() {
        return "Role: Teacher\n" +
               "Name: " + getName() + "\n" +
               "Age: " + getAge() + "\n" +
               "Subject: " + subject + "\n" +
               "Salary: " + salary + "\n" +
               "Course: " + courseTeaching + "\n" +
               "Timing: " + timing + "\n" +
               "Phone: " + getPhone() + "\n" +
               "Email: " + getEmail() + "\n";
    }}
class Academy {
    private String name = "The City Light Academy";
    private String location = "Attock City";
    public String getInfo() {
        return "Academy: " + name + "\nLocation: " + location + "\n\n";
    }}
public class AMS extends JFrame {
    private JTextArea output;
    private ArrayList<Student> studentList = new ArrayList<>();
    private ArrayList<Teacher> teacherList = new ArrayList<>();
    private Course[] courses = {
        new Course("Java", 15000),
        new Course("Web Development", 18000),
        new Course("Graphic Design", 12000),
        new Course("Cyber Security", 20000)
    };
    private final String studentFile = "students.dat";
    private final String teacherFile = "teachers.dat";
    public AMS() {
        loadData();
        setTitle("Academy Management System");
        setSize(600, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        Academy academy = new Academy();
        JLabel heading = new JLabel("The City Light Academy", JLabel.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 22));
        add(heading, BorderLayout.NORTH);
        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Student", studentPanel(academy));
        tabs.add("Teacher", teacherPanel(academy));
        add(tabs, BorderLayout.CENTER);
        output = new JTextArea(10, 50);
        output.setEditable(false);
        add(new JScrollPane(output), BorderLayout.SOUTH);
    }
    private JPanel studentPanel(Academy academy) {
        JPanel p = new JPanel(new GridLayout(10, 2, 5, 5));
        JTextField name = new JTextField();
        JTextField age = new JTextField();
        JTextField id = new JTextField();
        JTextField phone = new JTextField();
        JTextField email = new JTextField();
        JComboBox<String> courseBox = new JComboBox<>();
        for (Course c : courses) courseBox.addItem(c.getTitle());
        JButton registerBtn = new JButton("Register Student");
        JButton deleteBtn = new JButton("Delete Student");
        JButton displayBtn = new JButton("Display All Students");
        p.add(new JLabel("Name")); p.add(name);
        p.add(new JLabel("Age")); p.add(age);
        p.add(new JLabel("Student ID")); p.add(id);
        p.add(new JLabel("Phone")); p.add(phone);
        p.add(new JLabel("Email")); p.add(email);
        p.add(new JLabel("Course")); p.add(courseBox);
        p.add(registerBtn); p.add(deleteBtn);
        p.add(displayBtn);
        registerBtn.addActionListener(e -> {
            try {
                Student s = new Student();
                s.setName(name.getText());
                s.setAge(Integer.parseInt(age.getText()));
                s.setId(id.getText());
                s.setCourse(courseBox.getSelectedItem().toString());
                s.setContact(phone.getText(), email.getText());
                studentList.add(s);
                saveData();
                output.setText("Student Registered!\n\n" + displayStudents());
            } catch (Exception ex) {
                output.setText("Invalid input! Please check the data.");
            }
        });
        deleteBtn.addActionListener(e -> {
            String studentId = id.getText();
            boolean removed = studentList.removeIf(s -> s.getId().equals(studentId));
            saveData();
            output.setText(removed ? "Student Deleted!\n\n" + displayStudents()
                                   : "Student ID not found!");
        });
        displayBtn.addActionListener(e -> output.setText(displayStudents()));
        return p;
    }
    private String displayStudents() {
        if (studentList.isEmpty()) return "No students registered.";
        StringBuilder sb = new StringBuilder();
        for (Student s : studentList) sb.append(s.displayInfo()).append("\n");
        return sb.toString();
    }
    private JPanel teacherPanel(Academy academy) {
        JPanel p = new JPanel(new GridLayout(11, 2, 5, 5));
        JTextField name = new JTextField();
        JTextField age = new JTextField();
        JTextField subject = new JTextField();
        JTextField salary = new JTextField();
        JTextField phone = new JTextField();
        JTextField email = new JTextField();
        JComboBox<String> courseBox = new JComboBox<>();
        for (Course c : courses) courseBox.addItem(c.getTitle());
        JComboBox<String> timing = new JComboBox<>(new String[]{"Morning", "Evening"});
        JButton registerBtn = new JButton("Register Teacher");
        JButton deleteBtn = new JButton("Delete Teacher");
        JButton displayBtn = new JButton("Display All Teachers");
        p.add(new JLabel("Name")); p.add(name);
        p.add(new JLabel("Age")); p.add(age);
        p.add(new JLabel("Subject")); p.add(subject);
        p.add(new JLabel("Salary")); p.add(salary);
        p.add(new JLabel("Phone")); p.add(phone);
        p.add(new JLabel("Email")); p.add(email);
        p.add(new JLabel("Course")); p.add(courseBox);
        p.add(new JLabel("Timing")); p.add(timing);
        p.add(registerBtn); p.add(deleteBtn);
        p.add(displayBtn);
        registerBtn.addActionListener(e -> {
            try {
                Teacher t = new Teacher();
                t.setName(name.getText());
                t.setAge(Integer.parseInt(age.getText()));
                t.setSubject(subject.getText());
                t.setSalary(Double.parseDouble(salary.getText()));
                t.setCourseTeaching(courseBox.getSelectedItem().toString());
                t.setTiming(timing.getSelectedItem().toString());
                t.setContact(phone.getText(), email.getText());
                teacherList.add(t);
                saveData();
                output.setText("Teacher Registered!\n\n" + displayTeachers());
            } catch (Exception ex) {
                output.setText("Invalid input. Please check the data.");
            }
        });
        deleteBtn.addActionListener(e -> {
            String teacherName = name.getText();
            boolean removed = teacherList.removeIf(t -> t.getName().equalsIgnoreCase(teacherName));
            saveData();
            output.setText(removed ? "Teacher Deleted!\n\n" + displayTeachers()
                                   : "Teacher name not found!");
        });
        displayBtn.addActionListener(e -> output.setText(displayTeachers()));
        return p;
    }
    private String displayTeachers() {
        if (teacherList.isEmpty()) return "No teachers registered.";
        StringBuilder sb = new StringBuilder();
        for (Teacher t : teacherList) sb.append(t.displayInfo()).append("\n");
        return sb.toString();
    }
    private void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(studentFile))) {
            studentList = (ArrayList<Student>) ois.readObject();
        } catch (Exception e) {
            studentList = new ArrayList<>();}
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(teacherFile))) {
            teacherList = (ArrayList<Teacher>) ois.readObject();
        } catch (Exception e) {
            teacherList = new ArrayList<>();
        }}
    private void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(studentFile))) {
            oos.writeObject(studentList);
        } catch (IOException e) { e.printStackTrace(); }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(teacherFile))) {
            oos.writeObject(teacherList);
        } catch (IOException e) { e.printStackTrace(); }
}
    public static void main(String[] args) {
        new AMS().setVisible(true);
    }
}