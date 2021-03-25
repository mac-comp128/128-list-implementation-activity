package listImplementation.exceptions;

public class IndexOutOfBoundsException extends RuntimeException
{
    /**
     * Sets up this exception with an appropriate message.
     * @param collection the name of the collection
     */
    public IndexOutOfBoundsException(String collection, int index)
    {
        super("The index " + index + " is out of bounds for this "+collection);
    }
}
