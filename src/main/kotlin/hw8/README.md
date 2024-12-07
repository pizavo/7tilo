# Random-access Machine

## Autoři

- Kateřina Šůstalová
- Vojtěch Píža

## Popis

RAM pracuje s páskou, jejíž buňky jsou v řetězci odděleny pomocí `|` (případná podpora čísel > 9); na začátku a na konci
se `|` nepíší. Prádzná políčka jsou reprezentována symbolem `#`.
Prázdná páska je reprezentována jediným symbolem `#` - tedy prázdným políčkem. Na konec vstupní pásky se automaticky
přidává jedno prázdné políčko.

Podporované operace jsou:

| Instrukce | =i | i | *i |
|-----------|:--:|:-:|:--:|
| *READ*    |    | ✅ |    |
| *WRITE*   | ✅  | ✅ | ✅  |
| *LOAD*    | ✅  | ✅ |    |
| *STORE*   |    | ✅ | ✅  |
| *ADD*     | ✅  |   |    |
| *SUB*     | ✅  |   |    |
| *JMP*     |    | ✅ |    |
| *JZ*      |    | ✅ |    |
| *JGTZ*    |    | ✅ |    |
| *JH*      |    | ✅ |    |
| *HALT*    |    |   |    |

Sady programových instrukcí se zapisují do `.txt` a za čísla řádků se považují samotné řádky v rámci souboru.

## Výsledky

```txt
1. {1ⁿ 2ⁿ 3ⁿ | n ≥ 0}
Tape IN: 1|1|1|2|2|2|3|3|3|#
Tape OUT: 1

2. n
Tape IN: 1|2|3|4|5|#
Tape OUT: 5

3. reversed
Tape IN: 0|1|2|3|4|5|6|7|8|9|#
Tape OUT: 9|8|7|6|5|4|3|2|1|0

4. Σ = {0, 1, 2}, remove '1's
Tape IN: 0|0|1|1|2|2|1|1|0|2|0|1|2|1|0|#
Tape OUT: 0|0|2|2|0|2|0|2|0
```
