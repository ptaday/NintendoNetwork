package Homework5;

/**
 * @author Pushkar Taday
 * SBU ID:114375166
 * Recitation:04
 */

/**
 * This class represents node for the Nintendo network.
 */

public class NetworkNode {

    private String name;
    private boolean isNintendo;
    private boolean isBroken;
    private NetworkNode parent;
    private NetworkNode[] children;
    final int maxChildren=9;
    int childrenCount=0;

    /**
     * This is a parameterized constructor which gets invoked when object of the class is instantiated with the given set of parameters
     * @param name
     * name of the node
     * @param isNintendo
     * whether the node is nintendo or not
     * @param isBroken
     * whether the node is broken or not
     */
    NetworkNode(String name,  boolean isNintendo, boolean isBroken)
    {
        this.name=name;
        this.isNintendo=isNintendo;
        this.isBroken=isBroken;

        if(isNintendo==true)
            children=null;

        else
        {
            children=new NetworkNode[maxChildren];
        }

    }

    /**
     * This is the accessor method for the name of the node
     * @return
     * the name of the node
     */

    public String getName() {
        return name;
    }

    /**
     * This is the accessor method for the parent of the node
     * @return
     * the parent node
     */

    public NetworkNode getParent() {
        return parent;
    }

    /**
     * This is the accessor method for the children array of this class
     * @return
     * the reference of the children array
     */

    public NetworkNode[] getChildren() {
        return children;
    }

    /**
     * This is the accessor method for the node at the specified index of the array
     * @param index
     * index
     * @return
     * node at the specified index
     */

    public NetworkNode getChildren(int index) {
        return children[index];
    }

    /**
     * This is an accessor method for whether the node is nintendo or not
     * @return
     * boolean value
     */

    public boolean isNintendo() {
        return isNintendo;
    }

    /**
     * This is an accessor method for whether the node is broken or not
     * @return
     * boolean value
     */
    public boolean isBroken() {
        return isBroken;
    }

    /**
     * This is a mutator method used to set whether node is broken or not
     * @param broken
     */
    public void setBroken(boolean broken) {
        isBroken = broken;
    }

    /**
     * This is a mutator method to set a node as the child at the specified index
     * @param child
     * node that has to be added into the array
     * @param index
     * position at which the node has to be added
     */
    public void setChildren(NetworkNode child,int index) {
         children[index]=child;
           }

    /**
     * This is a mutator method for the name of the node
     * @param name
     * name of the node
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This is a mutator method for the boolean of isNintendo
     * @param nintendo
     * boolean value
     */
    public void setNintendo(boolean nintendo) {
        isNintendo = nintendo;
    }

    /**
     * This is a mutator method for the parent of the node.
     * @param parent
     * parent of the node
     */
    public void setParent(NetworkNode parent) {
        this.parent = parent;
    }

    /**
     * This is the accessor method to keep a count of children in the array
     * @return
     * a count of children in an array
     */
    public int getCount() {
        return childrenCount;
    }

    /**
     * This is a mutator method for the count of the children in the array
     * @param count
     */
    public void setCount(int count) {
        this.childrenCount = count;
    }

    /**
     * This is a to string method which returns the name of the node
     * @return
     * the name of the node
     */
    public String toString()
    {
        return this.getName();
    }
}
