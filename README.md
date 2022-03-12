# Bs parser

See https://www.youtube.com/watch?v=vcFBwt1nu2U&t=3178s

### "Features":

1. All variable names must start with €
2. Code blocks are defined by indentation
    1. 1 level of indentation: 2 spaces
    2. 4 spaces -> 1 tab  
       ex: 3 levels of indentation = 1 tab + 2 spaces
3. Odd number of spaces and tab (combined) are comments
4. Multiline coments start with 1 tab + 1 space and end with 1 space and 1 tab
5. Has `null`
6. `if` statement doesn't exit, we use `unless` statement instead
    - _value_ `unless` _not_condition_
7. The `unless` statement ends with a greek question mark ; (which is not a semi-colon)
8. There's only one exception, and it is `HALT_AND_CATCH_FIRE` (yes, it is a reserved keyword)
9. Inference equality (like javascript)
    - `0 = '0'` is false
    - `0 == ''` is true
    - `0 == '0'` is true
    - `0 == 'Zero'` is true
    - `22/7 == 🥧` is true
    - `undefined !=! null` is true
10. strict equality
    - If you wish for strict inequality, put a bang! after the equal, cause it is an important check!
    - ex: `1 != '1'` is false but `1 !=! '1'` is true
    - If you wish for strict _equality_, inverse the result of the strict inequality with a band ! before it!
    - ex: `1 == '1'` is true but `1 !!=! '1'` is false
11. Strings
    - 'Hello' // ASCII
    - ''Hello''          // ANSI
    - "Hello"            // DBSC
    - ""Hello""          // EBCDIC
    - «Hello» // UTF-256
    - ««Hello ${name}»» // UTF-256 + interpolation
12. Arrays
    - Arrays indices start at -1 and go down