# Gardevoir GANG

## Membres de l'équipe
  - Logan Brunet
  - Sacha Hassan
  - Lucas Schiavetti
  - Logan Laporte
  - Arthur Bailleul
 
## Organisation

Pour la réalisation de ce projet en groupe nous avons décidé de nous diviser les tâches pour être le plus performant possible et respecter les contraintes de temps imposées. En addition à cela nous nous aidions les uns les autres sur certaines tâches pour éviter que quelqu’un soit bloqué trop longtemps sur un problème. Ainsi Logan.b s’est principalement occupé du code, Lucas lui s’est occupé du code et de la doc, Sacha s’est occupé du code et de la JavaDoc, Logan.l s’est occupé du code et Arthur s’est occupé du code et des tests unitaires avec JUnit.

## Instructions


### Combat entre 2 humains
Pour jouer un combat entre 2 dresseurs, il faut créer 2 dresseursHumains comme ceci :

DresseurHumain dresseurHumain1 = new DresseurHumain();
dresseurHumain1.setUpDresseur();

DresseurHumain dresseurHumain2 = new DresseurHumain();
dresseurHumain2.setUpDresseur();


puis il faut créer le combat et le lancer : 

Combat combatTest = new Combat(dresseurHumain1,dresseurHumain2);
combatTest.commence();


### Combat entre 2 IA
Pour jouer un combat entre 2 IA, il faut créer 2 dresseursIA comme ceci :

DresseurIA dresseurIA1 = new DresseurIA();
dresseurIA1.setUpDresseur();

DresseurIA dresseurIA2 = new DresseurIA();
dresseurIA2.setUpDresseur();


puis il faut créer le combat et le lancer : 

Combat combatTest = new Combat(dresseurIA1,dresseurIA2);
combatTest.commence();


### Combat entre 1 IA et 1 humain
Pour jouer un combat entre 1 IA et 1 humain, il faut créer un dresseurHumain et un dresseurIA :

DresseurIA dresseurIA1 = new DresseurIA();
dresseurIA1.setUpDresseur();

DresseurHumain dresseurHumain1 = new DresseurHumain();
dresseurHumain1.setUpDresseur();


puis il faut créer le combat et le lancer : 

Combat combatTest = new Combat(dresseurIA1,dresseurHumain1);
combatTest.commence();

## [Lien Contributing](https://github.com/IUT-DEPT-INFO-UCA/pokae-gardevoir-gang/blob/main/CONTRIBUTING.md)

