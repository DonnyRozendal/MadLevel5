package nl.hva.madlevel5.features.domain

import nl.hva.madlevel5.core.interactor.UseCase
import nl.hva.madlevel5.features.data.repositories.BacklogRepository

class ClearBacklogUseCase(private val backlogRepository: BacklogRepository) :
        UseCase<Boolean, UseCase.None>() {

    override suspend fun run(params: None) = backlogRepository.clearBacklog()

}