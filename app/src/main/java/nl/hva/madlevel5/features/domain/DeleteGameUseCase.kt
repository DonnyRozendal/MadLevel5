package nl.hva.madlevel5.features.domain

import nl.hva.madlevel5.core.interactor.UseCase
import nl.hva.madlevel5.features.data.models.Game
import nl.hva.madlevel5.features.data.repositories.BacklogRepository

class DeleteGameUseCase(private val backlogRepository: BacklogRepository) :
        UseCase<Boolean, DeleteGameUseCase.Params>() {

    override suspend fun run(params: Params) = backlogRepository.deleteGame(params.game)

    data class Params(val game: Game)

}