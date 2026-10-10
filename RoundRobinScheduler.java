// Round-Robin Task Scheduler using a Circular Singly Linked List
class Node {
    int taskID;
    String taskName;
    int executionTime;
    Node next;
    Node(int taskID, String taskName, int executionTime) {
        this.taskID = taskID;
        this.taskName = taskName;
        this.executionTime = executionTime;
        this.next = null;
    }
}
class TaskScheduler {
    private Node head;    
    private Node tail;     
    private Node current;  

    // 1. Add a task at the end
    public void addTask(int id, String name, int time) {
        Node newNode = new Node(id, name, time);

        if (head == null) {
            head = newNode;
            tail = newNode;
            newNode.next = newNode;
            current = newNode;
        } else {
            tail.next = newNode;
            newNode.next = head;  
            tail = newNode;
        }
        System.out.println("Task added: " + id + " - " + name);
    }
    
    // 2. Display all tasks once
    public void displayTasks() {
        if (head == null) {
            System.out.println("No tasks in the list.");
            return;
        }

        System.out.println("---- Task List ----");
        Node temp = head;
        do {
            System.out.println("ID: " + temp.taskID + " | Name: " + temp.taskName
                    + " | Time: " + temp.executionTime);
            temp = temp.next;
        } while (temp != head);
        System.out.println("-------------------");
    }

    // 3. Show the current task and move to the next one
    public void executeNextTask() {
        if (head == null) {
            System.out.println("No tasks to execute.");
            return;
        }

        System.out.println("Executing -> ID: " + current.taskID + " | Name: "
                + current.taskName + " | Time: " + current.executionTime);
        current = current.next;
    }

    // 4. Search a task by ID
    public void searchTask(int id) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        Node temp = head;
        do {
            if (temp.taskID == id) {
                System.out.println("Task found -> ID: " + temp.taskID + " | Name: "
                        + temp.taskName + " | Time: " + temp.executionTime);
                return;
            }
            temp = temp.next;
        } while (temp != head);

        System.out.println("Task " + id + " not found.");
    }

    // 5. Remove a task by ID
    public void removeTask(int id) {
        if (head == null) {
            System.out.println("List is empty, nothing to remove.");
            return;
        }

        // Case 1: only one node in the list
        if (head == tail) {
            if (head.taskID == id) {
                head = null;
                tail = null;
                current = null;
                System.out.println("Task " + id + " removed. List is now empty.");
            } else {
                System.out.println("Task " + id + " not found.");
            }
            return;
        }

        // Case 2: the task is the head
        if (head.taskID == id) {
            if (current == head) {
                current = head.next;
            }
            head = head.next;
            tail.next = head;      // keep the circle
            System.out.println("Task " + id + " removed.");
            return;
        }

        // Case 3: the task is in the middle or at the end
        Node prev = head;
        Node curr = head.next;
        while (curr != head) {
            if (curr.taskID == id) {
                if (current == curr) {
                    current = curr.next;
                }
                prev.next = curr.next;   // skip the node
                if (curr == tail) {
                    tail = prev;         // last node was removed
                }
                System.out.println("Task " + id + " removed.");
                return;
            }
            prev = curr;
            curr = curr.next;
        }

        System.out.println("Task " + id + " not found.");
    }

    // 6. Count the tasks
    public int countTasks() {
        if (head == null) {
            return 0;
        }

        int count = 0;
        Node temp = head;
        do {
            count++;
            temp = temp.next;
        } while (temp != head);
        return count;
    }
}

public class RoundRobinScheduler {
    public static void main(String[] args) {
        TaskScheduler s = new TaskScheduler();
        s.displayTasks();
        s.executeNextTask();
        s.removeTask(101);
        System.out.println("Total tasks: " + s.countTasks());
        System.out.println();

        s.addTask(101, "Backup", 5);
        s.addTask(102, "Printing", 3);
        s.addTask(103, "Database", 8);
        s.addTask(104, "Antivirus", 6);
        System.out.println();

        s.displayTasks();
        System.out.println("Total tasks: " + s.countTasks());
        System.out.println();

        for (int i = 0; i < 6; i++) {
            s.executeNextTask();
        }
        System.out.println();

        s.searchTask(103);
        s.searchTask(999);
        System.out.println();

        s.removeTask(102);
        s.removeTask(104);
        s.removeTask(101);
        s.displayTasks();
        System.out.println("Total tasks: " + s.countTasks());
        System.out.println();
        s.removeTask(103);
        s.displayTasks();
        System.out.println("Total tasks: " + s.countTasks());
    }
}