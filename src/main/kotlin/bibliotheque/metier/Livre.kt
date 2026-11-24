package bibliotheque.metier

// Livre : empruntable et consultable sur place
class Livre(
    titre: String,
    val auteur: String,
    val editeur: String,
    dateDeParution: String
) : MediaEmpruntable(titre, dateDeParution), Consultable {

    init {
        require(auteur.isNotBlank()) { "L'auteur ne peut pas être vide." }
        require(editeur.isNotBlank()) { "L'éditeur ne peut pas être vide." }
    }

    override val typeLibelle = "Livre"
    override val designation get() = "Le livre '$titre' de '$auteur'"

    override fun afficher() = println(
        "Livre: Titre = '$titre', Auteur = '$auteur', Éditeur = '$editeur', " +
            "Date de parution = '$dateDeParution'"
    )

    override fun consulter() = println("$designation est consulté sur place.")
}
