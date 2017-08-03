package rationals;


import java.math.BigInteger;

public class Rational {
    private long num;
    private long den;
    public Rational(long x, long y){
        if (y == 0) throw new ArithmeticException("Rational: denumerator = 0");
        long g = gcd(x, y);
        num = x/g;
        den = y/g;
    }
    public Rational(long x){
        num = x;
        den = 1;
    }
    public Rational(String str){
            //String str = String.valueOf(x);
            if (!(str.contains("."))) {
                str = str.concat(".");
            }
        if (!(str.startsWith("-"))) {
            int l = str.indexOf(".");
            String base = str.substring(0, l);
            String rightStr = str.substring(l + 1);
            long left = Long.valueOf(base);
            long right = Long.valueOf(rightStr);
            if (right == 0) {
                num = left;
                den = 1;
            } else {
                long leftLen = pow(10, rightStr.length());
                num = left * leftLen + right;
                den = leftLen;
            }
        }
        else {
            str = str.replaceFirst("-", "");
            int l = str.indexOf(".");
            String base = str.substring(0, l);
            String rightStr = str.substring(l + 1);
            long left = Long.valueOf(base);
            long right = Long.valueOf(rightStr);
            if (right == 0) {
                num = left;
                den = 1;
            } else {
                long leftLen = pow(10, rightStr.length());
                num = -(left * leftLen + right);
                den = leftLen;
            }

        }
    }
    public long getNum(){return num;}
    public long getDen(){return den;}
    public boolean equals(Rational that) {
        return (this.num * that.den == this.den * that.num);
    }
    public boolean equals(BigRational that){
        return (BigInteger.valueOf(num).multiply(that.getDen()).equals(BigInteger.valueOf(den).multiply(that.getNum())));
    }
    public boolean less(Rational that){
        return (this.num * that.den < that.num * this.den);
    }
    public boolean less(BigRational that){
        return ( (BigInteger.valueOf(this.num).multiply(that.getDen())).compareTo(that.getNum().multiply(BigInteger.valueOf(den))) < 0);
    }
    public boolean greater(Rational that) {
        return (!(this.less(that) || this.equals(that)));
    }
    public boolean greater(BigRational that){return (!(this.less(that) || this.equals(that)));}
    public Rational minus(){
        return (new Rational(-num, den));
    }
    public Rational rev(){
        if (num == 0) throw new NumberFormatException("Rational rev: reverse zero");
        return (new Rational(den, num));
    }
    public Rational add(Rational that){
        return (new Rational(this.num * that.getDen() + that.getNum() * this.den, that.getDen() * this.den));
    }
    public BigRational add(BigRational that){
        return new BigRational(BigInteger.valueOf(num).multiply(that.getDen()).add(that.getNum().multiply(BigInteger.valueOf(this.den))),
                that.getDen().multiply(BigInteger.valueOf(this.den)));
    }
    public Rational sub(Rational that){
        return (this.add(that.minus()));
    }
    public BigRational sub(BigRational that){ return (this.add(that.minus()));
    }
    public Rational mul(Rational that){
        return (new Rational(this.num * that.num, this.den * that.den));
    }
    public BigRational mul(BigRational that){ return that.mul(this);
    }
    public Rational div(Rational that) {
        return (this.mul(that.rev()));
    }
    public BigRational div(BigRational that) {return this.mul(that.rev());}

    private long gcd(long x, long y) {
        if (y == 0) return x;
        else return gcd(y, x % y);
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
    public double toDecimal(){
        double x = 1;
        return (x * num) / den;
    }
    @Override
    public String toString() {
        if (den == 1) return num + "";
        if ( den < 0 && num >= 0) {num = -num;den = -den;return num + "/" + den;}
        if ( den >= 0 && num < 0) {return num + "/" + den;}
        return num + "/" + den;
    }
}
