import java.util.Scanner;

public class LRUPageReplacement {

    private static int findLRU(int[] lastUsed, int numFrames) {
        int oldest = lastUsed[0], pos = 0;
        for (int i = 1; i < numFrames; i++) {
            if (lastUsed[i] < oldest) {
                oldest = lastUsed[i];
                pos = i;
            }
        }
        return pos;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of frames: ");
        int numFrames = scanner.nextInt();

        final int MAX_REF_STRINGS = 100;
        int[] refString = new int[MAX_REF_STRINGS];
        System.out.println("Enter the reference string (end with -1): ");

        int numRefStrings = 0;
        while (numRefStrings < MAX_REF_STRINGS) {
            int input = scanner.nextInt();
            if (input == -1) {
                break;
            }
            refString[numRefStrings] = input;
            numRefStrings++;
        }

        int[] frames = new int[numFrames];
        int[] lastUsed = new int[numFrames];
        for (int i = 0; i < numFrames; ++i) {
            frames[i] = -1; // Initialize frames
            lastUsed[i] = -1; // Initialize last used times
        }

        int pageFaultCount = 0;
        System.out.println("\tRef String\tPage Frames");
        for (int i = 0; i < numRefStrings; ++i) {
            System.out.print("\t" + refString[i] + "\t\t");

            boolean pageFault = true;

            for (int j = 0; j < numFrames; ++j) {
                if (frames[j] == refString[i]) {
                    pageFault = false;
                    lastUsed[j] = i; // Update last used time
                    break;
                }
            }


            if (pageFault) {
                int replaceIndex = findLRU(lastUsed, numFrames);
                frames[replaceIndex] = refString[i];
                lastUsed[replaceIndex] = i; // Update last used time
                pageFaultCount++;

                System.out.print("PF No. " + pageFaultCount + "\t");
            }

            for (int j = 0; j < numFrames; ++j) {
                if (frames[j] != -1) {
                    System.out.print(frames[j] + "\t");
                } else {
                    System.out.print("Empty\t");
                }
            }

            System.out.println();
        }

        System.out.println("The number of page faults using LRU are: " + pageFaultCount);
        scanner.close();
    }
}
