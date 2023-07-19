package Homework5;

/**
 * @author Pushkar Taday
 * SBU ID:114375166
 * Recitation:04
 */

import java.io.*;
import java.util.Scanner;

/**
 * This class contains a menu driven application to form and operate on the nintendo network
 */

public class NintendoNetwork {

    public static NetworkTree networkTree;

    /**
     * Menu driven program and the main execution
     * @param args
     * arguments passed as the parameter
     * @throws FileNotFoundException
     * if the file is not found
     */

    public static void main(String[] args) throws FileNotFoundException {
        try {

            NetworkNode removedNode=null;

            networkTree = new NetworkTree();

            Scanner input = new Scanner(System.in);

            System.out.println("Welcome to the Nintendo Network Manager.\n" +
                    "\n" +
                    "Menu:\n" +
                    "\n" +
                    "    L) Load from file\n" +
                    "\n" +
                    "    P) Print tree\n" +
                    "\n" +
                    "    C) Move cursor to a child node\n" +
                    "\n" +
                    "    R) Move cursor to root\n" +
                    "\n" +
                    "    U) Move cursor up to parent\n" +
                    "\n" +
                    "    A) Add a child \n" +
                    "\n" +
                    "    X) Remove/Cut Cursor and its subtree\n" +
                    "\n" +
                    "    V) Paste Cursor and its subtree\n" +
                    "\n" +
                    "    S) Save to file\n" +
                    "\n" +
                    "    M) Cursor to root of minimal subtree containing all faults\n" +
                    "\n" +
                    "    B) Mark cursor as broken/fixed\n" +
                    "\n" +
                    "    Q) Quit");

            boolean check = true;

            while (check) {
                System.out.print("Please select an option:");
                char enter = input.next().charAt(0);
                input.nextLine();

                enter = Character.toUpperCase(enter);

                switch (enter) {
                    case 'L':
                        System.out.print("Please enter filename:");
                        String fileName = input.nextLine();

                        try {
                            networkTree = networkTree.readFromFile(fileName);
                            System.out.println(fileName+" loaded.");
                        } catch (IOException | EmptyNodeException e) {
                            System.out.println(fileName+" file not found.");
                        }

                        System.out.println();
                        break;

                    case 'P':
                        System.out.println();
                        System.out.println(networkTree.toString());
                         break;

                    case 'C':
                        System.out.print("Please select an index:");
                         int index = input.nextInt();
                        networkTree.cursorToChild(index - 1);

                        System.out.println("Cursor moved to "+networkTree.getCursor().getName()+".");
                        System.out.println();
                        break;

                    case 'R':
                        networkTree.cursorToRoot();
                        System.out.println("Cursor moved to "+networkTree.getRoot()+".");
                        System.out.println();
                                break;

                    case 'U':
                             networkTree.cursorToParent();
                               System.out.println("Cursor moved to "+networkTree.getCursor().getName()+".");
                        System.out.println();
                                break;

                    case 'A':
                        System.out.print("Please enter an index:");
                        int addIndex=input.nextInt();



                        input.nextLine();

                        System.out.print("Enter device name:");
                        String deviceName=input.nextLine();



                        boolean checkIsNintendo=true;

                        NetworkNode networkNode=null;

                        while(checkIsNintendo) {

                            System.out.print("Is this Nintendo (y/n):");
                            char isNintendo = input.next().charAt(0);


                            isNintendo = Character.toUpperCase(isNintendo);

                            if(isNintendo=='Y') {
                                networkNode = new NetworkNode(deviceName, true, false);
                                checkIsNintendo = false;
                                System.out.println("Nintendo added!");
                            }

                            else if(isNintendo=='N')
                            {
                                networkNode = new NetworkNode(deviceName, false, false);
                                checkIsNintendo = false;
                            }

                            else
                                System.out.println("Only enter Y or N");


                        }

                        networkTree.addChild(addIndex,networkNode);

                        networkTree.setCursor(networkNode);
                        System.out.println();

                        break;

                    case 'X':
                        removedNode=networkTree.cutCursor();

                        if(networkTree.getCursor()==null){
                        System.out.println(removedNode.getName()+" cut, cursor is at "+networkTree.getCursor().getName()+".");}

                        else
                            System.out.println();System.out.println(removedNode.getName()+" cut, cursor now does not point to anything.");

                        System.out.println();

                        break;


                    case 'V':
                        System.out.print("Please enter an index:");
                        int pasteIndex=input.nextInt();
                        networkTree.addChild(pasteIndex,removedNode);
                        System.out.println(removedNode.getName()+" pasted as a child of "+networkTree.getCursor()+".");
                        networkTree.setCursor(removedNode);
                        System.out.println();
                        break;

                    case 'B':
                        networkTree.changeCursorBrokenStatus();
                        System.out.println();
                        break;

                    case 'Q':
                        System.out.println("Make like a tree and leave!");
                        check = false;
                        break;

                    case 'S':

                        System.out.print("Please enter a filename:");
                        String file=input.nextLine();
                        networkTree.writeToFile(networkTree,file);
                        System.out.println("File Saved.");
                        System.out.println();
                        break;

                    case 'M':
                        networkTree.cursorToMinimalBrokenSubtree();

                        if(networkTree.getCursor()==null)
                            System.out.println("No minimal broken subtree available.");

                        else
                        System.out.println("Cursor moved to "+networkTree.getCursor().getName());

                        break;

                    default:
                        System.out.println("Wrong input. Enter again:");

                }
            }


        } catch (EmptyNodeException | IOException e) {

            System.out.println(e.getMessage());
        }

    }
}