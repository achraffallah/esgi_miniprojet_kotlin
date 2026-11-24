package bibliotheque.metier

// Magazine : consultable sur place uniquement
class Magazine(
    titre: String,
    dateDeParution: String,
    val numero: String
) : Media(titre, dateDeParution), Consultable {

    init {
        require(numero.isNotBlank()) { "Le numéro ne peut pas être vide." }
    }

    override val typeLibelle = "Magazine"

    override fun afficher() = println(
        "Magazine: Titre = '$titre', Numéro = '$numero', Date de parution = '$dateDeParution'"
    )

    override fun consulter() =
        println("Le magazine '$titre', numéro '$numero' ($dateDeParution) est consulté sur place.")
}
