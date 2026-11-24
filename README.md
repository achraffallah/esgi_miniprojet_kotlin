# Système de Gestion de Bibliothèque en Kotlin

Mini-projet Kotlin : emprunt, retour et consultation sur place de différents médias
(livres, magazines, journaux, enregistrements audio, DVD).

## Prérequis
- JDK 17 ou plus
- IntelliJ IDEA  ou Gradle installé

## Exécution
**IntelliJ IDEA** : ouvrir le dossier, attendre l'import Gradle, puis lancer
`src/main/achraf_fellah_miniprojet/presentation/Main.kt`.

**Ligne de commande** :
```bash
gradle run --console=plain                   # scénarios de démonstration
gradle run --console=plain --args="--menu"   # menu interactif
es
```

## Architecture
| Couche | Package | Contenu |
|---|---|---|
| Métier | `bibliotheque.metier` | `Media` (abstraite), interfaces `Empruntable` / `Consultable`, `MediaEmpruntable` (état emprunté factorisé), `Livre`, `Magazine`, `Journal`, `EnregistrementAudio`, `DVD` |
| Accès aux données | `bibliotheque.acces` | `Bibliotheque` : collection `medias`, liste `emprunts` |
| Présentation | `bibliotheque.presentation` | `Main.kt` (scénarios), `Menu.kt` (menu interactif) |

| Média | Empruntable | Consultable sur place |
|---|---|---|
| Livre | oui | oui |
| Magazine | non | oui |
| Journal | non | oui |
| Enregistrement audio | oui | non |
| DVD | oui | non |

## Choix de conception
- **Dates** : `String` au format ISO 8601 `YYYY-MM-DD`, validées à la construction.
- **Validation** : `require(...)` dans les constructeurs ; `emprunter` / `retourner` refusent les
  actions invalides et renvoient `false`.
- Un média emprunté ou consulté sans avoir été ajouté est enregistré automatiquement dans le catalogue.

## Bonus implémentés
Recherche par titre, médias disponibles, statistiques par type, menu interactif avec validation.
