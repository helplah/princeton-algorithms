# Programming Assignment 2: Randomized Queues and Deques #
Write a generic data type for a deque and a randomized queue. 
The goal of this assignment is to implement elementary data structures using arrays and linked lists, 
and to introduce you to generics and iterators.

**Assignments**: http://coursera.cs.princeton.edu/algs4/assignments/queues.html

**Checklists**: http://coursera.cs.princeton.edu/algs4/checklists/queues.html

**Discussions**: https://www.coursera.org/learn/algorithms-part1/discussions/weeks/2

## Dequeue ##
A double-ended queue or deque (pronounced "deck") is a generalization of a stack and a queue that 
supports inserting and removing items from either the front or the back of the data structure. 

## Randomized Queue ##
A randomized queue is similar to a stack or queue, except that the item removed is chosen uniformly at random 
from items in the data structure. Create a generic data type *RandomizedQueue* that implements the iterator.

Each *iterator* must return the items in uniformly random order. 
The order of two or more iterators to the same randomized queue must be mutually independent; 
each iterator must maintain its own random order.

## Permutation ##
Write a client program Permutation.java that takes an integer k as a command-line argument; 
reads in a sequence of strings from standard input using StdIn.readString(); 
and prints exactly k of them, uniformly at random. Print each item from the sequence at most once.
