In a room there are 10 people, none of whom are older than 60, but each of whom is at least 1 year old. Prove that one can always find two groups of people (with no common person) the sum of whose ages is the same.


## 10 People

2^10 - 1 = 1023 possible non-empty subsets of people.

60x10 - 1x10 = 590 possible values for these subsets.

Due to the Pigeon Hole Principle, 2 of the non-empty subsets must sum to the same value.


## 9 People

2^9 - 1 = 511 possible non-empty subsets of people.

60x9 - 1x9 = 531 possible values of these subsets (_Same logic as 10 no longer works_).

We can assume that no one in the room has the same age. If there were two with the same age, then those two would form a subset with the same sum.

This gives us a new min of 1+2+...+10 and max of 51+52+...+60

51+52+...+60 - 1+2+...+10 = 500 possible values of these subsets (_Now the logic from before works_).

Due to the Pigeon Hole Principle, 2 of the non-empty subsets must sum to the same value.


## 8 People

Impossible at max age of 60.

Needs 85 as max age -> [20, 40, 72, 78, 81, 83, 84, 85]

## 7 People

[1, 2, 4, 24, 40, 48, 56]

Needs 44 as max age -> [20, 31, 37, 40, 42, 43, 44]


## 6 People

Needs 24 as max age -> [11, 17, 20, 22, 23, 24]


## 5 People

Needs 13 as max age -> [3, 6, 11, 12, 13]


## 4 People

Needs 7 as max age -> [3, 5, 6, 7]
