package nl.hva.madlevel5.features.presentation.backlog

import androidx.lifecycle.MutableLiveData
import nl.hva.madlevel5.core.interactor.UseCase
import nl.hva.madlevel5.core.platform.BaseViewModel
import nl.hva.madlevel5.features.data.models.Game
import nl.hva.madlevel5.features.data.repositories.BacklogRepository
import nl.hva.madlevel5.features.domain.ClearBacklogUseCase
import nl.hva.madlevel5.features.domain.DeleteGameUseCase
import nl.hva.madlevel5.features.domain.GetBacklogUseCase

class BacklogViewModel(
        private val getBacklogUseCase: GetBacklogUseCase,
        private val clearBacklogUseCase: ClearBacklogUseCase,
        private val deleteGameUseCase: DeleteGameUseCase
) : BaseViewModel() {

    val backlog = MutableLiveData<List<Game>>()
    val backlogCleared = MutableLiveData<Boolean>()
    val gameDeleted = MutableLiveData<Boolean>()

    fun getBacklog() {
        getBacklogUseCase(UseCase.None()) {
            it.either(::handleFailure) { result -> backlog.setValue(result) }
        }
    }

    fun clearBacklog() {
        clearBacklogUseCase(UseCase.None()) {
            it.either(::handleFailure) { result -> backlogCleared.setValue(result) }
        }
    }

    fun deleteGame(game: Game) {
        deleteGameUseCase(DeleteGameUseCase.Params(game)) {
            it.either(::handleFailure) { result -> gameDeleted.setValue(result) }
        }
    }

}