package nl.hva.madlevel5.features.presentation.create

import androidx.lifecycle.MutableLiveData
import nl.hva.madlevel5.core.platform.BaseViewModel
import nl.hva.madlevel5.features.data.models.Game
import nl.hva.madlevel5.features.domain.InsertGameUseCase

class CreateViewModel(private val insertGameUseCase: InsertGameUseCase) : BaseViewModel() {

    val gameAdded = MutableLiveData<Boolean>()

    fun insertGame(game: Game) {
        insertGameUseCase(InsertGameUseCase.Params(game)) {
            it.either(::handleFailure) { result -> gameAdded.setValue(result) }
        }
    }

}