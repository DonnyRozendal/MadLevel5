package nl.hva.madlevel5.features.data.repositories

import nl.hva.madlevel5.core.exception.Failure
import nl.hva.madlevel5.core.functional.Either
import nl.hva.madlevel5.features.data.models.Game
import nl.hva.madlevel5.features.data.room.BacklogDao

interface BacklogRepository {

    fun clearBacklog(): Either<Failure, Boolean>

    fun deleteGame(game: Game): Either<Failure, Boolean>

    fun editGame(game: Game): Either<Failure, Boolean>

    fun getBacklog(): Either<Failure, List<Game>>

    fun insertGame(game: Game): Either<Failure, Boolean>

    class Network(private val backlogDao: BacklogDao) : BacklogRepository {

        override fun clearBacklog(): Either<Failure, Boolean> {
            backlogDao.clearTable()
            return Either.Right(true)
        }

        override fun deleteGame(game: Game): Either<Failure, Boolean> {
            backlogDao.delete(game)
            return Either.Right(true)
        }

        override fun editGame(game: Game): Either<Failure, Boolean> {
            backlogDao.edit(game)
            return Either.Right(true)
        }

        override fun getBacklog(): Either<Failure, List<Game>> {
            return Either.Right(backlogDao.getAll())
        }

        override fun insertGame(game: Game): Either<Failure, Boolean> {
            backlogDao.insert(game)
            return Either.Right(true)
        }

    }

}