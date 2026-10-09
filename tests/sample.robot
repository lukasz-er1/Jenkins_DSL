*** Settings ***
Documentation    Przykładowe testy: część przechodzi, część celowo failuje.

*** Test Cases ***
Addition Works
    ${sum}=    Evaluate    2 + 2
    Should Be Equal As Integers    ${sum}    4

String Contains Word
    Should Contain    DISH OnStream    OnStream

List Has Three Items
    ${items}=    Create List    a    b    c
    Length Should Be    ${items}    3

Addition Is Wrong (Expected Fail)
    ${sum}=    Evaluate    2 + 2
    Should Be Equal As Integers    ${sum}    5

Missing Word (Expected Fail)
    Should Contain    DISH OnStream    Sagemcom

Explicit Failure (Expected Fail)
    Fail    Celowy błąd do sprawdzenia raportu