package de.hoepmat.client.logic;

import junit.framework.Assert;
import org.junit.Test;

public class AufgabeTest
{

    @Test
    public void testproduceARandomOperand()
    {
        for (int i = 0; i < 100000; i++)
        {
            final int randomNumber = Aufgabe.getRandomNumber(-10, 10);
            System.out.println(randomNumber);
            Assert.assertTrue("Die Zufallszahl " + randomNumber + " ist ausserhalb des erlaubten Bereiches!",randomNumber >= -10 && randomNumber <= 10);
        }
    }

    @Test
    public void generateValidAufgabe(){
        for (int i = 0; i < 10; i++)
        {
            final Aufgabe aufgabe = Aufgabe.get();
            System.out.println("Aufgabe: " + aufgabe);
        }
    }

    @Test
    public void generateValidAufgabeMitEingeschraenktenOperatoren(){
        for (int i = 0; i < 10; i++)
        {
            final Aufgabe aufgabe = Aufgabe.get(Aufgabe.OPERATOR.PLUS, Aufgabe.OPERATOR.MINUS);
            System.out.println("Aufgabe: " + aufgabe);
        }
    }

    @Test
    public void generateValidAufgabeMitEingeschraenktenOperatorenUndPositivemWerteBereich(){
        for (int i = 0; i < 10; i++)
        {
            final Aufgabe aufgabe = Aufgabe.get(0,20,Aufgabe.OPERATOR.PLUS, Aufgabe.OPERATOR.MINUS);
            System.out.println("Aufgabe: " + aufgabe);
        }
    }
}
