# Infix to Postfix and Prefix Pseudocode

## 1. Problem Description
This pseudocode shows how an infix expression is converted to postfix and prefix using a stack.

---

## 2. Pseudocode
```text
BEGIN

DO
    DISPLAY "===== MENU ====="
    DISPLAY "1. Infix to Postfix"
    DISPLAY "2. Infix to Prefix"
    DISPLAY "3. Exit"

    INPUT choice

    IF choice == 3 THEN
        EXIT PROGRAM
    ENDIF

    INPUT infix_expression

    // ==============================
    // CASE 1: INFIX TO POSTFIX
    // ==============================
    IF choice == 1 THEN

        INITIALIZE empty stack S
        INITIALIZE empty result R

        FOR each character char in infix_expression DO

            IF char is operand THEN
                ADD char to R

            ELSE IF char == '(' THEN
                PUSH char onto S

            ELSE IF char == ')' THEN
                WHILE top of S is not '(' DO
                    POP from S and ADD to R
                END WHILE
                POP '(' from S

            ELSE IF char is operator THEN
                WHILE S is not empty AND
                      precedence(char) <= precedence(top of S) DO
                    POP from S and ADD to R
                END WHILE
                PUSH char onto S
            ENDIF

        END FOR

        WHILE S is not empty DO
            POP from S and ADD to R
        END WHILE

        DISPLAY "Postfix Expression: ", R

    // ==============================
    // CASE 2: INFIX TO PREFIX
    // ==============================
    ELSE IF choice == 2 THEN

        REVERSE infix_expression → rev_exp

        FOR each character char in rev_exp DO
            IF char == '(' THEN
                REPLACE with ')'
            ELSE IF char == ')' THEN
                REPLACE with '('
            ENDIF
        END FOR

        INITIALIZE empty stack S
        INITIALIZE empty result R

        FOR each character char in rev_exp DO

            IF char is operand THEN
                ADD char to R

            ELSE IF char == '(' THEN
                PUSH char onto S

            ELSE IF char == ')' THEN
                WHILE top of S is not '(' DO
                    POP from S and ADD to R
                END WHILE
                POP '(' from S

            ELSE IF char is operator THEN
                WHILE S is not empty AND
                      precedence(char) <= precedence(top of S) DO
                    POP from S and ADD to R
                END WHILE
                PUSH char onto S
            ENDIF

        END FOR

        WHILE S is not empty DO
            POP from S and ADD to R
        END WHILE

        REVERSE R → prefix_result

        DISPLAY "Prefix Expression: ", prefix_result

    ENDIF

WHILE choice != 3

END
