import java.util.ArrayDeque;
import java.util.Queue;

public class FIFOSCPageReplacement {
    public static void main(String[] args) {
        int[] referenceString = {1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5};
        int frameCount = 3;

        int pageFaults = simulateFIFO(referenceString, frameCount);
        System.out.println("Número de faltas de página: " + pageFaults);
    }

    public static int simulateFIFO(int[] referenceString, int frameCount) {
        int pageFaults = 0;
        int[] frames = new int[frameCount];
        Queue<Integer> fifoQueue = new ArrayDeque<>();

        for (int i = 0; i < referenceString.length; i++) {
            int page = referenceString[i];

            if (!fifoQueue.contains(page)) {
                pageFaults++;
                if (fifoQueue.size() == frameCount) {
                    int removedPage = fifoQueue.poll();
                    int index = -1;
                    for (int j = 0; j < frameCount; j++) {
                        if (frames[j] == removedPage) {
                            index = j;
                            break;
                        }
                    }
                    if (index != -1) {
                        frames[index] = page;
                    }
                } else {
                    frames[fifoQueue.size()] = page;
                }
                fifoQueue.add(page);
            }
        }

        return pageFaults;
    }
}

