package nl.hva.madlevel5.features.presentation.backlog

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import nl.hva.madlevel5.features.data.models.Game

class SwipeToDeleteCallback(
        private val adapter: BacklogAdapter,
        private val onSwipedListener: (Game) -> Unit
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

    override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
    ) = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val swipedItem = adapter.currentList[viewHolder.adapterPosition]
        onSwipedListener(swipedItem)
    }

}