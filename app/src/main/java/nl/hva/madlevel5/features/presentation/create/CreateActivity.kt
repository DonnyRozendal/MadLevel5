package nl.hva.madlevel5.features.presentation.create

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_create.*
import nl.hva.madlevel5.R
import nl.hva.madlevel5.core.extension.observe
import nl.hva.madlevel5.core.extension.showKeyboard
import nl.hva.madlevel5.features.data.models.Game
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class CreateActivity : AppCompatActivity() {

    private val viewModel by viewModel<CreateViewModel>()

    companion object {
        fun getIntent(context: Context) = Intent(context, CreateActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Create Game"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.apply {
            observe(gameAdded) { finish() }
        }
        initViews()
    }

    private fun initViews() {
        buttonSave.setOnClickListener {
            viewModel.insertGame(getGame())
        }
        textInputEditTextTitle.requestFocus()
    }

    private fun getGame(): Game {
        val title = textInputEditTextTitle.text.toString()
        val platform = textInputEditTextPlatform.text.toString()
        val status = spinner.selectedItem.toString()

        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val today = simpleDateFormat.format(Calendar.getInstance().time)

        return Game(null, title, platform, status, today)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

}