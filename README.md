# Profil développeur UI Compose

## 1. Description
Ce projet améliore ma fiche « Profil développeur » du devoir 03.
L'écran affiche maintenant le profil d'un développeur de façon plus complète et plus soignée :
- un en-tête avec l'avatar, un badge de disponibilité, le nom, le rôle et une description ;
- les compétences, dans une liste qui défile de gauche à droite ;
- les projets, chacun dans une carte ;
- une zone contact avec l'email ;
- un bouton « Me contacter ».

Les infos viennent de la data class `Profile` (et `Project` pour les projets). J'ai deux faux profils pour tester : Léa (disponible) et Tom (indisponible).
Le clic sur le bouton ne fait rien pour l'instant : il appelle juste une fonction vide.

## 2. Composants utilisés
| Composant | Où je l'utilise |
|---|---|
| `Text` | Nom, rôle, description, titres de section, compétences, projets |
| `Button` | `ProfileActionButton` (« Me contacter ») |
| `Icon` | Badge ✓ / ✗ de l'avatar, étoile des projets, enveloppe du contact et du bouton |
| `Image` | L'avatar, avec `painterResource` |
| `Card` | `SkillCard`, `ProjectCard`, `ContactSection` |
| `OutlinedTextField` | L'email, en lecture seule (`readOnly = true`) |

## 3. Layouts utilisés
| Layout | Où et pourquoi |
|---|---|
| `Column` | Dans l'en-tête et dans les cartes : les éléments sont les uns sous les autres |
| `Row` | Icône + titre côte à côte dans `ProjectCard` et `ContactSection` |
| `Box` | `AvatarWithBadge` : le badge est superposé sur l'avatar, en bas à droite (`Alignment.BottomEnd`) |
| `Spacer` | Pour faire de l'espace entre deux éléments (`height` dans une Column, `width` dans une Row) |
| `Surface` | Le fond coloré et arrondi de l'en-tête, et le rond du badge |

## 4. Les listes Lazy
J'utilise deux listes :
- **`LazyColumn`** : c'est tout l'écran. Elle empile les blocs de haut en bas et fait défiler l'écran. J'ajoute un bloc avec `item { }` (en-tête, titres, contact, bouton) et une série de blocs avec `items(profile.projects) { ProjectCard(it) }` : une carte par projet.
- **`LazyRow`** : pour les compétences. Elle défile de gauche à droite et appelle `SkillCard` une fois par compétence.

« Lazy » veut dire que la liste ne dessine que les éléments visibles à l'écran. Avec beaucoup de projets ou de compétences, l'appli reste rapide.

`SkillCard` et `ProjectCard` sont mes composables réutilisables : chacun ne connaît qu'un seul élément (une compétence ou un projet) reçu en paramètre.

## 5. Choix du thème
J'ai créé mon propre thème Material 3 dans le dossier `ui/theme` :
- **colorScheme** (`Color.kt`, `Theme.kt`) : une palette bleu-vert (teal) avec une couleur sable pour les compétences. Il y a une version claire et une version sombre. Le thème choisit la bonne avec `isSystemInDarkTheme()`. J'ai retiré les couleurs dynamiques pour que mes couleurs soient toujours utilisées.
- **typography** (`Type.kt`) : 5 styles de texte (`headlineSmall` pour le nom, `titleLarge` pour les titres de section, `titleMedium`, `bodyMedium`, `labelLarge`).
- **shapes** (`Shape.kt`) : 3 arrondis (`small` pour les compétences et le bouton, `medium` pour les cartes, `large` pour l'en-tête).

Dans mes composables, je n'écris plus de couleur ni de taille en dur : je prends tout dans `MaterialTheme.colorScheme`, `MaterialTheme.typography` et `MaterialTheme.shapes`. C'est pour ça que l'écran passe tout seul en mode sombre.

## 6. Mes fichiers
- `Profile.kt` : les data class `Profile` et `Project`.
- `ProfileData.kt` : `fakeProfiles`, mes deux faux profils.
- `ProfileScreen.kt` : tous mes composables et mes deux previews (clair et sombre).
- `ui/theme/` : `Color.kt`, `Type.kt`, `Shape.kt`, `Theme.kt`.

## 7. Captures
| Mode clair                                        |
|---------------------------------------------------|
| [Modes clair et sombre](capture_card_profile.png) |

## 8. Quelles notions du chapitre Compose UI avez-vous réutilisées ?
- **Les fonctions composables et le découpage** : l'écran est découpé en petits composables (`ProfileHeader`, `AvatarWithBadge`, `SkillCard`, `ProjectCard`, `ContactSection`, `ProfileActionButton`…). J'ai gardé `ProfileFullName`, `ProfileJob` et `ProfileDescription` du devoir 03.
- **Les paramètres et la data class** : chaque composable reçoit ses données en paramètres, rien n'est écrit en dur.
- **Le Modifier** : chaque composable a un `modifier: Modifier = Modifier`, et j'utilise `padding`, `fillMaxWidth`, `size`, `clip`, `align`.
- **Le padding externe et interne** : `contentPadding` de la LazyColumn écarte la liste des bords de l'écran (externe), et le `padding` dans les cartes écarte le contenu de leurs bords (interne).
- **Les callbacks** : `ProfileActionButton` reçoit `onClick`. Il prévient du clic, et c'est `ProfileScreen` qui décide quoi faire.
- **Les previews** : deux previews, Léa en mode clair et Tom en mode sombre, avec les mêmes composables.
- **L'accessibilité de base** : `contentDescription` sur l'avatar et sur le badge (« Disponible » / « Indisponible »), et `null` sur les icônes décoratives.
- **Material 3** : `MaterialTheme` avec `colorScheme`, `typography` et `shapes`.