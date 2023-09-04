// Samuel Subra

import java.util.LinkedList;
import java.util.Scanner;

public class ReachabilityMatrix
{
    public static void main(String[] args)
    {
        // SET UP
        boolean enterData = false;
        int option = 0;
        int n = 0;
        int sum = 0;
        Integer[][] inputMatrix = new Integer[1][1];
        Scanner s1 = new Scanner(System.in);
        LinkedList<Integer[][]> myList = new LinkedList<>();

        // MAIN MENU
        do
        {
            System.out.println();
            System.out.println("------MAIN MENU------");
            System.out.println("1. Enter graph data");
            System.out.println("2. Print outputs");
            System.out.println("3. Exit program");
            System.out.println();
            System.out.print("Enter option number: ");
            option = s1.nextInt();
            s1.nextLine();
            System.out.println();

            switch (option)
            {
                // 1 ENTER GRAPH DATA
                case 1:
                    // User Input - number of nodes
                    System.out.print("Enter number of nodes in graph: ");
                    n = s1.nextInt();
                    s1.nextLine();

                    // User Input - graph data
                    inputMatrix = new Integer[n][n];
                    for (int i = 0; i < n; i++)
                    {
                        for (int j = 0; j < n; j++)
                        {
                            System.out.print("Enter A1[" + i + "," + j + "]: ");
                            inputMatrix[i][j] = s1.nextInt();
                            s1.nextLine();
                        }
                    }
                    myList.add(inputMatrix);

                    enterData = true;
                    break;

                // 2 PRINT
                case 2:
                    if (!enterData)
                    {
                        System.out.println("Please select option 1 first to enter graph data.");
                    }
                    else
                    {
                        // Print Input Matrix
                        System.out.println("Input Matrix:");
                        for (int i = 0; i < n; i++)
                        {
                            for (int j = 0; j < n; j++)
                            {
                                System.out.print(inputMatrix[i][j] + " ");
                            }
                            System.out.println();
                        }
                        System.out.println();

                        // Calculate Adjacency Matrices
                        Integer[][] temp1 = new Integer[n][n];
                        Integer[][] temp2 = new Integer[n][n];
                        sum = 0;
                        for (int h = 1; h < n; h++)
                        {
                            temp1 = myList.get(h-1); // previous matrix
                            temp2 = new Integer[n][n];
                            for (int i = 0; i < n; i++)
                            {
                                for (int j = 0; j < n; j++)
                                {
                                    for (int k = 0; k < n; k++)
                                    {
                                        sum = sum + temp1[i][k] * inputMatrix[k][j];
                                    }
                                    temp2[i][j] = sum;
                                    sum = 0;
                                }
                            }
                            myList.add(temp2);
                        }

                        // Calculate Reachability Matrix
                        Integer[][] reachabilityMatrix = new Integer[n][n];
                        for (int i = 0; i < n; i++)
                        {
                            for (int j = 0; j < n; j++)
                            {
                                reachabilityMatrix[i][j] = 0;
                            }
                        }

                        for (int h = 0; h < n; h++)
                        {
                            for (int i = 0; i < n; i++)
                            {
                                for (int j = 0; j < n; j++)
                                {
                                    reachabilityMatrix[i][j] = reachabilityMatrix[i][j] + myList.get(h)[i][j];
                                }
                            }
                        }

                        // Print Reachability Matrix
                        System.out.println("Reachability Matrix:");
                        for (int i = 0; i < n; i++)
                        {
                            for (int j = 0; j < n; j++)
                            {
                                System.out.print(reachabilityMatrix[i][j] + " ");
                            }
                            System.out.println();
                        }
                        System.out.println();

                        // Print Graph Information

                        // In-Degrees
                        System.out.println("In-degrees:");
                        for (int i = 1; i <= n; i++)
                        {
                            sum = 0;
                            for (int j = 0; j < n; j++)
                            {
                                sum = sum + inputMatrix[j][i-1];
                            }
                            System.out.println("Node " + i + " in-degree is " + sum);
                        }
                        System.out.println();

                        // Out-Degrees
                        System.out.println("Out-degrees:");
                        for (int i = 1; i <= n; i++)
                        {
                            sum = 0;
                            for (int j = 0; j < n; j++)
                            {
                                sum = sum + inputMatrix[i-1][j];
                            }
                            System.out.println("Node " + i + " out-degree is " + sum);
                        }
                        System.out.println();

                        // Self-Loops
                        sum = 0;
                        for (int i = 0; i < n; i++)
                        {
                            sum = sum + inputMatrix[i][i];
                        }
                        System.out.print("Total number of self-loops: " + sum);
                        System.out.println();

                        // Cycles of Length 3
                        sum = 0;
                        for (int i = 0; i < n; i++)
                        {
                            sum = sum + myList.get(2)[i][i];
                        }
                        System.out.print("Total number of cycles of length 3 edges: " + sum);
                        System.out.println();

                        // Paths of Length 1
                        sum = 0;
                        for (int i = 0; i < n; i++)
                        {
                            for (int j = 0; j < n; j++)
                            {
                                sum = sum + inputMatrix[i][j];
                            }
                        }
                        System.out.print("Total number of paths of length 1 edge: " + sum);
                        System.out.println();

                        // Paths of Length 3
                        sum = 0;
                        for (int i = 0; i < n; i++)
                        {
                            for (int j = 0; j < n; j++)
                            {
                                sum = sum + myList.get(2)[i][j];
                            }
                        }
                        System.out.print("Total number of paths of length 3 edges: " + sum);
                        System.out.println();

                        // Paths of Length 1 to 3
                        sum = 0;
                        for (int h = 0; h < 3; h++)
                        {
                            temp1 = myList.get(h);
                            for (int i = 0; i < n; i++)
                            {
                                for (int j = 0; j < n; j++)
                                {
                                    sum = sum + temp1[i][j];
                                }
                            }
                        }
                        System.out.print("Total number of paths of length 1 to 3 edges: " + sum);
                        System.out.println();

                        // Cycles of Length 1 to 3
                        sum = 0;
                        for (int h = 0; h < 3; h++)
                        {
                            temp1 = myList.get(h);
                            for (int i = 0; i < n; i++)
                            {
                                sum = sum + temp1[i][i];
                            }
                        }
                        System.out.print("Total number of cycles of length 1 to 3 edges: " + sum);
                        System.out.println();
                        System.out.println("--------------------------------------------------");
                    }
                    break;

                // 3 EXIT
                case 3:
                    break;

                // EXCEPTIONS
                default:
                    System.out.println("Invalid option, please try again.");
                    break;
            }
        } while (option != 3);
    }
}