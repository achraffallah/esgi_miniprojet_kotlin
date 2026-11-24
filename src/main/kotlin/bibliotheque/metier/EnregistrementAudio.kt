package bibliotheque.metier

/**
 * Enregistrement audio
 */
class EnregistrementAudio(
    titre: String,
    val duree: Int,
    val genre: String,
    dateDeParution: String
) : MediaEmpruntable(titre, dateDeParution) {

    init {
        require(duree > 0) { "La durée doit être strictement positive (en secondes)." }
        require(genre.isNotBlank()) { "Le genre ne peut pas être vide." }
    }

    override val typeLibelle = "Enregistrement Audio"
    override val designation get() = "L'enregistrement audio '$titre'"

    override fun afficher() = println(
        "Enregistrement Audio: Titre = '$titre', Durée = '$duree' secondes, Genre = '$genre'"
    )
}
