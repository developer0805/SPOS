import java.util.Scanner;

public class BestFit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of memory blocks
        System.out.print("Enter number of memory blocks: ");
        int m = sc.nextInt();
        int[] blockSize = new int[m];
        int[] blockAllocated = new int[m]; // -1 means block is free

        System.out.println("Enter size of each memory block:");
        for (int i = 0; i < m; i++) {
            System.out.print("Block " + (i + 1) + ": ");
            blockSize[i] = sc.nextInt();
            blockAllocated[i] = -1;
        }

        // Input number of processes
        System.out.print("\nEnter number of processes: ");
        int n = sc.nextInt();
        int[] processSize = new int[n];
        int[] allocation = new int[n]; // Stores block index allocated to each process

        System.out.println("Enter size of each process:");
        for (int i = 0; i < n; i++) {
            System.out.print("Process " + (i + 1) + ": ");
            processSize[i] = sc.nextInt();
            allocation[i] = -1;
        }

        // Best Fit Allocation Logic
        for (int i = 0; i < n; i++) {
            int bestIdx = -1;
            for (int j = 0; j < m; j++) {
                if (blockAllocated[j] == -1 && blockSize[j] >= processSize[i]) {
                    if (bestIdx == -1 || blockSize[j] < blockSize[bestIdx]) {
                        bestIdx = j; // Choose the smallest block that fits
                    }
                }
            }
            if (bestIdx != -1) {
                allocation[i] = bestIdx;
                blockAllocated[bestIdx] = i;
                blockSize[bestIdx] -= processSize[i]; // Remaining space (internal fragmentation)
            }
        }

        // Display Results
        System.out.println("\nMemory Allocation Table:");
        System.out.println("Process\tSize\tBlock Allocated");
        for (int i = 0; i < n; i++) {
            if (allocation[i] != -1)
                System.out.println("P" + (i + 1) + "\t" + processSize[i] + "\tBlock " + (allocation[i] + 1));
            else
                System.out.println("P" + (i + 1) + "\t" + processSize[i] + "\tNot Allocated");
        }

        // Display Internal Fragmentation
        System.out.println("\nInternal Fragmentation (Unused Space in Blocks):");
        for (int i = 0; i < m; i++) {
            System.out.println("Block " + (i + 1) + " → Remaining Space: " + blockSize[i]);
        }

        sc.close();
    }
}
