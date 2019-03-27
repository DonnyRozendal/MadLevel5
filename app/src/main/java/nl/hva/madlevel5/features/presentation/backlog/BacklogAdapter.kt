package nl.hva.madlevel5.features.presentation.backlog

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view_item_row.*
import kotlinx.android.synthetic.main.view_item_row.view.*
import nl.hva.madlevel5.R
import nl.hva.madlevel5.features.data.models.Game

class BacklogAdapter(private val clickListener: (Game) -> Unit) :
        ListAdapter<Game, BacklogAdapter.ViewHolder>(GameDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater
                .from(parent.context)
                .inflate(R.layout.view_item_row, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(game: Game, clickListener: (Game) -> Unit) = with(view) {
            textViewTitle.setTypeface(null, Typeface.ITALIC)
            textViewTitle.text = game.title
            textViewPlatform.text = game.platform
            textViewStatus.text = game.status
            textViewDate.text = game.date

            setOnClickListener { clickListener(game) }
        }

    }

    class GameDiffCallback : DiffUtil.ItemCallback<Game>() {
        override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem == newItem
        }
    }

}