package nl.hva.madlevel5.features.domain

import nl.hva.madlevel5.core.interactor.UseCase
import nl.hva.madlevel5.features.data.models.Game
import nl.hva.madlevel5.features.data.repositories.BacklogRepository

class GetBacklogUseCase(private val backlogRepository: BacklogRepository) :
        UseCase<List<Game>, UseCase.None>() {

    override suspend fun run(params: None) = backlogRepository.getBacklog()

}