package com.luv2code.demo.rest;

/*
* RuntimeException is the superclass for those
* exceptions that can be thrown in the normal operation
* of the JVM.
*
* This and its subclasses are unchecked exceptions.
* */
public class StudentNotFoundException extends RuntimeException
{
    // This class is calling the parent constructor with super();

    public StudentNotFoundException (String message)
    {
        super(message);
    }

    public StudentNotFoundException(String message, Throwable cause)
    {
        super(message, cause);
    }
    public StudentNotFoundException(Throwable cause)
    {
        super(cause);
    }
}
