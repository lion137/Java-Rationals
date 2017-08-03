# Java-Rationals
copyleft by lion137    

Read also: https://lion137.blogspot.co.uk/2017/08/java-rational.html    

Rational and bigrational numbers implementation in Java.    

Constructors:    
Rational r1 = new Rational(<long, long> | <long> | <String>)    
As a string, for example "1.99999011", can be used   
Methods supported:
Boolean:    
- equals;    
- less;    
- greater;    
Unary:    
- unary minus (minus);    
- reverse (rev) - return reveresed rational/bigrational.    
Binary:
- add;    
- sub;    
- mul;    
- div;    
The all unary methods are overloaded - can be mixed beetwen rationals and big rationals.   
 
The BigRational class has additional constructors  (<BigInteger, BigInteger> | <String, String>), obviously, to     
allow to create big number.    
There are toDecimal methods, to print Rational (and BigRational) as a decimal; it prints exact result if possible or     
rounds to 128 bits in the case of infinite expansion.


