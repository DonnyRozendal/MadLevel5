package nl.hva.madlevel5.features.presentation.edit

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_create.*
import nl.hva.madlevel5.R
import nl.hva.madlevel5.core.extension.observe
import nl.hva.madlevel5.features.data.models.Game
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class EditActivity : AppCompatActivity() {

    private val viewModel by viewModel<EditViewModel>()

    companion object {
        const val GAME = "GAME"

        fun getIntent(context: Context, game: Game): Intent {
            val intent = Intent(context, EditActivity::class.java)
            intent.putExtra(GAME, game)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Edit Game"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.apply {
            observe(gameEdited) { if (it) finish() }
        }
        initViews()
    }

    private fun initViews() {
        val game = intent.getParcelableExtra<Game>(GAME)
        setGame(game)
        buttonSave.setOnClickListener {
            viewModel.editGame(getUpdatedGame(game.id))
        }
    }

    private fun setGame(game: Game) {
        textInputEditTextTitle.setText(game.title)
        textInputEditTextPlatform.setText(game.platform)
        spinner.setSelection(resources.getStringArray(R.array.status).indexOf(game.status))
    }

    private fun getUpdatedGame(id: Int?): Game {
        val title = textInputEditTextTitle.text.toString()
        val platform = textInputEditTextPlatform.text.toString()
        val status = spinner.selectedItem.toString()

        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val today = simpleDateFormat.format(Calendar.getInstance().time)

        return Game(id, title, platform, status, today)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

}