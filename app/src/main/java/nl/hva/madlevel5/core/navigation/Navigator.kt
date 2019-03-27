package nl.hva.madlevel5.core.navigation

import android.content.Context
import nl.hva.madlevel5.features.data.models.Game
import nl.hva.madlevel5.features.presentation.create.CreateActivity
import nl.hva.madlevel5.features.presentation.edit.EditActivity

class Navigator {

    fun showCreateActivity(context: Context) {
        context.startActivity(CreateActivity.getIntent(context))
    }

    fun showEditActivity(context: Context, game: Game) {
        context.startActivity(EditActivity.getIntent(context, game))
    }

}