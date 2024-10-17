package com.swallows.additionalapi.crafttweaker.util;

import crafttweaker.annotations.ZenRegister;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.math.BigInteger;

@ZenRegister
@ZenClass("mods.additionalapi.BigInteger")
@SuppressWarnings("unused")
public class SaaBigInteger {

    @ZenMethod
    public  static String BigIntegerAdd (String a, String b) {
        if(!a.matches("-?\\d+") || !b.matches("-?\\d+")){
            return null;
        }
        BigInteger A = new BigInteger(a);
        BigInteger B = new BigInteger(b);
        return A.add(B).toString();
    }

    @ZenMethod
    public  static String BigIntegerSubtract (String a, String b) {
        if(!a.matches("-?\\d+") || !b.matches("-?\\d+")){
            return null;
        }
        BigInteger A = new BigInteger(a);
        BigInteger B = new BigInteger(b);
        return A.subtract(B).toString();
    }

    @ZenMethod
    public  static String BigIntegerMultiply (String a, String b) {
        if(!a.matches("-?\\d+") || !b.matches("-?\\d+")){
            return null;
        }
        BigInteger A = new BigInteger(a);
        BigInteger B = new BigInteger(b);
        return A.multiply(B).toString();
    }

    @ZenMethod
    public  static String BigIntegerDivide (String a, String b) {
        if(!a.matches("-?\\d+") || !b.matches("-?\\d+")){
            return null;
        }
        BigInteger A = new BigInteger(a);
        BigInteger B = new BigInteger(b);
        return A.divide(B).toString();
    }

    @ZenMethod
    public  static String BigIntegerRemainder (String a, String b) {
        if(!a.matches("-?\\d+") || !b.matches("-?\\d+")){
            return null;
        }
        BigInteger A = new BigInteger(a);
        BigInteger B = new BigInteger(b);
        return A.remainder(B).toString();
    }

    @ZenMethod
    public  static String BigIntegerPow (String a, int b) {
        if(!a.matches("-?\\d+")){
            return null;
        }
        BigInteger A = new BigInteger(a);
        return A.pow(b).toString();
    }

    @ZenMethod
    public  static String BigIntegerNegate (String a) {
        if(!a.matches("-?\\d+")){
            return null;
        }
        BigInteger A = new BigInteger(a);
        return A.negate().toString();
    }

    @ZenMethod
    public  static int BigIntegerCompareTo (String a, String b) {
        if(!a.matches("-?\\d+") || !b.matches("-?\\d+")){
            return -2;
        }
        BigInteger A = new BigInteger(a);
        BigInteger B = new BigInteger(b);
        return A.compareTo(B);
    }

    @ZenMethod
    public  static String BigIntegerMin (String a, String b) {
        if(!a.matches("-?\\d+") || !b.matches("-?\\d+")){
            return null;
        }
        BigInteger A = new BigInteger(a);
        BigInteger B = new BigInteger(b);
        return A.min(B).toString();
    }

    @ZenMethod
    public  static String BigIntegerMax (String a, String b) {
        if(!a.matches("-?\\d+") || !b.matches("-?\\d+")){
            return null;
        }
        BigInteger A = new BigInteger(a);
        BigInteger B = new BigInteger(b);
        return A.max(B).toString();
    }

    @ZenMethod
    public  static String toString (int a) {
        return String.valueOf(a);
    }

    @ZenMethod
    public  static String toString (long a) {
        return String.valueOf(a);
    }
}
