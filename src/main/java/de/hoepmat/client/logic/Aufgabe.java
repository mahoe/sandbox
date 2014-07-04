package de.hoepmat.client.logic;

import java.io.Serializable;
import java.util.Random;

public class Aufgabe {

    private static final Random random = new Random(System.currentTimeMillis());

    public enum OPERATOR{
        PLUS("+",new Execution()
        {
            public int execute(int operandLinks, int operandRechts)
            {
                return operandLinks + operandRechts;
            }
        }),MINUS("-", new Execution()
        {
            public int execute(int operandLinks, int operandRechts)
            {
                return operandLinks - operandRechts;
            }
        }),DIV("/", new Execution()
        {
            public int execute(int operandLinks, int operandRechts)
            {
                return operandLinks / operandRechts;
            }
        }),MUL("x", new Execution()
        {
            public int execute(int operandLinks, int operandRechts)
            {
                return operandLinks * operandRechts;
            }
        });

        private final String zeichen;
        private final Execution execution;
        private int rest = 0;

        OPERATOR(String zeichen, Execution execution){
            this.zeichen = zeichen;
            this.execution = execution;
        }

        public int execute(int operandLinks, int operandRechts){
            return execution.execute(operandLinks,operandRechts);
        }

        public String toString(){
            return zeichen;
        }

        interface Execution extends Serializable {
            int execute(int operandLinks, int operandRechts);
        }
    }

    private int opA;

    public String getOpA()
    {
        return Integer.toString(opA);
    }

    public String getOpB()
    {
        return Integer.toString(opB);
    }

    private OPERATOR op;

    private int opB;

    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append(getOpA()).append(op).append(getOpB()).append("=").append(getResultAsString());
        return result.toString();
    }

    private String getResultAsString()
    {
        return Integer.toString( op.execute(opA,opB));
    }

    public static Aufgabe get(){
        return get(OPERATOR.values());
    }

    public static Aufgabe get(int min, int max){
        return get(min,max,OPERATOR.values());
    }

    public static Aufgabe get(OPERATOR... operators){
        return get(-10,10,operators);
    }

    public static Aufgabe get(int min, int max, OPERATOR... operators){
        Aufgabe a = new Aufgabe();
        a.opA = getRandomNumber(min,max);
        a.opB = getRandomNumber(min,max);
        a.op = operators[getRandomNumber(0,operators.length -1)];
        return a;
    }

    public static final int getRandomNumber(int from, int to){
        if(to<from){
            throw new IllegalStateException("TO can not be less than FROM!");
        }

        final double aDouble = random.nextDouble();
        int multiplikator = to - from +1;

        int tmpNumber = (int) (aDouble * multiplikator);

        return from + tmpNumber;
    }

}
