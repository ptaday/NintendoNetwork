/**
 * @author Pushkar Taday
 * SBU ID:114375166
 * Recitation:04
 */

package Homework5;

/**
 * This class represent exception for all the occurrences of empty node.
 */

public class EmptyNodeException extends Exception {

    /**
     * This method is a parameterized constructor for this class.
     * @param str
     * string which is passed through the parameter
     */

    public EmptyNodeException(String str)
    {
        super(str);
    }
}
