# Identificación de Mutantes

### Utilice un modelo basado en Teoría de Grafos utilizando una Matriz de Adjacencia para mejorar el performance al momento de realizar las combinaciones de matrices de NxN. Permitiendo así tener tiempos de OlogN independientemente del tamaño de los datos de entrada.

Se pueden indentificar patrones de bases nigrogenadas de ADN de acuerdo a las siguientes matrices (Vertical, Horizontal y Diagonalmente)

```
[0, 0, 0, 0, 1, 0], 
[0, 0, 0, 0, 1, 0], 
[0, 0, 0, 0, 1, 0], 
[0, 0, 1, 1, 1, 1], 
[1, 1, 1, 1, 0, 0], 
[0, 0, 0, 0, 0, 0]

[0, 0, 1, 0, 1, 0], 
[0, 0, 1, 0, 1, 0], 
[0, 0, 1, 0, 1, 0], 
[0, 0, 1, 1, 1, 1], 
[1, 1, 1, 1, 0, 0], 
[0, 1, 0, 1, 0, 0]]

[1, 0, 1, 0, 1, 1], 
[0, 1, 1, 0, 1, 0], 
[0, 0, 1, 0, 1, 1], 
[1, 0, 1, 1, 1, 1], 
[1, 1, 1, 0, 0, 1], 
[1, 1, 1, 1, 0, 0]]
```

##El servicio se encuentra Hosteado en Heroku bajo la siguiente URL 
```
https://mutantes.herokuapp.com/validate/
```

##Las solicitudes se deben de hacer por POST por ejemplo: 
```
Vertical 

curl -X POST -H "Content-Type: application/json" -d '{"dna": "[['C', 'A', 'G', 'C', 'C', 'A'],['C', 'C', 'G', 'T', 'G', 'C'],['C', 'T', 'C', 'T', 'T', 'T'],['C', 'G', 'A', 'C', 'G', 'G'],['G', 'C', 'G', 'T', 'C', 'A'],['T', 'C', 'A', 'C', 'T', 'G']]"}' https://mutantes.herokuapp.com/validate/

Horizontal 

curl -X POST -H "Content-Type: application/json" -d '{"dna": "[['G', 'G', 'G', 'G', 'C', 'A'],['C', 'C', 'G', 'T', 'G', 'C'],['C', 'T', 'C', 'T', 'T', 'T'],['C', 'G', 'A', 'C', 'G', 'G'],['G', 'C', 'G', 'T', 'C', 'A'],['T', 'C', 'A', 'C', 'T', 'G']]"}' https://mutantes.herokuapp.com/validate/
Diagonal

curl -X POST -H "Content-Type: application/json" -d '{"dna": "[['A', 'A', 'G', 'C', 'C', 'A'],['C', 'A', 'G', 'T', 'G', 'C'],['T', 'T', 'A', 'T', 'T', 'T'],['A', 'G', 'A', 'A', 'G', 'G'],['G', 'C', 'G', 'T', 'C', 'A'],['T', 'C', 'A', 'C', 'T', 'G']]"}' https://mutantes.herokuapp.com/validate/

```
####El servicio devuelve el mismo objeto enviado, la matriz de calculo y un par de llave valor de nombre "kind" indicando si es un MUTANTE, un posible HUMANO ó ERROR DE ENTRADA
```
{"dna":"[
        [C, A, G, C, C, A],
        [C, C, G, T, G, C],
        [C, T, C, T, T, T],
        [C, G, A, C, G, G],
        [G, C, G, T, C, A],
        [T, C, A, C, T, G]
        ]",
  "data":[["C","A","G","C","C","A"],["C","C","G","T","G","C"],["C","T","C","T","T","T"],["C","G","A","C","G","G"],["G","C","G","T","C","A"],["T","C","A","C","T","G"]],
  "kind":"MUTANTE"}
```

