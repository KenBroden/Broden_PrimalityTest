# Primality Lab - Cryptography 4554

*Lab completed by Ken Broden*

## TODOs

- Implement Miller-Rabin test described in textbook.
- Set *s*(security parameter) to some initial number.
- Set a counter to count the number of candidates *a* that you tried before you can determine that your number is composite.
- Your number is prime if you have finished all *s* tests and did not find a counterexample.

### Helpful Java methods

- **modPow of BigInteger:** allows you to calculate a *very* large power of a *very* large number modulo another large number efficiently.  It reduces all intermediate results modulo a given N.
- A **BigInteger constructor** that allows you to generate a random BigInteger of a given bit length.
- A **BigInteger constructor** that generates a random prime integer.  You can use it for testing you primality tests, but not for the last task of your lab (finding the large primes).

### Testing

Test you method on numbers that you know to be prime (obtained from the prime number constructor) and on numbers that you know to be composite, such as product of two large primes, each half the length and a number that has a lot of small prime factors. You need to test it for both the length 512 and 1024 bits (approximately). Record the counters for all your test cases. Adjust s if needed.

### Finding large primes

Use each of the tests to generate two primes of the bit length 1024. Do not use the prime number constructor. Record the number of random candidates you tried, the number of candidates *a* for each random candidate, and the average number of BigInteger operations per candidate *a*.

#### Conclusions

Please see [RESULTS.md](RESULTS.md)
