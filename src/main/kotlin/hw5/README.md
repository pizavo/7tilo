# Procházení grafu

## Autoři

- Kateřina Šůstalová
- Vojtěch Píža

## Řešení

Samotné řešení je spustitelné pomocí souboru `Main.kt`.
Výběr nejvhodnějšího průchodu grafem je implmentován v souboru `Traversal.kt`.

### Postup

Graf je procházen tak, že jsou z uzlu, v němž se nacházíme, zmapovány všechny možné průchody grafem bez zpětného směru
se zastavením ve všech uzlech po cestě. Ze všech těchto průchodů je pak vybrán ten, jehož čistý zisk zdrojů je největší
(po odečtení nákladů ze zdrojů při případném nedostatku rozpočtu). Tento průchod je poté traverzován. Jakmile dojde
k poslednímu uzlu ve zmapovaném průchodu, je celý proces mapování průchodů a jejich traverzování opakován. Není-li již
možné najít další průchod, jehož čistý zisk by byl větší než nula, je procházení ukončeno.

### Zobrazení mapování

Pro zobrazení mapování průchodů je možné změnit proměnnou `debug` v souboru `Main.kt` na `true`.<br>
Při každém mapování tak dojde k zobrazení všech možných zmapovaných průchodů, jejich nákladů a hrubého zisku.

### Výsledek

```txt
r=485, z=0
Starting traversal

[0]   Lane<X>   (X),    Node<0>   (5)   -> r=485,   z=5
[1]   Lane<1>   (145),  Node<1>   (40)  -> r=340,   z=45
[2]   Lane<4>   (78),   Node<4>   (4)   -> r=262,   z=49
[3]   Lane<5>   (30),   Node<5>   (20)  -> r=232,   z=69
[4]   Lane<5>   (0),    Node<4>   (0)   -> r=232,   z=69
[5]   Lane<4>   (0),    Node<1>   (0)   -> r=232,   z=69
[6]   Lane<1>   (0),    Node<0>   (0)   -> r=232,   z=69
[7]   Lane<7>   (132),  Node<7>   (14)  -> r=100,   z=83
[8]   Lane<9>   (21),   Node<9>   (15)  -> r=79,    z=98
[9]   Lane<9>   (0),    Node<7>   (0)   -> r=79,    z=98
[10]  Lane<7>   (0),    Node<0>   (0)   -> r=79,    z=98
[11]  Lane<1>   (0),    Node<1>   (0)   -> r=79,    z=98
[12]  Lane<4>   (0),    Node<4>   (0)   -> r=79,    z=98
[13]  Lane<6>   (57),   Node<6>   (19)  -> r=22,    z=117
[14]  Lane<6>   (0),    Node<4>   (0)   -> r=22,    z=117
[15]  Lane<4>   (0),    Node<1>   (0)   -> r=22,    z=117
[16]  Lane<2>   (4),    Node<2>   (1)   -> r=18,    z=118

No more profitable paths available
r=18, z=118
```