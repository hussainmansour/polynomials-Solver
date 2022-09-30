proplem statment:-
--------------------

You are required to design a linked allocation system to represent and manipulate polynomials.
You should use one of the linked list classes you implemented in part (A).
Each term of the polynomial will be represented as a node, using it’s coefficient and exponent.
Assume that you have 3 available polynomial variables: A, B and C, that can be set by the
user and one variable R that acts as an accumulator for the results of operations on other
polynomials. You should order the polynomial terms in descending order by the exponent.
A polynomial can have a negative exponent.

Create a user-friendly, menu-driven system that performs the following operations:-
 
?  Read in a polynomial and store it in variable A, B, or C.

?  Output a polynomial using a form that clearly displays it.

?  Add two polynomials and store the result in R.

?  Subtract two polynomials and store the result in R.

?  Multiply two polynomials and store the result in R.

?  Evaluate a polynomial at some point, a, where a is a floating point constant. In other 
words, substitute by the given value in your polynomial. Display the result as a floating point. 

?  Clear a polynomial. Note that: a polynomial whose value is cleared or initially unset
cannot be involved in an operation.The core of the application should throw a runtime exception when it encounter any invalid input
or operation.