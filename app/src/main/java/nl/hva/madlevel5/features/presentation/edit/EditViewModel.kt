package nl.hva.madlevel5.features.presentation.edit

import androidx.lifecycle.MutableLiveData
import nl.hva.madlevel5.core.platform.BaseViewModel
import nl.hva.madlevel5.features.data.models.Game
import nl.hva.madlevel5.features.domain.EditGameUseCase

class EditViewModel(private val editGameUseCase: EditGameUseCase) : BaseViewModel() {

    val gameEdited = MutableLiveData<Boolean>()

    fun editGame(game: Game) {
        editGameUseCase(EditGameUseCase.Params(game)) {
            it.either(::handleFailure) { result -> gameEdited.setValue(result) }
        }
    }

}