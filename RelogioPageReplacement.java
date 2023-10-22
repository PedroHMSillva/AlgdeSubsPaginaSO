import java.util.LinkedList;

public class RelogioPageReplacement {
    private LinkedList<Integer> frameList;
    private boolean[] referenceBits;
    private int pointer;

    public RelogioPageReplacement(int numFrames) {
        frameList = new LinkedList<>();
        referenceBits = new boolean[numFrames];
        for (int i = 0; i < numFrames; i++) {
            frameList.add(-1);
            referenceBits[i] = false;
        }
        pointer = 0;
    }

    public void referenciarPagina(int pageNumber) {
        if (frameList.contains(pageNumber)) {
            int index = frameList.indexOf(pageNumber);
            referenceBits[index] = true;
        } else {
            substituirPagina(pageNumber);
        }
    }

    private void substituirPagina(int pageNumber) {
        while (true) {
            if (!referenceBits[pointer]) {
                frameList.set(pointer, pageNumber);
                referenceBits[pointer] = false;
                pointer = (pointer + 1) % frameList.size();
                return;
            } else {
                referenceBits[pointer] = false;
                pointer = (pointer + 1) % frameList.size();
            }
        }
    }

    public static void main(String[] args) {
        RelogioPageReplacement algoritmo = new RelogioPageReplacement(3); // Substituição de página com 3 frames

        algoritmo.referenciarPagina(1);
        algoritmo.referenciarPagina(2);
        algoritmo.referenciarPagina(3);
        algoritmo.referenciarPagina(4);
        algoritmo.referenciarPagina(1);
        algoritmo.referenciarPagina(2);
        algoritmo.referenciarPagina(5);

        System.out.println("Páginas em memória: " + algoritmo.frameList);
    }
}

