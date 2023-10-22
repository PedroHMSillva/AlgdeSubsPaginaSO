import java.util.LinkedList;

class Page {
    int pageNumber;
    boolean referenced;
    boolean modified;

    public Page(int pageNumber) {
        this.pageNumber = pageNumber;
        this.referenced = false;
        this.modified = false;
    }
}

class WSClock {
    private LinkedList<Page> frameList;
    private int capacity;
    private int hand;

    public WSClock(int capacity) {
        this.capacity = capacity;
        this.frameList = new LinkedList<>();
        this.hand = 0;
    }

    public void referencePage(int pageNumber) {
        
        for (Page page : frameList) {
            if (page.pageNumber == pageNumber) {
                page.referenced = true;
                return;
            }
        }

        
        for (Page page : frameList) {
            if (!page.referenced) {
                page.pageNumber = pageNumber;
                page.referenced = true;
                return;
            }
        }

        
        while (true) {
            Page page = frameList.get(hand);
            if (page.referenced) {
                page.referenced = false;
                hand = (hand + 1) % capacity;
            } else {
                if (page.modified) {
                    
                    System.out.println("Page " + page.pageNumber + " evicted (modified)");
                } else {
                    System.out.println("Page " + page.pageNumber + " evicted");
                }
                page.pageNumber = pageNumber;
                page.referenced = true;
                return;
            }
        }
    }

    public void printPageTable() {
        System.out.println("Page Table:");
        for (Page page : frameList) {
            System.out.println("Page " + page.pageNumber + " - Referenced: " + page.referenced + " - Modified: " + page.modified);
        }
    }
}

public class WSCLOCKPageReplacement {
    public static void main(String[] args) {
        int frameCount = 4;
        WSClock wsClock = new WSClock(frameCount);

        wsClock.referencePage(1);
        wsClock.referencePage(2);
        wsClock.referencePage(3);
        wsClock.referencePage(4);
        wsClock.referencePage(1);
        wsClock.referencePage(5);
        wsClock.referencePage(6);
        wsClock.referencePage(1);
        wsClock.referencePage(7);
        wsClock.referencePage(8);
        wsClock.referencePage(2);

        wsClock.printPageTable();
    }
}

