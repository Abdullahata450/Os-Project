import java.util.Scanner;

public class PagingSimulation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int ms, ps, nop, np, rempages, i, j, process_no, page_no, pa, offset;
        int[] s = new int[10];
        int[][] fno = new int[10][20];

        System.out.print("\nEnter the memory size -- ");
        ms = scanner.nextInt();

        System.out.print("\nEnter the page size -- ");
        ps = scanner.nextInt();

        nop = ms / ps;
        System.out.println("\nThe no. of pages available in memory are -- " + nop);

        System.out.print("\nEnter number of processes -- ");
        np = scanner.nextInt();
        rempages = nop;

        for (i = 1; i <= np; i++) {
            System.out.print("\nEnter no. of pages required for p[" + i + "]-- ");
            s[i] = scanner.nextInt();

            if (s[i] > rempages) {
                System.out.println("\nMemory is Full");
                break;
            }

            rempages -= s[i];

            System.out.print("\nEnter pagetable for p[" + i + "] --- ");
            for (j = 0; j < s[i]; j++)
                fno[i][j] = scanner.nextInt();
        }

        System.out.print("\nEnter Logical Address to find Physical Address ");
        System.out.print("\nEnter process no., page number, and offset -- ");
        process_no = scanner.nextInt();
        page_no = scanner.nextInt();
        offset = scanner.nextInt();

        if (process_no > np || page_no >= s[process_no] || offset >= ps) {
            System.out.println("\nInvalid Process or Page Number or offset");
        } else {
            pa = fno[process_no][page_no] * ps + offset;
            System.out.println("\nThe Physical Address is -- " + pa);
        }

        scanner.close();
    }
}
