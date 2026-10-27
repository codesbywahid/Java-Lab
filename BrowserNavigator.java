class BrowserHistory {
    
    // Back stack
    String[] backStack;
    int backTop;
    int stackSize;

    // Forward stack
    String[] forwardStack;
    int forwardTop;

    // Circular queue
    String[] recentQueue;
    int front;
    int rear;
    int count;
    int queueSize;

    // Current website
    String currentWebsite;

    BrowserHistory(int stackSize, int queueSize) {
        if (stackSize <= 0 || queueSize <= 0) {
            throw new IllegalArgumentException(
                "Size must be greater than zero."
            );
        }
        this.stackSize = stackSize;
        this.queueSize = queueSize;

        backStack = new String[stackSize];
        forwardStack = new String[stackSize];

        backTop = -1;
        forwardTop = -1;

        recentQueue = new String[queueSize];
        front = 0;
        rear = -1;
        count = 0;

        currentWebsite = null;
    }
    // Push into Back stack
    boolean pushBack(String url) {

        if (backTop == stackSize - 1) {
            return false;
        }
        backTop++;
        backStack[backTop] = url;
        return true;
}
    // Push into Forward stack
    boolean pushForward(String url) {

        if (forwardTop == stackSize - 1) {
            return false;
        }

        forwardTop++;
        forwardStack[forwardTop] = url;
        return true;
    }
    // Pop from Back stack
    String popBack() {

        if (backTop == -1) {
            return null;
        }
        String url = backStack[backTop];
        backStack[backTop] = null;
        backTop--;

        return url;
}
    // Pop from Forward stack
    String popForward() {
        if (forwardTop == -1) {
            return null;
        }
        String url = forwardStack[forwardTop];
        forwardStack[forwardTop] = null;
        forwardTop--;
        return url;
}
    // Clear Forward stack
    void clearForward() {

        while (forwardTop != -1) {
            forwardStack[forwardTop] = null;
            forwardTop--;
}}
    // Record recent visit
    void recordVisit(String url) {
        if (url == null) {
            return;
        }
        // Avoid consecutive duplicate
        if (count > 0 && recentQueue[rear].equals(url)) {
            return;
        }
        // Remove oldest visit if queue is full
        if (count == queueSize) {
            recentQueue[front] = null;
            front = (front + 1) % queueSize;
            count--;
        }
        // Add new visit
        rear = (rear + 1) % queueSize;
        recentQueue[rear] = url;
        count++;
    }
    // Visit a new website
    void visitWebsite(String url) {
        if (url == null || url.trim().isEmpty()) {
            System.out.println("Invalid website URL.");
            return;
        }
        // Check Back stack before changing history
        if (currentWebsite != null &&
            backTop == stackSize - 1) {

            System.out.println("Back stack is full. Visit cancelled.");
            return;
        }
        if (currentWebsite != null) {
            pushBack(currentWebsite);
        }
        currentWebsite = url;

        // New visit clears Forward stack
        clearForward();
        recordVisit(url);
        System.out.println("Visited: " + currentWebsite);
    }
    // Go back
    void goBack() {
        if (backTop == -1) {
            System.out.println("Cannot go back. Back stack is empty.");
            return;
        }
        // Check Forward stack before navigation
        if (forwardTop == stackSize - 1) {
            System.out.println("Forward stack is full. Cannot go back.");
            return;
        }
        pushForward(currentWebsite);
        currentWebsite = popBack();
        System.out.println("Went back to: " + currentWebsite);
    }
    // Go forward
    void goForward() {

        if (forwardTop == -1) {
            System.out.println("Cannot go forward. Forward stack is empty.");
            return;
        }
        // Check Back stack before navigation
        if (backTop == stackSize - 1) {
            System.out.println("Back stack is full. Cannot go forward.");
            return;
        }
        pushBack(currentWebsite);
        currentWebsite = popForward();

        System.out.println("Went forward to: " + currentWebsite);
    }
    // Display recent visits
    void displayRecentVisits() {

        System.out.println("\nRecent Website Visits:");
        if (count == 0) {
            System.out.println("No recent visits.");
            return;
        }
        for (int i = 0; i < count; i++) {
            int index = (front + i) % queueSize;
            System.out.println(recentQueue[index]);
}}
    // Display navigation history
    void displayNavigationHistory() {
        System.out.println("\nNavigation History:");

        System.out.println("Current Website: " +
            (currentWebsite == null ? "None" : currentWebsite));

        System.out.print("Back Stack (top to bottom): ");

        if (backTop == -1) {
            System.out.print("Empty");
        } else {
            for (int i = backTop; i >= 0; i--) {
                System.out.print(backStack[i] + " ");
}}
        System.out.println();

        System.out.print("Forward Stack (top to bottom): ");

        if (forwardTop == -1) {
            System.out.print("Empty");
        } else {
            for (int i = forwardTop; i >= 0; i--) {
                System.out.print(forwardStack[i] + " ");
}}
        System.out.println();
    }
    public static void main(String[] args) {

        BrowserHistory browser = new BrowserHistory(3, 3);
        // Test empty stacks
        browser.goBack();
        browser.goForward();
        // Visit websites
        browser.visitWebsite("Google");
        browser.visitWebsite("YouTube");
        browser.visitWebsite("Facebook");
        // Test consecutive duplicate
        browser.visitWebsite("Facebook");
        // Test full circular queue
        browser.visitWebsite("Wikipedia");
        browser.displayRecentVisits();
        browser.displayNavigationHistory();
        // Test Back navigation
        browser.goBack();
        browser.goBack();
        // Test Forward navigation
        browser.goForward();
        browser.displayNavigationHistory();
        // New visit clears Forward stack
        browser.visitWebsite("Amazon");
        browser.goForward();
        browser.displayRecentVisits();
        browser.displayNavigationHistory();
        // Test full Back stack
        browser.visitWebsite("Instagram");
        browser.visitWebsite("Twitter");
}}