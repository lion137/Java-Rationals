# Java-Rationals
Rational and bigrational numbers implementation in Java
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


