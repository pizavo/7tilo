# Problém nejkratší cesty

## Autoři
- Kateřina Šůstalová
- Vojtěch Píža

## Výsledky

```txt
Dijkstra's algorithm
[1]: PRG(0.00, 500.63) -> BRE(500.63, 827.53) -> WAW(1328.16, 1166.90) -> DME(2495.06, 2565.34) -> EVX(5060.40, 6662.77) -> DEL(11723.17, 0.00)
[2]: BRE(0.00, 827.53) -> WAW(827.53, 1166.90) -> DME(1994.43, 7502.07) -> HND(9496.50, 1553.67) -> OKA(11050.17, 1851.41) -> PEK(12901.58, 1464.84) -> CKG(14366.42, 2032.71) -> FNJ(16399.13, 0.00)
[3]: JFK(0.00, 1732.49) -> RSW(1732.49, 8518.50) -> WAW(10250.99, 1166.90) -> DME(11417.89, 2565.34) -> EVX(13983.23, 5835.67) -> PAK(19818.90, 3127.30) -> CAI(22946.20, 0.00)
[4]: DUB(0.00, 449.39) -> LHR(449.39, 1044.93) -> PRG(1494.32, 500.63) -> BRE(1994.95, 827.53) -> WAW(2822.48, 1166.90) -> DME(3989.38, 0.00)
[5]: OKA(0.00, 1553.67) -> HND(1553.67, 7502.07) -> DME(9055.74, 2565.34) -> EVX(11621.08, 0.00)

A* search
[1]: PRG(0.00, 500.63) -> BRE(500.63, 827.53) -> WAW(1328.16, 1166.90) -> DME(2495.06, 2565.34) -> EVX(5060.40, 6662.77) -> DEL(11723.17, 0.00)
[2]: BRE(0.00, 827.53) -> WAW(827.53, 1166.90) -> DME(1994.43, 7502.07) -> HND(9496.50, 1553.67) -> OKA(11050.17, 1851.41) -> PEK(12901.58, 1464.84) -> CKG(14366.42, 2032.71) -> FNJ(16399.13, 0.00)
[3]: JFK(0.00, 1732.49) -> RSW(1732.49, 8518.50) -> WAW(10250.99, 1166.90) -> DME(11417.89, 2565.34) -> EVX(13983.23, 5835.67) -> PAK(19818.90, 3127.30) -> CAI(22946.20, 0.00)
[4]: DUB(0.00, 449.39) -> LHR(449.39, 1044.93) -> PRG(1494.32, 500.63) -> BRE(1994.95, 827.53) -> WAW(2822.48, 1166.90) -> DME(3989.38, 0.00)
[5]: OKA(0.00, 1553.67) -> HND(1553.67, 7502.07) -> DME(9055.74, 2565.34) -> EVX(11621.08, 0.00)
```

### A* heuristika

Monetálně se využívá kombinovaná heuristika využívající
Direct Distance-Based Heuristic (vybírá přímé spojení, existuje-li),
Average Edge Weight Heuristic (pokouší se odhadnout vhodnou cestu na základě průměrné ceny hran) a
Centrality-Based Heuristic (vybírá cestu vedoucí přes vrcholy s nejvíce hranami - předpovídá rychlejší cestu).

Ve výsledku je to ale úplně jedno, ať jsme použili jakoukoli heuristiku stejně jsme vždy došli k výsledku stejnému
jako u Dijkstrova algoritmu (tedy jako A* s nulovou heuristikou). Bohužel nešlo bez koordinátů použít Euklidovskou heuristiku,
jedině v abstraktním prostoru, kde by koordinaty byly tvořeny náhodně, což nemá smysl + i v tomto případě vyšel stejný výsledek.