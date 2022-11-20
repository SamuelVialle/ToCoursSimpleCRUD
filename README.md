# Les opérations CRUD sur Firestore

L'acronyme informatique anglais CRUD, pour Create, Read, Update, Delete, désigne les quatre
opérations de base pour la persistance des données, en particulier lors de l'utilisation de base de
données.

Soit :

- Create : créer
- Read : lire
- Update : mettre à jour
- Delete : supprimer

  Noter que l'on utilise parfois SCRUD avec un "S" pour Search.

Ce terme est aussi un jeu de mot en anglais sur l'adjectif crude (en français brut ou rudimentaire).

## Application dans les bases de données

| Opération          | SQL      | HTTP           | FIRESTORE   |
|--------------------|----------|----------------|-------------|
| Create             | INSERT   | PUT // POST    | x           |
| Read (retrieve)    | SELECT   | GET            | x           |
| Update (Modify)    | UPDATE   | PUT // PATCH   | x           |
| Delete (Destroy)   | DELETE   | DELETE         | x           |

## Le TP de l'application

Cette application de TodoList sera composée de 2 activités : ////////

- La première affichant la liste des notes enregistrées par ordre de priorité
- La seconde servira pour la création et la modification des éléments TODO
- La suppression sera ajouter directement dans l'item d'un de la liste

#### Les différentes vues de l'application

//TODO Ajouter les images ;)

### Instanciations de bases

#### 1 - Lier Firebase et l'application

En utilisant le menu Tools > Firebase :
- 1/ Activer la liaision avec un projet existant* dont les règles de sécurités sont en mode test ou à
true \* Le cas échéant créer un projet puis le raccorder 
- 2/ Ajouter les librairies pour le bon fonctionnement des classes héritées

#### 2 - Ajouter les librairies connexes

- Ajouter les librairies RecyclerView et CardView en utilisant l'utilitaire wysiwyg File > Project
  Structure > Depedencies > App > + > Library depedency
- Ouvrir le fichier Gradle module, vérifier l'ajout des librairies et trier celles-ci pour une
  meilleure lisibilité 

```xml
    // Firestore
    implementation 'com.google.firebase:firebase-firestore:24.4.1'
    implementation 'com.firebaseui:firebase-ui-firestore:8.0.0'
    // RecyclerView
    implementation 'androidx.recyclerview:recyclerview:1.2.1'
    implementation 'androidx.recyclerview:recyclerview-selection:1.2.0-alpha01'
    // CardView
    implementation 'androidx.cardview:cardview:1.0.0'
```

Pour connaitre le nom exact d'une
librairie [https://developer.android.com/jetpack/androidx/migrate/artifact-mappings]

#### 3 - Ajouter les permissions dans le manifest

Cette application doit avoir accès à internet pour faire la synchronisation avec le serveur

```xml
[...]
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <uses-permission android:name="android.permission.INTERNET" />

    <application
    [...]
```

#### 4 - Création de la classe interface constants dans le package commons

- Clic droit sur le package principale > New > Package
- Le nommer commons
- Clic droit sur le package commons > New > Java Class
- Choisir Interface et la nommer Constants
- Y ajouter la constante du chemin de la collection qui contiendra les TODOS

```xml
  // Constantes pour la collection FireStore
  String COLLECTION_TODO = "TodoList";
```

#### 5 - Ajouter le modèle de données sur lequel on va s'appuyer pour les opérations CRUD

- Clic droit sur le package principal > New > Java Class
- Laisser par défaut et la nommer TodoModel
- Ajouter les deux champs de type String : title, content
- Ajouter un champ de type int : priority
- Dans la foulée, ajouter les constantes pour les champs de la collection dans l'interface constants
```xml
// Constantes pour les champs des documents de cette collection
String KEY_TITLE = "title";
String KEY_CONTENT = "content";
String KEY_PRIORITY = "priority";
```

#### 6 - Création de la première page MainActivity

Dans le layout ajouter, sans oublier les id pour chaque widgets :

- Un recyclerView qui affichera les TODO // rvTodoList
- Un Fab (Floating Action Button) // fabAddTodo Dans le Java
- Noter que pour ajouter le plus du FAB il faut au préalable ajouter un vector asset 'plus' dans drawable. 

Définir des variables du types des widgets

- Créer une méthode initUI() pour faire le lien design/code et initialiser les composants du recycler
- Ajouter la méthode initUI() dans le onCreate

#### 7 - Ajout du code pour la gestion de l'alertDialog de la partie CREATE du CRUD
Pour ajouter des notes il faut cliquer sur le FAB cela ouvrira une alertDialog (sorte de pop-up) permettant l'ajout de TODO
- Commencez par l'ajout du layout de l'alertDialog
- Clic droit sur layout, New > Layout Resource File
- Nommez-le : alert_dialog_create_todo

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_margin="4dp"
        android:orientation="vertical">

  <com.google.android.material.textfield.TextInputLayout
          style="@style/Widget.MaterialComponents.TextInputLayout.OutlinedBox"
          android:layout_width="match_parent"
          android:layout_height="wrap_content"
          android:layout_margin="8dp"
          android:gravity="center">

    <com.google.android.material.textfield.TextInputEditText
            android:id="@+id/etTodoTitle"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="Title"
            android:inputType="textPersonName"
            android:imeOptions="actionDone"/>
  </com.google.android.material.textfield.TextInputLayout>

  <com.google.android.material.textfield.TextInputLayout
          style="@style/Widget.MaterialComponents.TextInputLayout.OutlinedBox"
          android:layout_width="match_parent"
          android:layout_height="wrap_content"
          android:layout_margin="8dp"
          android:gravity="center">

    <com.google.android.material.textfield.TextInputEditText
            android:id="@+id/etTodoContent"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="Content"
            android:inputType="textMultiLine"
            android:imeOptions="actionDone"/>
  </com.google.android.material.textfield.TextInputLayout>
</LinearLayout>
```

Dans MainActivity ajouter une méthode pour afficher l'alertDialog, notez que cette méthode fera appel à la méthode CreateItemIntoFirestore(),
méthode qui sera ajouter par la suite.



