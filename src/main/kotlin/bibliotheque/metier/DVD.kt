package bibliotheque.metier

/**
 * DVD : empruntable uniquement.
 */
class DVD(
    titre: String,
    val duree: Int,
    val genre: String,
    dateDeParution: String
) : MediaEmpruntable(titre, dateDeParution) {

    init {
        require(duree > 0) { "La durée doit être strictement positive (en secondes)." }
        require(genre.isNotBlank()) { "Le genre ne peut pas être vide." }
    }

    override val typeLibelle = "DVD"
    override val designation get() = "Le DVD '$titre'"

    override fun afficher() =
        println("DVD: Titre = '$titre', Durée = '$duree' secondes, Genre = '$genre'")
}
