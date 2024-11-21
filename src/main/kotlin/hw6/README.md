# Minimální kostra grafu

## Autoři
- Kateřina Šůstalová
- Vojtěch Píža

## Výsledky

Nejlépe se umístil Jarník, jehož algoritmus produkuje minimální kostru s hranami v takovém pořádí, aby při procházení
nedocházelo k vytváření izolovaných podgrafů, a to i přesto, že vybírá počáteční uzel zcela náhodně.<br>
(Jestli jsme tedy pochopili správně, že za nenavazující město je považováno takové město, jež není součástí dané podsítě,
v niž jsme zrovna stavěli, a musíme se tedy přesunout do jiné podsítě/vytvořit novou 😄)

```txt
[d_1] Mordheim -> Godric's Hollow: 1 hours, 1 km
[d_2] Lordaeron -> Tristram: 3 hours, 2 km
[d_3] Minas Tirith -> Rivia: 4 hours, 3 km
[d_4] Godric's Hollow -> LV-426: 4 hours, 3 km
[d_5] Tristram -> Minas Tirith: 5 hours, 4 km
[d_6] Ankh Morpork -> Mos Eisley: 5 hours, 4 km
[d_7] Mordheim -> Gondolin: 6 hours, 5 km
[d_8] Gondolin -> Ankh Morpork: 5 hours, 5 km
[d_9] Tristram -> Arrakeen: 8 hours, 7 km
[d_10] Rivia -> Solitude: 8 hours, 8 km
[d_11] Mordheim -> Minas Tirith: 8 hours, 7 km
[d_12] Mordheim -> Minas Tirith: 1 hours, 1 km
[d_13] Ankh Morpork -> Lannisport: 8 hours, 8 km
[d_14] Ankh Morpork -> Lannisport: 2 hours, 2 km
-------------------------------------
result: 14 days, 60 km

[d_1] Lordaeron -> Tristram: 2 hours, 2 km
[d_2] Tristram -> Minas Tirith: 4 hours, 4 km
[d_3] Minas Tirith -> Rivia: 3 hours, 3 km
[d_4] Tristram -> Arrakeen: 7 hours, 7 km
[d_5] Rivia -> Solitude: 8 hours, 8 km
[d_6] Minas Tirith -> Mordheim: 8 hours, 8 km
[d_7] Mordheim -> Godric's Hollow: 1 hours, 1 km
[d_8] Godric's Hollow -> LV-426: 3 hours, 3 km
[d_9] Mordheim -> Gondolin: 5 hours, 5 km
[d_10] Gondolin -> Ankh Morpork: 5 hours, 5 km
[d_11] Ankh Morpork -> Mos Eisley: 4 hours, 4 km
[d_12] Ankh Morpork -> Lannisport: 8 hours, 8 km
[d_13] Ankh Morpork -> Lannisport: 2 hours, 2 km
-------------------------------------
result: 13 days, 60 km

[d_1] Lordaeron -> Tristram: 2 hours, 2 km
[d_2] Tristram -> Arrakeen: 7 hours, 7 km
[d_3] Minas Tirith -> Rivia: 4 hours, 3 km
[d_4] Rivia -> Solitude: 8 hours, 8 km
[d_5] Lannisport -> Ankh Morpork: 8 hours, 7 km
[d_6] Lannisport -> Ankh Morpork: 3 hours, 3 km
[d_7] Ankh Morpork -> Mos Eisley: 4 hours, 4 km
[d_8] Mordheim -> Godric's Hollow: 2 hours, 1 km
[d_9] Mordheim -> Gondolin: 5 hours, 5 km
[d_10] Godric's Hollow -> LV-426: 3 hours, 3 km
[d_11] Tristram -> Minas Tirith: 5 hours, 4 km
[d_12] Ankh Morpork -> Gondolin: 6 hours, 5 km
[d_13] Minas Tirith -> Mordheim: 8 hours, 7 km
[d_14] Minas Tirith -> Mordheim: 1 hours, 1 km
-------------------------------------
result: 14 days, 60 km

1. Jarnik
2. Kruskal
3. Boruvka
```
