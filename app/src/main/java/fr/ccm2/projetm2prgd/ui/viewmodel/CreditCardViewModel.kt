package fr.ccm2.projetm2prgd.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.ccm2.projetm2prgd.data.repository.CreditCardRepository
import fr.ccm2.projetm2prgd.ui.model.CreditCardUi
import fr.ccm2.projetm2prgd.ui.model.toUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMap
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class CreditCardViewModel : ViewModel() {
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val creditCardRepository: CreditCardRepository by lazy { CreditCardRepository() }
    private val _creditCards: Flow<List<CreditCardUi>>
        get() = creditCardRepository.selectAll().map { list ->
            list.groupBy { it.type }
        }.map { groupedMap ->
            buildList {
                groupedMap.forEach { (type, groupedCards) ->
                    // Ajouter un header avec le type comme titre
                    add(CreditCardUi.Header(title = type))

                    // Ajouter toutes les cartes dans ce groupe sous forme d'items, triées par numéro de carte
                    addAll(groupedCards.sortedBy { it.number }.map { card ->
                        CreditCardUi.Item(
                            number = card.number,
                            expiryDate = card.expiryDate,
                            type = card.type
                        )
                    })

                    // Ajouter un footer avec le nombre de cartes dans ce groupe de type spécifique
                    add(CreditCardUi.Footer(footer = groupedCards.size))
                }

                // Ajouter un FooterTotal avec le nombre total de cartes
                add(CreditCardUi.FooterTotal(footerTotal = groupedMap.values.flatten().size))
            }
        }
    val creditCards = _creditCards

    fun insertNewCreditCard() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                creditCardRepository.fetchData()
            } catch (e: Exception) {
                //l'api à un cooldown entre les appels api
                _errorMessage.value = "Erreur lors de la récupération des données. Veuillez réessayer ultérieurement"
            }

        }
    }
    fun deleteAllCreditCards() {
        viewModelScope.launch(Dispatchers.IO) {
            creditCardRepository.deleteAll()
        }
    }
}
