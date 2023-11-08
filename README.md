<h1 style="text-align: center;">Gestion d'Authentification_Spring</h1>

<h2>Introduction</h2>
<p>Le projet de gestion d'authentification, un système essentiel pour sécuriser l'accès aux ressources et services, est abordé dans ce rapport. Les deux entités principales du projet sont la conception de "User" (utilisateur) et "Role". Une relation Many-to-Many relie ces deux entités, ce qui permet à un utilisateur d'être associé à plusieurs rôles, et inversement. L'utilisation de la technologie Java Persistence API (JPA) et de Spring Data rend la gestion de ces relations plus facile.
 </p>

<h2>Conception du Projet</h2>
<p >La conception du projet repose sur deux entités principales :
User (utilisateur) et Role (rôle). Un utilisateur peut être associé à 
plusieurs rôles, et un rôle peut être attribué à plusieurs utilisateurs. 
Cette relation est gérée en utilisant une association Many-to-Many entre 
les entités "User" et "Role".</p>
<img src="captures/conception.png" alt="Conception">
<h2>Structure du Projet</h2>
  <p>Le projet est organisé en plusieurs packages, chacun a un rôle precis :</p>
  <ul>
    <li>Le package <code>ma.enset.entities</code> contient les définitions des entités <code>User</code> et <code>Role</code>. Ces entités sont annotées avec des métadonnées JPA pour les mappages en base de données.</li>
    <li>Le package <code>ma.enset.repositories</code> abrite les interfaces <code>UserRepository</code> et <code>RoleRepository</code>, qui héritent de <code>JpaRepository</code>. Ces interfaces sont utilisées pour effectuer des opérations CRUD (Create, Read, Update, Delete) sur les entités <code>User</code> et <code>Role</code>.</li>
    <li>Le package <code>ma.enset.service</code> contient l'interface <code>UserService</code> et son implémentation <code>UserServiceImpl</code>. <code>UserService</code> définit des méthodes pour la gestion des utilisateurs et des rôles, telles que l'ajout d'un nouvel utilisateur, la recherche d'un utilisateur par nom d'utilisateur, et bien d'autres. <code>UserServiceImpl</code> fournit l'implémentation concrète de ces méthodes.</li>
    <li>Le package <code>ma.enset.web</code> contient le contrôleur <code>UserController</code>, qui expose une API REST pour récupérer les détails d'un utilisateur en utilisant son nom d'utilisateur. Cette API est accessible via une requête HTTP GET sur l'URL <code>"/users/{username}"</code>.</li>
  </ul>

<h2>Annotations</h2>
<p style="text-align: justify; text-justify: inter-word;">Les annotations sont largement utilisées dans le projet pour configurer les entités, les repositories, et les contrôleurs REST. Voici un résumé des annotations clés et de leur utilité :</p>
<ol>
  <li><strong>Annotations_entités :</strong>
    <ul>
      <li><strong>@Entity :</strong> Marque les classes comme des entités persistantes, mappées sur des tables de base de données.</li>
      <li><strong>@Id :</strong> Définit un champ comme la clé primaire de l'entité.</li>
      <li><strong>@GeneratedValue(strategy = GenerationType.IDENTITY) :</strong> Définit la stratégie de génération des valeurs de la clé primaire, indiquant que la base de données génère automatiquement les valeurs.</li>
      <li><strong>@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) :</strong> Contrôle la sérialisation JSON des entités en excluant certains champs de la réponse JSON.</li>
      <li><strong>@ManyToMany(fetch = FetchType.EAGER) :</strong> Indique une relation many-to-many entre les entités avec un chargement EAGER.</li>
      <li><strong>@ToString.Exclude :</strong> Exclut le champ de la génération automatique de la méthode <code>toString()</code> par Lombok.</li>
      <li><strong>@Column(name="Description") :</strong> Définit le nom de la colonne dans la table de base de données.</li>
      <li><strong>@Table(name="USERS") :</strong> Définit le nom de la table dans la base de données pour l'entité.</li>
      <li><strong>@Enumerated(EnumType.STRING) :</strong> Utilisée pour stocker les valeurs d'une énumération sous forme de chaînes de caractères dans la base de données, ce qui facilite la lisibilité des données.</li>
      <li><strong>@AllArgsConstructor :</strong> Cette annotation est généralement associée au framework Lombok. Elle génère automatiquement un constructeur qui prend tous les champs de la classe en tant que paramètres, simplifiant la création d'instances de la classe.</li>
      <li><strong>@NoArgsConstructor :</strong> Cette annotation, également associée à Lombok, génère un constructeur sans aucun paramètre, permettant de créer des instances de la classe sans avoir à spécifier de valeurs pour les champs.</li>
      <li><strong>@Data :</strong> Cette annotation de Lombok génère automatiquement des méthodes getter, setter, equals(), hashCode(), et toString() pour tous les champs de la classe, réduisant le code boilerplate.</li>
    </ul>
  </li>
  <li><strong>Annotations_repositories :</strong>
    <ul>
      <li><strong>@Repository :</strong> Indique que la classe est un composant de persistance Spring, activant la création automatique d'une implémentation de l'interface par Spring Data JPA.</li>
      <li><strong>JpaRepository :</strong> L'interface fournit des méthodes de base pour effectuer des opérations de CRUD sur l'entité correspondante.</li>
    </ul>
  </li>
  <li><strong>Annotations_services :</strong>
    <ul>
      <li><strong>@Service :</strong> Cette annotation est utilisée pour indiquer que la classe est un composant de service Spring, permettant à Spring de détecter automatiquement cette classe lors de la configuration de l'application.</li>
      <li><strong>@Transactional :</strong> Cette annotation est utilisée pour indiquer que les méthodes de cette classe sont transactionnelles, gérant les transactions de base de données.</li>
    </ul>
  </li>
  <li><strong>Annotations_contrôlleurs REST :</strong>
    <ul>
      <li><strong>@RestController :</strong> Marque les classes comme des contrôleurs REST, permettant d'exposer des points d'API HTTP.</li>
      <li><strong>@GetMapping :</strong> Définit les méthodes comme des gestionnaires de requêtes HTTP GET pour des chemins d'URL spécifiques.</li>
      <li><strong>@Autowired :</strong> Utilisée pour injecter automatiquement les dépendances, permettant d'accéder aux ressources nécessaires, comme les repositories, dans les contrôleurs.</li>
    </ul>
  </li>
  <li><strong>Annotation @Bean pour la Configuration des Beans :</strong>
    <ul>
      <li><strong>@Bean :</strong> Utilisée pour créer un bean personnalisé de type CommandLineRunner, gérant des dépendances, personnalisant le comportement du démarrage de l'application, centralisant la configuration, et exécutant des tâches spécifiques au lancement de l'application.</li>
    </ul>
  </li>
</ol>
<h2>Configuration de la Base de Données</h2>
<p style="text-align: justify; text-justify: inter-word;">Le projet est configuré pour utiliser 
une base de données afin de stocker les informations des utilisateurs, des rôles, et les relations entre eux.
La configuration de la source de données se fait dans le fichier application.properties, 
où l'on peut spécifier le type de base de données (H2 ou MySQL), l'URL de la base de données,
le nom d'utilisateur, et le mot de passe. 
Dans ce projet,on a utilisé dans un premier temps H2 et puis MySQL.</p>

<h2>Gestion des Utilisateurs et leurs Rôles</h2>
<p>Le service UserService propose un ensemble de fonctionnalités essentielles pour la gestion des utilisateurs et des rôles :</p>


<h3>1. Ajout d'un Nouveau Utilisateur</h3>
<p>La méthode <code>addNewUser(User user)</code> permet d'ajouter un nouveau
utilisateur en spécifiant son nom d'utilisateur et son password. Un identifiant
unique est généré automatiquement pour l'utilisateur.</p>
<img src="captures/user_h2.png" alt="user_db_h2">

<h3>2. Ajout d'un Nouveau Rôle</h3>
<p>La méthode <code>addNewRole(Role role)</code> permet d'ajouter un
nouveau rôle en précisant son nom.</p>
<img src="captures/role_h2.png" alt="role_db_h2">
<h3>3. Recherche d'un Utilisateur par Nom d'Utilisateur et afficher ses roles avec son mot de passe</h3>
<img src="captures/affich_psw.png" alt="user avec ses roles">
<img src="captures/user_json_psw.png" alt="user avec ses roles">

<h3>4. Attribution d'un Rôle à un Utilisateur</h3>
<p>La méthode <code>addRoleToUser(String userName, String roleName)</code>
permet d'attribuer un rôle à un utilisateur en établissant une relation Many-to-Many
entre l'utilisateur et le rôle.</p>
<img src="captures/role_user_h2.png" alt="role_user">


<h3>5. Authentification d'un Utilisateur</h3>
<p>La méthode <code>autenhticate(String userName, String password)</code> permet
d'authentifier un utilisateur en vérifiant ses informations d'identification,
telles que le nom d'utilisateur et le mot de passe. Cette méthode garantit une
authentification sécurisée.</p>
<h4> Authentification réussie</h4>
<img src="captures/affichage.png" alt="Authentification réussie">
<h4> Authentification en échec</h4>
<img src="captures/badCredential.png" alt="Authentification en échec">

<h2>Exposition d'une API REST</h2>
<p>Le  contrôleur "UserController" expose une API REST pour 
récupérer les détails d'un utilisateur en utilisant son nom d'utilisateur.
Cette API est accessible via une requête HTTP GET sur l'URL "/users/{username}".</p>


<h3>les détails d'un utilisateur sans mot de passe</h3>
<p>Pour exclure le mot de passe, on utilise la sérialisation JSON avec <code>@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)</code> , 
afin de maintenir la sécurité et la confidentialité des informations sensibles.</p>
<img src="captures/ne_pas_aff_psw.png" alt="user avec ses roles">
<img src="captures/user_json.png" alt="users_json">
<h2>Conclusion</h2>
<p>Ce projet de gestion d'authentification démontre à quel point il est important de concevoir un système d'authentification 
robuste et sécurisé. Il s'appuie sur des concepts tels que les relations Many-to-Many, les annotations JPA et l'injection de dépendances. En exposant une API REST, il permet d'accéder aux informations des utilisateurs tout en maintenant la confidentialité des informations sensibles.

</p>