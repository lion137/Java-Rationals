package rationals;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public class BigRational {
    private BigInteger num;
    private BigInteger den;
    //Constructors:
    public BigRational(long x, long y){
        if (y == 0) throw new ArithmeticException("BigRational: denumerator = 0");
        long g = gcd(x, y);
        num = BigInteger.valueOf(x/g);
        den = BigInteger.valueOf(y/g);
    }
    public BigRational(BigInteger x, BigInteger y){
        if (y.equals(BigInteger.ZERO)) throw new NumberFormatException("BigRational: denumerator = 0");
        BigInteger g = gcd(x.abs(), y.abs());
        num = x.divide(g);
        den = y.divide(g);
    }
    public BigRational(long x){
        num = BigInteger.valueOf(x);
        den = BigInteger.ONE;
    }
    public BigRational(String x, String y){
        BigInteger tmp1 = new BigInteger(x);
        BigInteger tmp2 = new BigInteger(y);
        BigInteger g = gcd(tmp1.abs(), tmp2.abs());
        num = tmp1.divide(g);
        den = tmp2.divide(g);
    }
    public BigRational(String str){
        if (!(str.contains("."))) {
            str = str.concat(".");
        }
        if (!(str.startsWith("-"))) {
            int l = str.indexOf(".");
            String base = str.substring(0, l);
            String rightStr = str.substring(l + 1);
            BigInteger left = new BigInteger(base);
            BigInteger right = new BigInteger(rightStr);
            if (right.equals(BigInteger.ZERO)) {
                num = left;
                den = BigInteger.ONE;
            } else {
                BigInteger leftLen = BigInteger.valueOf(10).pow(rightStr.length());
                BigInteger g = gcd(((left.multiply(leftLen)).add(right)).abs(), leftLen.abs());
                num = ((left.multiply(leftLen)).add(right)).divide(g);
                den = leftLen.divide(g);
            }
        }
        else {
            str = str.substring(1);
            int l = str.indexOf(".");
            String base = str.substring(0, l);
            String rightStr = str.substring(l + 1);
            BigInteger left = new BigInteger(base);
            BigInteger right = new BigInteger(rightStr);
            if (right.equals(BigInteger.ZERO)) {
                num = left;
                den = BigInteger.ONE;
            } else {
                BigInteger leftLen = BigInteger.valueOf(10).pow(rightStr.length());
                num = ((left.multiply(leftLen)).add(right)).negate();
                den = leftLen;
            }
        }
    }
    //Getters
    public BigInteger getNum(){return num;}
    public BigInteger getDen(){return den;}
    //Boleans
    public boolean equals(BigRational that){
        return (num.multiply(that.den).equals(den.multiply(that.num)));
    }
    public boolean equals(Rational that){
        //his.num * that.den == this.den * that.num
        return (num.multiply(BigInteger.valueOf(that.getDen())).equals(den.multiply(BigInteger.valueOf(that.getNum()))));
    }
    public boolean less(BigRational that){
        return ( num.multiply(that.den).compareTo(den.multiply(that.num)) < 0);
    }
    public boolean less(Rational that){
        return ( num.multiply(BigInteger.valueOf(that.getDen())).compareTo(den.multiply(BigInteger.valueOf(that.getNum()))) < 0);
    }
    public boolean greater(BigRational that){ return ( !(this.less(that) || this.equals(that)));}
    public boolean greater(Rational that){ return ( !(this.less(that) || this.equals(that)));}
    //Unary
    public BigRational minus(){return new BigRational(num.negate(), den);}
    public BigRational rev() {
        if (den.equals(BigInteger.ZERO)) throw new NumberFormatException("BigRational rev: reverse 0");
        return new BigRational(den, num);
    }
    //Binary
    public BigRational add(BigRational that){
        return new BigRational(this.num.multiply(that.getDen()).add(this.den.multiply(that.getNum())),
                that.getDen().multiply(den));
    }
    public BigRational add(Rational that){
        return new BigRational(this.num.multiply(BigInteger.valueOf(that.getDen())).add(this.den.multiply(BigInteger.valueOf(that.getNum()))),
                this.den.multiply(BigInteger.valueOf(that.getDen())));
    }
    public BigRational sub(BigRational that){return this.add(that.minus());}
    public BigRational sub(Rational that){return this.add(that.minus());}
    public BigRational mul(BigRational that){
        return new BigRational(num.multiply(that.getNum()), den.multiply(that.getDen()));
    }
    public BigRational mul(Rational that){
        return new BigRational(num.multiply(BigInteger.valueOf(that.getNum())),
                den.multiply(BigInteger.valueOf(that.getDen())));
    }
    public BigRational div(BigRational that) {return this.mul(that.rev());}
    public BigRational div(Rational that){return this.mul(that.rev());}
    //helpers and converts
    private long gcd(long x, long y) {
        if (y == 0) return x;
        else return gcd(y, x % y);
    }
    private BigInteger gcd(BigInteger x, BigInteger y) {
        if (y.equals(BigInteger.ZERO)) return x;
        else return gcd(y, x.mod(y));
    }
    private long pow(long a, long b) {
        if (b == 0) return 1;
        if (b == 1) return a;
        if (even(b)) return pow(a * a, b / 2);
        if (odd(b)) return (a * pow( a * a, (b - 1) / 2));
        else return 1;
    }
    private  boolean even(long a) {
        return a % 2 == 0;
    }
    private boolean odd(long a) {
        return !even(a);
    }
    public BigDecimal toDecimal(){ // Precision set to 128 digits, with the Half up rounding mode: https://docs.oracle.com/javase/7/docs/api/java/math/RoundingMode.html#HALF_UP
        BigDecimal tmp1 = new BigDecimal(num.toString());
        BigDecimal tmp2 = new BigDecimal(den.toString());
        return tmp1.divide(tmp2, new MathContext(128));
    }
    @Override
    public String toString() {
        if (den.equals(BigInteger.ONE)) return num +"";
        //if ( den.compareTo(BigInteger.ZERO ) > 0)  {return num + "/" + den;}
        return num + "/" + den;
    }
}
