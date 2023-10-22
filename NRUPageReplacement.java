import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

public class NRUPageReplacement {
    private List<Page> pages;
    private Random random;

    public NRUPageReplacement(int numPages) {
        pages = new ArrayList<>();
        random = new Random();

        for (int i = 0; i < numPages; i++) {
            pages.add(new Page(i));
        }
    }

    public void referencePage(int pageNumber) {
        Page page = pages.get(pageNumber);
        page.referenced = true;
    }

    public void modifyPage(int pageNumber) {
        Page page = pages.get(pageNumber);
        page.modified = true;
    }

    public int replacePage() {
        List<Page> class0 = new ArrayList<>();
        List<Page> class1 = new ArrayList<>();
        List<Page> class2 = new ArrayList<>();
        List<Page> class3 = new ArrayList<>();

        for (Page page : pages) {
            if (!page.referenced && !page.modified) {
                class0.add(page);
            } else if (!page.referenced && page.modified) {
                class1.add(page);
            } else if (page.referenced && !page.modified) {
                class2.add(page);
            } else {
                class3.add(page);
            }
            page.referenced = false;
        }

        if (!class0.isEmpty()) {
            return evictRandomPage(class0);
        } else if (!class1.isEmpty()) {
            return evictRandomPage(class1);
        } else if (!class2.isEmpty()) {
            return evictRandomPage(class2);
        } else {
            return evictRandomPage(class3);
        }
    }

    private int evictRandomPage(List<Page> pages) {
        int index = random.nextInt(pages.size());
        return pages.get(index).pageNumber;
    }

    public static void main(String[] args) {
        int numPages = 4;
        NRUPageReplacement nru = new NRUPageReplacement(numPages);

        // Simulando referências e modificações de páginas
        for (int i = 0; i < 10; i++) {
            int pageNumber = nru.random.nextInt(numPages);
            if (i % 2 == 0) {
                nru.referencePage(pageNumber);
            } else {
                nru.modifyPage(pageNumber);
            }
        }

        // Substituindo página
        int pageToReplace = nru.replacePage();
        System.out.println("Substituindo página: " + pageToReplace);
    }
}
