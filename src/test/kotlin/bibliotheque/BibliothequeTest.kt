package bibliotheque

import bibliotheque.acces.Bibliotheque
import bibliotheque.metier.DVD
import bibliotheque.metier.EnregistrementAudio
import bibliotheque.metier.Journal
import bibliotheque.metier.Livre
import bibliotheque.metier.Magazine
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class BibliothequeTest {

    private fun livre() = Livre("1984", "George Orwell", "Gallimard", "1972-01-01")
    private fun dvd() = DVD("Inception", 8880, "Action", "2010-01-01")

    @Test
    fun empruntEtRetourDuLivre() {
        val b = Bibliotheque()
        val livre = livre()
        assertTrue(b.emprunter(livre))
        assertTrue(livre.estEmprunte)
        assertEquals(1, b.empruntsEnCours.size)
        assertTrue(b.retourner(livre))
        assertFalse(livre.estEmprunte)
        assertTrue(b.empruntsEnCours.isEmpty())
    }

    @Test
    fun doubleEmpruntEtDoubleRetourSontRefuses() {
        val b = Bibliotheque()
        val dvd = dvd()
        assertTrue(b.emprunter(dvd))
        assertFalse(b.emprunter(dvd))
        assertTrue(b.retourner(dvd))
        assertFalse(b.retourner(dvd))
    }

    @Test
    fun livreEstConsultableSurPlace() {
        assertTrue(Bibliotheque().consulter(livre()))
    }

    @Test
    fun magazineEtJournalNeSontPasEmpruntables() {
        val b = Bibliotheque()
        assertFalse(b.emprunter(Magazine("National Geographic", "2026-10-28", "10")))
        assertFalse(b.emprunter(Journal("Le Monde", "2026-10-28")))
    }

    @Test
    fun magazineEtJournalSontConsultables() {
        val b = Bibliotheque()
        assertTrue(b.consulter(Magazine("National Geographic", "2026-10-28", "10")))
        assertTrue(b.consulter(Journal("Le Monde", "2026-10-28")))
    }

    @Test
    fun dvdEtAudioNeSontPasConsultables() {
        val b = Bibliotheque()
        assertFalse(b.consulter(dvd()))
        assertFalse(b.consulter(EnregistrementAudio("Symphonie 9", 960, "Classical", "1967-01-01")))
    }

    @Test
    fun retourDunMediaNonEmprunteEstRefuse() {
        assertFalse(Bibliotheque().retourner(livre()))
    }

    @Test
    fun dateInvalideLeveUneException() {
        assertFailsWith<IllegalArgumentException> { Journal("Le Monde", "28/10/2026") }
        assertFailsWith<IllegalArgumentException> { Journal("Le Monde", "2026-13-45") }
    }

    @Test
    fun titreVideEtDureeNegativeLevent() {
        assertFailsWith<IllegalArgumentException> { Journal("  ", "2026-10-28") }
        assertFailsWith<IllegalArgumentException> { DVD("X", -5, "Action", "2010-01-01") }
    }

    @Test
    fun rechercheParTitre() {
        val b = Bibliotheque()
        b.ajouterMedia(livre())
        b.ajouterMedia(dvd())
        assertEquals(1, b.rechercher("INCEP").size)
        assertTrue(b.rechercher("zzz").isEmpty())
        assertTrue(b.rechercher("   ").isEmpty())
    }

    @Test
    fun disponibilite() {
        val b = Bibliotheque()
        val livre = livre()
        b.ajouterMedia(livre)
        b.ajouterMedia(Journal("Le Monde", "2026-10-28"))
        assertEquals(1, b.mediasDisponibles().size)   // le journal n'est pas empruntable
        b.emprunter(livre)
        assertTrue(b.mediasDisponibles().isEmpty())
    }

    @Test
    fun statistiquesParType() {
        val b = Bibliotheque()
        b.ajouterMedia(livre())
        b.ajouterMedia(Livre("Fahrenheit 451", "Ray Bradbury", "Denoël", "1955-01-01"))
        b.ajouterMedia(dvd())
        val stats = b.statistiques()
        assertEquals(2, stats["Livre"])
        assertEquals(1, stats["DVD"])
    }
}
