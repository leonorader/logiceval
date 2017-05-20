# logiceval
A logical expression parser and evaluator.

## what is this app?
Is is a simple command line tool to validate, parse, format and evaluate logical expressions. 

## how to use it?
It is simple again.
If you have the jar file, you can simply use command line tools to work with the app.

You have the following options:

- h or help (shows help) 
  
- x or expression (you can pass the expression as a value, if you add nothing else, a complete evaluation will be made)
  
  - v or validation (if you want to validate your expression)
      
  - p or parse or f or format (validates, parses and prints the expression to the command line)
   
  - e or evaluation (if you add nothing else, a complete evaluation will be made)
    
  - t or type (you can check what the given expression is)

    - tautology
              
    - contradiction
              
    - satisfiable
          
    - data (if you want to evaluate a concrete dataset)
          
      - d or data (add your values in this format: variablename:value;variablename:value;....)
            
- ef or expressionfile and df or datafile (you have to pass the path to the files in these parameters; use these if you want to evalute multiple expressions at the same time and you have a file filled up with these; the expressionfile should contain the expressions line by line and the datafile should contain the values in the following format: variablename:value;variablename:value;.... )
  
### examples

  if you would only like to format your expression simply add these:
  
     --expression "dog and cat and hedgehog" --format
     
  and you will get something like this:
  
      ( ( DOG AND CAT ) AND HEDGEHOG )
  
  or if you would like to evaluate an expression with your data:
  
    --expression "( A and B ) or ( C and D )" --evaluation --type "data" --data "A:1;B:1;C:0;D:1"
  
  then you will get:
  
      The result of the expression ( ( A AND B ) OR ( C AND D ) ) is TRUE.
  
  ## supported symbols:

- "0", "false" are used for false
- "1", "true" are used for true
- ">" and "implies" are used for implication
- "!" and "not" are used for NOT.
- "&", "^" and "and" are used for AND.
- "|", "v" and "or" are used for OR.
  
   
