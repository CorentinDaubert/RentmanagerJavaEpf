# RentmanagerJavaEpf
rendu du TD Java Epf 2022

Corentin DAUBERT
corentin.daubert@epfedu.fr
11/03/2022

Les Difficultée rencontrées :
- les mocks et les testes : j'ai eu beaucoup de mal a les réaliser,  je ne les ai réaliser que sur les Clients (par faute de temps). De plus je n'ai pu réalisé que 3 testes : 
  • est ce que le client est mineur
  • est ce que le nom fait au moins 3 caractère
  • est ce que le prénom fait au moins 3 caractère
 
 - De plus je n'ai pas réussi à envoyer un message d'erreur lorsque l'utilisateur ne réussi pas un test (par exmple si celui-ci à moins de 18 ans)
 
 - Je n'ai pas aussi réalisé les fonctions éditer et détails par faute de temps

Commande pour lancer le site :
mvn tomcat7:run
