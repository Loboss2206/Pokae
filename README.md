# Pokae Gardevoir GANG

Ce projet a été réalisé dans le cadre du cours SAE201 lors de mon deuxième semestre de Bachelor en Informatique. \
L'objectif de ce projet était de créer une simulation de combat Pokémon jouable dans la console en utilisant le langage de programmation Java. \
Ce projet est ainsi une reproduction simplifiée d'un combat Pokémon, où vous pourrez choisir de jouer entre différents types de joueurs, que ce soit des joueurs humains ou des IA (joueurs controlés par l'ordinateur).

## 💻 Instructions pour jouer une partie

Pour jouer un combat, suivez ces étapes simples :

### Joueur Humain

1. Ouvrez la classe principale du projet dans le package "src/testPokae".
2. Créez un objet DresseurHumain avec les commandes ci-dessous.

```java
DresseurHumain dresseurHumain1 = new DresseurHumain();
dresseurHumain1.setUpDresseur();
```

Répétez ces étapes pour créer un deuxième joueur humain, dresseurHumain2.

### Joueur IA

1. Ouvrez la classe principale du projet dans le package "src/testPokae".
2. Créez un objet DresseurIA avec les commandes ci-dessous.

```java
DresseurIA dresseurIA1 = new DresseurIA();
dresseurIA1.setUpDresseur();
```

Répétez ces étapes pour créer un deuxième joueur IA, dresseurIA2.

### Lancer le Combat

1. Créez un objet Combat en utilisant les joueurs que vous avez créés :

```java
Combat combatTest = new Combat(dresseurHumain1, dresseurHumain2);  // Pour un combat entre deux joueurs humains
// ou
Combat combatTest = new Combat(dresseurIA1, dresseurIA2);  // Pour un combat entre deux joueurs IA
// ou
Combat combatTest = new Combat(dresseurIA1, dresseurHumain1);  // Pour un combat entre un joueur IA et un joueur humain
```

2. Lancez le combat :

```java
combatTest.commence();
```

\
C'est aussi simple que ça ! Choisissez le type de combat que vous souhaitez simuler en ajustant les types de joueurs que vous créez.

## ✏️ Comment contribuer ?

Si vous êtes enthousiasmé par ce projet et souhaitez contribuer, veuillez consulter le [guide de contribution](https://github.com/Loboss2206/Pokae/blob/main/CONTRIBUTING.md) pour plus d'informations.

Ce projet a été créé avec passion par l'équipe Pokae Gardevoir GANG du département d'informatique de l'IUT de l'Université Côte d'Azur. Nous espérons que vous apprécierez jouer avec !
