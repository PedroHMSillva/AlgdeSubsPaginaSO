import java.util.ArrayDeque;
import java.util.Queue;

public class FIFOPageReplacement {
    public static void main(String[] args) {
        int[] pages = {1, 2, 3, 4, 1, 5, 6, 2, 7, 8, 3};
        int numberOfFrames = 3;

        int pageFaults = performFIFO(pages, numberOfFrames);
        System.out.println("Número de faltas de página: " + pageFaults);
    }

    public static int performFIFO(int[] pages, int numberOfFrames) {
        Queue<Integer> frameQueue = new ArrayDeque<>(numberOfFrames);
        int pageFaults = 0;

        for (int page : pages) {
            if (!frameQueue.contains(page)) {
                if (frameQueue.size() < numberOfFrames) {
                    frameQueue.add(page);
                } else {
                    int removedPage = frameQueue.poll();
                    frameQueue.add(page);
                    System.out.println("Substituir página: " + removedPage + " por página: " + page);
                    pageFaults++;
                }
            }
            System.out.println("Quadros de página atuais: " + frameQueue);
        }

        return pageFaults;
    }
}

