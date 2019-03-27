package nl.hva.madlevel5.core.di

import androidx.room.Room
import nl.hva.madlevel5.core.navigation.Navigator
import nl.hva.madlevel5.features.data.repositories.BacklogRepository
import nl.hva.madlevel5.features.data.room.BacklogDatabase
import nl.hva.madlevel5.features.domain.*
import nl.hva.madlevel5.features.presentation.backlog.BacklogViewModel
import nl.hva.madlevel5.features.presentation.create.CreateViewModel
import nl.hva.madlevel5.features.presentation.edit.EditViewModel
import org.koin.androidx.viewmodel.experimental.builder.viewModel
import org.koin.dsl.module.module
import org.koin.experimental.builder.factory
import org.koin.experimental.builder.single

val applicationModule = module {
    single<Navigator>()
}

val viewModelModule = module {
    viewModel<CreateViewModel>()
    viewModel<BacklogViewModel>()
    viewModel<EditViewModel>()
}

val useCaseModule = module {
    factory<GetBacklogUseCase>()
    factory<InsertGameUseCase>()
    factory<ClearBacklogUseCase>()
    factory<EditGameUseCase>()
    factory<DeleteGameUseCase>()
}

val repositoryModule = module {
    single<BacklogRepository> { BacklogRepository.Network(get()) }
}

val roomModule = module {
    single { Room.databaseBuilder(get(), BacklogDatabase::class.java, "backlog").build() }
    single { get<BacklogDatabase>().backlogDao() }
}

val koinModules = listOf(
        applicationModule,
        viewModelModule,
        useCaseModule,
        repositoryModule,
        roomModule
)