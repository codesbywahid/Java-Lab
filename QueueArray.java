public class QueueArray {
    int[] arr = new int[5];
    int front;
    int rear;
    int capacity;

    QueueArray(int size){
        capacity=size;
        arr = new int[capacity];
        this.front=0;
        this.rear=-1;
}

public void enqueue(int value){
    if (rear == capacity-1) {
        System.out.println("Queue is full");
        return ;
    }
    arr[++rear]=value;
    System.out.println(value + " inserted");
}

public void dequeue(){
    if (rear<front) {
        System.out.println("Queue is empty");
        return ;   
    }
    System.out.println(arr[front]+" value");
    front++;
}

public void display(){
    if (rear<front) {
        System.out.println("Queue is empty");
        return ;
    }
    for (int i=front; i <= rear; i++) {
        System.out.println(arr[i]+" value");
    }
}

public static void main(String[]args){
    QueueArray q = new QueueArray(5);
    q.enqueue(10);
    q.enqueue(20);
    q.enqueue(30);
    q.dequeue();
    q.display();
}}