class PatientNode {
    String id;
    String name;
    int priority;
    PatientNode next;
    PatientNode(String id, String name, int priority) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.next = null;
    }

    public String toString() {
        return "[" + id + ", " + name + ", " + priority + "]";
    }
}

class WaitingList {
    private PatientNode head = null;
    private void insertNode(PatientNode node) {
        if (head == null || node.priority < head.priority) {
            node.next = head;
            head = node;
            return;
        }
        PatientNode curr = head;
        while (curr.next != null && curr.next.priority <= node.priority) {
            curr = curr.next;
        }
        node.next = curr.next;
        curr.next = node;
    }
    private PatientNode detachNode(String id) {
        if (head == null) return null;
        if (head.id.equals(id)) {
            PatientNode removed = head;
            head = head.next;
            removed.next = null;
            return removed;
        }
        PatientNode curr = head;
        while (curr.next != null && !curr.next.id.equals(id)) {
            curr = curr.next;
        }
        if (curr.next == null) return null;
        PatientNode removed = curr.next;
        curr.next = removed.next;
        removed.next = null;
        return removed;
    }

    private PatientNode find(String id) {
        PatientNode curr = head;
        while (curr != null) {
            if (curr.id.equals(id)) return curr;
            curr = curr.next;
        }
        return null;
    }

    public void addPatient(String id, String name, int priority) {
        if (priority < 1 || priority > 5) {
            System.out.println("Error: priority must be between 1 and 5.");
            return;
        }
        if (find(id) != null) {
            System.out.println("Error: patient ID " + id + " already exists.");
            return;
        }
        insertNode(new PatientNode(id, name, priority));
        System.out.println("Added: " + id + " (" + name + ", priority " + priority + ")");
    }

    public void removePatient(String id) {
        PatientNode removed = detachNode(id);
        if (removed == null) {
            System.out.println("Patient " + id + " not found.");
        } else {
            System.out.println("Removed (examined): " + removed);
        }
    }

    public void searchPatient(String id) {
        PatientNode p = find(id);
        if (p == null) {
            System.out.println("Patient " + id + " not found.");
        } else {
            System.out.println("Found -> ID: " + p.id + " | Name: " + p.name
                    + " | Priority: " + p.priority);
        }
    }

    public void changePriority(String id, int newPriority) {
        if (newPriority < 1 || newPriority > 5) {
            System.out.println("Error: priority must be between 1 and 5.");
            return;
        }
        PatientNode p = find(id);
        if (p == null) {
            System.out.println("Patient " + id + " not found.");
            return;
        }
        if (p.priority == newPriority) {
            System.out.println("Patient " + id + " already has priority " + newPriority + ".");
            return;
        }
        PatientNode node = detachNode(id);
        node.priority = newPriority;     
        insertNode(node);                
        System.out.println("Priority of " + id + " changed to " + newPriority);
    }

    public int countPatients() {
        int count = 0;
        PatientNode curr = head;
        while (curr != null) {
            count++;
            curr = curr.next;
        }
        return count;
    }

    public void serveNextPatient() {
        if (head == null) {
            System.out.println("No patients are waiting.");
            return;
        }
        PatientNode served = head;
        head = head.next;
        served.next = null;
        System.out.println("Now serving: " + served);
    }

    public void displayByPriority(int priority) {
        System.out.println("Patients with priority " + priority + ":");
        boolean any = false;
        PatientNode curr = head;
        while (curr != null) {
            if (curr.priority == priority) {
                System.out.println("  " + curr);
                any = true;
            }
            curr = curr.next;
        }
        if (!any) System.out.println("  (none)");
    }

    public void displayWaitingList() {
        if (head == null) {
            System.out.println("Waiting list: empty -> null");
            return;
        }
        System.out.print("Waiting list: ");
        PatientNode curr = head;
        while (curr != null) {
            System.out.print(curr + " -> ");
            curr = curr.next;
        }
        System.out.println("null");
    }
}

public class PatientManagement {
    public static void main(String[] args) {
        WaitingList list = new WaitingList();
        System.out.println("--- Adding patients ---");
        list.addPatient("P07", " ", 4);
        list.addPatient("P18", "Hamza", 2);
        list.addPatient("P12", "Ayesha", 1);
        list.addPatient("P21", "Sara", 2);
        list.addPatient("P30", "Usman", 5);
        list.addPatient("P33", "Zainab", 4);  
        list.addPatient("P07", "Duplicate", 3); 
        list.displayWaitingList();

        System.out.println("\n--- Search ---");
        list.searchPatient("P18");
        list.searchPatient("P99");

        System.out.println("\n--- Count ---");
        System.out.println("Total waiting patients: " + list.countPatients());

        System.out.println("\n--- Display by priority ---");
        list.displayByPriority(2);
        list.displayByPriority(3);

        System.out.println("\n--- Change priority ---");
        list.changePriority("P30", 1); 
        list.changePriority("P12", 3);   
        list.displayWaitingList();

        System.out.println("\n--- Remove patient ---");
        list.removePatient("P07");
        list.removePatient("P99");
        list.displayWaitingList();

        System.out.println("\n--- Serve next patient ---");
        list.serveNextPatient();
        list.serveNextPatient();
        list.displayWaitingList();
        System.out.println("Total waiting patients: " + list.countPatients());
    }
}