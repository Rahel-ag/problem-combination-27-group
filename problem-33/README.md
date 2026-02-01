# Problem 33: The Syntax Validator

## Project Description

This project is a command-line Java program that checks whether brackets in a given string are syntactically balanced.

## It validates the correct matching of:

Parentheses: ()

Square brackets: []

Curly braces: {}

## The program uses:

A custom stack implementation (no Java built-in Stack)

A token system to track both the bracket and its position

A command-based interface where users can enter commands interactively

## Features

Detects unexpected closing brackets

Detects mismatched brackets

Detects unclosed opening brackets

Reports exact error index

Supports continuous input until the user exits

## How It Works

The user enters commands in the terminal

Commands must follow this format:

```text
VALIDATE "<code>"
```


The program scans the string character by character

Opening brackets are pushed onto a stack

Closing brackets are matched against the stack

Errors are reported immediately, otherwise the input is declared valid

