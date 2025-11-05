import java.util.Scanner;

public class WorstFit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of memory blocks and their sizes
        System.out.print("Enter number of memory blocks: ");
        int n = sc.nextInt();
        int blockSize[] = new int[n];
        System.out.println("Enter size of each memory block:");
        for (int i = 0; i < n; i++) {
            blockSize[i] = sc.nextInt();
        }

        // Input number of processes and their sizes
        System.out.print("Enter number of processes: ");
        int m = sc.nextInt();
        int processSize[] = new int[m];
        System.out.println("Enter size of each process:");
        for (int i = 0; i < m; i++) {
            processSize[i] = sc.nextInt();
        }

        int allocation[] = new int[m]; // To store allocated block index
        for (int i = 0; i < m; i++) {
            allocation[i] = -1; // Initially not allocated
        }

        // Worst Fit Allocation Logic
        for (int i = 0; i < m; i++) {
            int worstIdx = -1;

            for (int j = 0; j < n; j++) {
                if (blockSize[j] >= processSize[i]) {
                    if (worstIdx == -1 || blockSize[j] > blockSize[worstIdx]) {
                        worstIdx = j;
                    }
                }
            }

            // If found a suitable block
            if (worstIdx != -1) {
                allocation[i] = worstIdx;
                blockSize[worstIdx] -= processSize[i];
            }
        }

        // Display Results
        System.out.println("\nProcess No.\tProcess Size\tBlock No.");
        for (int i = 0; i < m; i++) {
            System.out.print("   " + (i + 1) + "\t\t" + processSize[i] + "\t\t");
            if (allocation[i] != -1)
                System.out.println(allocation[i] + 1);
            else
                System.out.println("Not Allocated");
        }

        // Display unused space (fragmentation)
        System.out.println("\nRemaining size of each block (Unused space):");
        for (int i = 0; i < n; i++) {
            System.out.println("Block " + (i + 1) + ": " + blockSize[i]);
        }

        sc.close();
    }
}
