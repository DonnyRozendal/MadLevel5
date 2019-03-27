package nl.hva.madlevel5.features.presentation.backlog

import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_backlog.*
import nl.hva.madlevel5.R
import nl.hva.madlevel5.core.extension.observe
import nl.hva.madlevel5.core.navigation.Navigator
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class BacklogActivity : AppCompatActivity() {

    private val viewModel by viewModel<BacklogViewModel>()
    private val navigator by inject<Navigator>()

    private val adapter = BacklogAdapter {
        navigator.showEditActivity(this, it)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_backlog)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Game Backlog"

        viewModel.apply {
            observe(backlog) { adapter.submitList(it.toList()) }
            observe(backlogCleared) { if (it) viewModel.getBacklog() }
            observe(gameDeleted) { if (it) viewModel.getBacklog() }
        }
        initViews()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getBacklog()
    }

    private fun initViews() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        ItemTouchHelper(SwipeToDeleteCallback(adapter) {
            viewModel.deleteGame(it)
        }).attachToRecyclerView(recyclerView)

        iconDelete.setOnClickListener {
            viewModel.clearBacklog()
        }
        buttonAdd.setOnClickListener {
            navigator.showCreateActivity(this)
        }
    }

}