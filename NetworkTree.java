package Homework5;

/**
 * @author Pushkar Taday
 * SBU ID:114375166
 * Recitation:04
 */

import java.io.*;

/**
 * This class helps to create a tree for the nintendo nodes.
 */
public class NetworkTree {
    private NetworkNode root;
    private NetworkNode cursor;

    /**
     * This is a default constructor for this class which gets instantiated during the object instantiation.
     */
    NetworkTree()
    {
        root=null;
        cursor=null;
    }

    /**
     * This is the accessor method for the cursor
     * @return
     * reference of the cursor
     */
    public NetworkNode getCursor() {
        return cursor;
    }

    /**
     * this is the accessor method of the root
     * @return
     * reference of the root
     */
    public NetworkNode getRoot() {
        return root;
    }

    /**
     * This is a mutator method for the cursor
     * @param cursor
     * sets the reference cursor with the passed reference
     */
    public void setCursor(NetworkNode cursor) {
        this.cursor = cursor;
    }

    /**
     * This is a mutator method for the root of the tree
     * @param root
     * sets the reference of the root with the passed reference
     */
    public void setRoot(NetworkNode root) {
        this.root = root;
    }

    /**
     * This method sets the cursor to the root of the tree
     * @throws EmptyNodeException
     * if there are no nodes
     */
    public void cursorToRoot() throws EmptyNodeException
    {
        if(root==null)
            throw new EmptyNodeException("Exception. No nodes in this tree.");

        else
            cursor=getRoot();

    }

    /**
     * This method adds child to given node
     * @param index
     * sets the reference of the child in the children array of the node
     * @param node
     * node which gets added to the aarray
     * @throws EmptyNodeException
     * if the node is empty, there are no nodes in the tree and if
     * @throws IllegalArgumentException
     * if it creates a hole in the array of children
     */
    public void addChild(int index, NetworkNode node) throws EmptyNodeException, IllegalArgumentException
    {
        if(node==null||root==null)
            throw new EmptyNodeException("Node is null.");

        else {
            if (index >= 1 && index<10) {

                if(cursor.getChildren(index-1) == null&& cursor.getCount()==index-1) {

                    cursor.setChildren(node, index - 1);
                    cursor.setCount(cursor.getCount() + 1);
                    node.setParent(cursor);
                }
                else
                {
                    if(index==9)
                    {
                        cursor.setChildren(node, index - 1);
                        node.setParent(cursor);
                    }
                    else
                    {

                       for(int i=cursor.getCount()+1;i>=index;i--)
                       {
                           cursor.setChildren(cursor.getChildren(i-1),i);
                       }
                        cursor.setChildren(node, index - 1);
                        cursor.setCount(cursor.getCount() + 1);
                        node.setParent(cursor);

                    }
                }
            }

            else
            {
                throw new IllegalArgumentException("Will create a hole.");
            }


        }
    }

    /**
     * This method sets the reference of the cursor to the child node at the specified index
     * @param index
     * index of the child node form the array of children
     * @throws EmptyNodeException
     * if there is no child node at the specified index in the array
     */
    public void cursorToChild(int index)throws EmptyNodeException
    {
        if(cursor.getChildren(index)==null)
        {
            throw new EmptyNodeException("Null node");
        }
        else
        {
            cursor=cursor.getChildren(index);
        }
    }

    /**
     * This method sets the reference of the cursor to the parent of the currently referneced node
     * @throws EmptyNodeException
     * if cursor is pointing to the root
     */

    public void cursorToParent() throws EmptyNodeException
    {
        if(cursor==root) {
            throw new EmptyNodeException("This is the parent node");
        }

        else
            cursor=cursor.getParent();

    }

    /**
     * This method removes the node at the cursor.
     * @return
     * node which is to be removed
     */

    public NetworkNode cutCursor()
    {

        if(cursor==root)
        {
            NetworkNode tempRoot=root;
            root=null;
            cursor=null;
            return tempRoot;
        }

        else {
            NetworkNode temp = cursor;

            NetworkNode referenceToParent = cursor.getParent();

            NetworkNode lastElement = referenceToParent.getChildren(referenceToParent.getCount() - 1);

            cursor = null;

            cursor = referenceToParent;

            if (lastElement == temp) {
                cursor.setCount(cursor.getCount() - 1);
            } else {
                int findIndex = 0;

                for (int i = 0; i < cursor.getCount(); i++) {
                    if (cursor.getChildren(0) == null)
                        findIndex = i;
                }

                for (int i = findIndex; i < cursor.getCount() - 1; i++) {
                    cursor.setChildren(cursor.getChildren(i + 1), i);
                }
                cursor.setChildren(null, cursor.getCount() - 1);

                cursor.setCount(cursor.getCount() - 1);
            }

            return temp;
        }
    }

    /**
     * This method generates a tree from the file.
     * @param fileName
     * the name of the file
     * @return
     * network tree
     * @throws IOException
     * if buffered reader throws an exception
     * @throws EmptyNodeException
     * if the node references to null
     */

    public static NetworkTree readFromFile(String fileName) throws IOException, EmptyNodeException{
        File file=new File(fileName);

        BufferedReader bufferedReader=new BufferedReader(new FileReader(file));

        String str=null;
        int once = 0;

        while((str=bufferedReader.readLine())!=null) {

            String digit=""; String nodeName="";

                int c = 0;int numberOfDigits=0;
                for (int i = 0; i < str.length(); i++) {

                    if(Character.isDigit(str.charAt(i))) {
                        digit = digit + str.charAt(i);
                        numberOfDigits=numberOfDigits+1;
                    }

                    if(!Character.isDigit(str.charAt(i))&&str.charAt(i)!='-')
                        nodeName=nodeName+str.charAt(i);

                    if (str.charAt(i) == '-')
                        c = 1;
                }
                NetworkNode node=null;

                if (c == 0)
                    node = new NetworkNode(nodeName, false, false);

                else
                    node = new NetworkNode(nodeName, true, false);


                if(once==0) {
                    NintendoNetwork.networkTree.setRoot(node);
                    NintendoNetwork.networkTree.setCursor(NintendoNetwork.networkTree.getRoot());
                    once=1;
                }

                for(int i=0;i<digit.length();i++)
                {
                     int d=digit.charAt(i)-48;

                     if(numberOfDigits==1)
                     NintendoNetwork.networkTree.addChild(d,node);

                     NintendoNetwork.networkTree.setCursor(NintendoNetwork.networkTree.getCursor().getChildren(d-1));


                     numberOfDigits=numberOfDigits-1;
                }
               NintendoNetwork.networkTree.setCursor(NintendoNetwork.networkTree.getRoot());


        }
        return NintendoNetwork.networkTree;
    }

    /**
     * This method helps to write to a file
     * @param tree
     * tree that has to be written to a file
     * @param filename
     * name of the file that has in which text has to be saved
     * @throws IOException
     * if the BufferedWriter  throws an exception
     * @throws EmptyNodeException
     * if the node is null
     */
    public static void writeToFile(NetworkTree tree, String filename) throws IOException, EmptyNodeException {

        if(tree.getRoot()==null)
            throw new EmptyNodeException("Cannot be saved into a file as tree doesn't exist.");


       BufferedWriter writer=new BufferedWriter(new FileWriter(filename));

       writer.write(tree.toString());

       writer.close();


    }

    /**
     * This methods makes the cursor point at the minimal broken subtree
     * @throws EmptyNodeException
     * if the node is null
     */
    public void cursorToMinimalBrokenSubtree() throws EmptyNodeException {

        cursor=getRoot();

     cursor=cursorToMinimalBrokenSubtreeHelper(0,getRoot().getCount(),0,null,0);

    }

    /**
     * A helper method for cursorToMinimalBrokenSubtree
     * @param i
     * start of the iteration
     * @param j
     * end of the iteration
     * @param flagCounter
     * to keep a track of broken nodes
     * @param minimalBrokenSubtree
     * minimal broken down node
     * @param totalCounter
     * total count of broken nodes
     * @return
     * the reference of the cursor
     * @throws EmptyNodeException
     * if the node is null
     */
    public NetworkNode cursorToMinimalBrokenSubtreeHelper(int i, int j,int flagCounter,NetworkNode minimalBrokenSubtree,int totalCounter) throws EmptyNodeException {

       while(i<j&&j>0)
       {
           cursorToChild(i);

           if(cursor.isBroken())
           {
               flagCounter++;

               if(flagCounter>=2||(flagCounter==1&&totalCounter>=1))
                   minimalBrokenSubtree=cursor.getParent();

                   else {
                   minimalBrokenSubtree = cursor;
                   totalCounter++;

               }
           }

           else {
               minimalBrokenSubtree = cursorToMinimalBrokenSubtreeHelper(0, cursor.getCount(), 0, minimalBrokenSubtree, totalCounter);

           }
            i++;
       }
        if(cursor!=root)
            cursorToParent();
       return  minimalBrokenSubtree;
    }

    /**
     * This method changes the status of the nroken status of the node
     */
    public void changeCursorBrokenStatus()
    {
        if(cursor.isBroken()) {
            cursor.setBroken(false);
            System.out.println(cursor.getName()+" is fixed.");
        }

        else {
            cursor.setBroken(true);
            System.out.println(cursor.getName()+" is broken.");
        }
    }

    /**
     * This method helps print the tree
     * @return
     * the string containing the tree to be printed
     */
    @Override
    public String toString()  {

        String s="";

        if(root==null)
        {
            return s;
        }



        NetworkNode tempCursor=cursor;

        NintendoNetwork.networkTree.setCursor(NintendoNetwork.networkTree.getRoot());

        boolean flag=true;

        int i=0;  int j=0;

        String horizontalTab="";

        try {
          //     System.out.println(cursor.getCount());
            s=NintendoNetwork.networkTree.toStringHelper("",tempCursor,0,cursor.getCount(),horizontalTab,false);
            cursor=tempCursor;
        } catch (EmptyNodeException e) {
             e.printStackTrace();
        }
      return s;
    }

    /**
     * This is an helper method for toString()
     * @param s
     * the string containing the tree
     * @param tempCursor
     * the reference of the cursor previous to the toString method to be invoked
     * @param i
     * the start of the iteration
     * @param j
     * end of the iteration
     * @param horizontalTab
     * the horizontal tab to printed
     * @param cursorPointer
     * to print the cursor
     * @return
     * the string containing the tree to be printed
     * @throws EmptyNodeException
     * if the node is null
     */

    public String toStringHelper(String s, NetworkNode tempCursor,int i,int j, String horizontalTab,boolean cursorPointer)throws EmptyNodeException
    {

        if(getCursor()==tempCursor) {
            if(cursor.isBroken())
                s = s + horizontalTab + "->" + getCursor().getName() + " ~Fault~"+"\n";

                else
            s = s + horizontalTab+"->"+getCursor().getName()+"\n";
            cursorPointer=true;
        }

        if(cursor.isNintendo()&&cursorPointer==false) {

            if(cursor.isBroken())
                s = s + horizontalTab + "-" + getCursor().getName() + " ~Fault~"+"\n";

            else
            s = s + horizontalTab + "-" + getCursor().getName() + "\n";
        }

        else if(cursor.isNintendo()==false&&cursorPointer==false) {

            if(cursor.isBroken())
                s = s + horizontalTab + "+" + getCursor().getName() + " ~Fault~"+"\n";

            else
            s = s + horizontalTab + "+" + getCursor().getName() + "\n";
        }


        else {
            if(!cursorPointer)
            s = s + horizontalTab + getCursor().getName() + "\n";
        }

       while (i<j && j>0)
        {
                cursorToChild(i);

                 s=NintendoNetwork.networkTree.toStringHelper(s,tempCursor,0,cursor.getCount(),horizontalTab+"\t",false);
            i++;
        }

        if(cursor!=root)
            cursorToParent();

        return s;
    }

}
