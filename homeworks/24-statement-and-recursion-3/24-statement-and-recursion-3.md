# [Homework 24: Statement and Recursion 3][hw24]

- **Andrew Bilyeu**: <!-- TODO: fill with first and last name (e.g., Brutus Buckeye) -->
- **bilyeu.14**: <!-- TODO: fill with OSU dot number (e.g., buckeye.17) -->
- **10/29/26 @ 1:50pm EST**: <!-- TODO: fill out with due date and time (e.g., 10/17 @ 3:10 PM EST) -->

## Preparation

Previous students would have wanted you to know the following
before you get started (based on 4 reviews):

- Estimated time to complete the assignment: 0.77 hours
- Most common emotion before starting the assignment: Hope (1 review)
- Most common emotion while completing the assignment: Enjoyment & Anger (1 review each)
- Most common emotion after completing the assignment: Rage (1 review)

If the information above is incomplete, you can help by [providing
your own feedback][feedback-form] after completing this assignment.

## Problems

**This homework is necessary preparation for the lab.** Make sure you
type your answers in files you bring to the lab so that you will not
have to waste time entering your code during the lab.

### Problem 1

> Using recursion, complete the body of the following Statement
> instance method.

```java
/**
 * Pretty prints {@code this} to the given stream {@code out} {@code offset}
 * spaces from the left margin using
 * {@link components.program.Program#INDENT_SIZE Program.INDENT_SIZE} spaces
 * for each indentation level.
 *
 * @param out
 *            the output stream
 * @param offset
 *            the number of spaces to be placed before every nonempty line
 *            of output; nonempty lines of output that are indented further
 *            will, of course, continue with even more spaces
 * @updates out.content
 * @requires out.is_open and 0 <= offset
 * @ensures <pre>
 * out.content =
 *   #out.content * [this pretty printed offset spaces from the left margin
 *                   using Program.INDENT_SIZE spaces for indentation]
 * </pre>
 */
    public void prettyPrint(SimpleWriter out, int offset) {
        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";
        assert offset >= 0 : "Violation of: 0 <= offset";

        switch (this.kind()) {
            case BLOCK: {
                int len = this.lengthOfBlock();
                for (int i = 0; i < len; i++) {
                    Statement c = this.removeFromBlock(i);
                    c.prettyPrint(out, offset);
                    this.addToBlock(i, c);
                }

                break;
            }
            case IF: {

                Statement c = this.newInstance();
                Statement p = this.disassembleIf(c);

                out.print(out, offset);

                out.println("IF " + toStringCondition(p));
                c.prettyPrint(out, offset + INDENT_SIZE);

                for (int i = 0; i < offset; i++) {
                    out.print(" ");
                }
                out.println("END IF");
                this.assembleIf(p, c);

                break;
            }
            case IF_ELSE: {

                Statement ifS = this.newInstance();
                Statement elseS = this.newInstance();
                Statement p = this.disassembleIfElse(ifS, elseS);

                out.print(out, offset);

                out.println("IF " + toStringCondition(p) + "THEN");
                ifS.prettyPrint(out, offset + INDENT_SIZE);
                printSpaces(out, offset);
                out.println("ELSE");
                elseS.prettyPrint(out, offset + INDENT_SIZE);

                printSpaces(out, offset);
                out.println("END IF");
                this.assembleIfElse(p, ifS, elseS);


                break;
            }
            case WHILE: {

                Statement c = this.newInstance();
                Statement p = this.disassembleWhile(c);

                out.print(out, offset);
                out.println("WHILE " + toStringCondition(p) + "DO");
                c.prettyPrint(out, offset + INDENT_SIZE);
                printSpaces(out, offset);
                out.println("END WHILE");

                this.assembleWhile(p, c);

                break;
            }
            case CALL: {

                String call = this.disassembleCall();
                printSpaces(out, offset);
                out.println(call);
                this.assembleCall(call);

                break;
            }
            default: {
                // this will never happen...
                break;
            }
        }
    }

```

> Here is an example of what prettyPrint should output
> (with offset 0 from the left edge of the page):

```bl
IF next-is-not-enemy THEN
    turnleft
    WHILE true DO
        IF random THEN
            move
            turnback
            IF next-is-not-wall THEN
                move
            END IF
            infect
            move
        ELSE
            go-for-it
            WHILE next-is-empty DO
            END WHILE
            turnleft
            turnright
        END IF
    END WHILE
    turnright
    skip
    skip-again
END IF
```

> In lab you will be given implementations for the following
> two static methods needed to implement prettyPrint.

```java
/**
 * Prints the given number of spaces to the given output stream.
 *
 * @param out
 *            the output stream
 * @param numSpaces
 *            the number of spaces to print
 * @updates out.content
 * @requires out.is_open  and  spaces >= 0
 * @ensures out.content = #out.content * [numSpaces spaces]
 */
private static void printSpaces(SimpleWriter out, int numSpaces) {

    for (int i = 0; i < numSpaces; i++) {
        out.print(' ');
    }
}
```

```java
/**
 * Converts c into the corresponding BL condition.
 *
 * @param c
 *            the Condition to convert
 * @return the BL condition corresponding to c
 * @ensures toStringCondition = [BL condition corresponding to c]
 */
private static String toStringCondition(Condition c) {
    String result;
    switch (c) {
        case NEXT_IS_EMPTY: {
            result = "next-is-empty";
            break;
        }
        case NEXT_IS_NOT_EMPTY: {
            result = "next-is-not-empty";
            break;
        }
        case NEXT_IS_ENEMY: {
            result = "next-is-enemy";
            break;
        }
        case NEXT_IS_NOT_ENEMY: {
            result = "next-is-not-enemy";
            break;
        }
        case NEXT_IS_FRIEND: {
            result = "next-is-friend";
            break;
        }
        case NEXT_IS_NOT_FRIEND: {
            result = "next-is-not-friend";
            break;
        }
        case NEXT_IS_WALL: {
            result = "next-is-wall";
            break;
        }
        case NEXT_IS_NOT_WALL: {
            result = "next-is-not-wall";
            break;
        }
        case RANDOM: {
            result = "random";
            break;
        }
        case TRUE: {
            result = "true";
            break;
        }
        default: {
            // this will never happen...
            result = "";
            break;
        }
    }
    return result;
}
```

## Submission

If you have completed the assignment using this template, VS Code should
automatically convert the template to a PDF on save. If you're not automatically
getting a PDF, please reach out to the instructor. If you're in a rush to
submit, you may use one of the alternative strategies described in this
[Markdown to PDF guide][markdown-to-pdf-guide]. You may also consider printing
the raw markdown directly. However, do not make a habit of this as the graders
reserve the right to give a zero.

[hw24]: https://cse22x1.engineering.osu.edu/2231/assignments/homeworks/statement3.html
[feedback-form]: https://forms.gle/qJ1gEM5N1r6X7Poy5
[markdown-to-pdf-guide]: https://therenegadecoder.com/blog/how-to-convert-markdown-to-a-pdf-3-quick-solutions/
