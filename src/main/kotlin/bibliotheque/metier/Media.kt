package bibliotheque.metier

import java.time.LocalDate
import java.time.format.DateTimeParseException

/**
 * Classe de base de tous les médias de la bibliothèque.
 *
 * @property titre titre du média (non vide)
 * @property dateDeParution date au format ISO 8601 : YYYY-MM-DD.
 *   Si la date exacte est inconnue, utiliser le 1er du mois ou le 1er janvier de l'année.
 */
abstract class Media(val titre: String, val dateDeParution: String) {

    init {
        require(titre.isNotBlank()) { "Le titre ne peut pas être vide." }
        require(estDateIsoValide(dateDeParution)) {
            "Date invalide '$dateDeParution' : le format attendu est YYYY-MM-DD (ISO 8601)."
        }
    }

    // Nom du type de média, utilisé pour les statistiques
    abstract val typeLibelle: String

    // Affiche les détails spécifiques du média sur la console
    abstract fun afficher()

    companion object {
        private val FORMAT_ISO = Regex("""\d{4}-\d{2}-\d{2}""")

        // Vérifie le format YYYY-MM-DD et l'existence réelle de la date
        fun estDateIsoValide(date: String): Boolean {
            if (!FORMAT_ISO.matches(date)) return false
            return try {
                LocalDate.parse(date)
                true
            } catch (e: DateTimeParseException) {
                false
            }
        }
    }
}
