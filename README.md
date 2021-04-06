# Gomoku #
<br>
## Répartition entre les classes ##
<br>
Notre méthode <strong>main()</strong> se trouve dans la classe <strong>Jeu</strong>. 
Cette classe permet de créer une instance de la classe partie depuis la méthode <strong>menuPrincipal()</strong>  Une partie est constituée de deux joueurs et d'un plateau, entre autres attributs nécessaires au déroulement d'une partie.
<br>
<strong>Joueur</strong> est une classe abstraite, mère de deux classes : 
<strong>JoueurHumain</strong> et <strong>JoueurIA</strong>, ayant en commun la méthode abstraite <strong>jouer()</strong>. <strong>JoueurIA</strong> génère des coups dans cette méthode, qu'il va envoyer dans la classe partie. <strong>JoueurHumain</strong> saisit lui-même les coups qu'il veut jouer. 
<br>
Un plateau est constitué, entre autres, d'un double tableau de <strong>Case</strong>. Une case a plusieurs propriétés, dont des coordonnées. Une coordonnée est l'association d'une ligne et d'une colonne. Cette classe est nécessaire pour, notamment, trouver des coordonnées voisines avec l'aide de la classe direction.
<br>
## Choix effectués ##
<br>
Pour effectuer ce programme, nous nous sommes inspirés des TP's que nous avons réalisés (le menu, par exemple, vient du TP bibliothèque) et du projet Towa (coordonnées, case...). 
Nous avons tenté de réaliser un code cohérent en utilisant les notions apprises en cours : les classes abstraites, les List, la récursivité, et évidemment les tests unitaires.
<br> 
Nous n'avons pas créé de collection. En effet, bien que nous voulions appliquer toutes les nouvelles notions apprises, nous n'avons pas trouvé d'utilité à utiliser une collection dans cette situation.
  
<br> <br> 
***Projet réalisé par Mathieu Rakotoarisoa et Ayman Kachmar S2A***


