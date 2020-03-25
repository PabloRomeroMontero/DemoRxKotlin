package pablo.romero.demorxkotlin.ui

import People
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.character_item.view.*
import pablo.romero.demorxkotlin.R
import pablo.romero.demorxkotlin.ui.MainActivityAdapter.*

class MainActivityAdapter : ListAdapter<People, ViewHolder>(PeopleDiffCallback){

    private var data: List<People> = emptyList()

    class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(people: People) {
//            mirar lo del containerView
            containerView.textViewCharacterName.text = people.name
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.character_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

}



object PeopleDiffCallback : DiffUtil.ItemCallback<People>() {
    override fun areItemsTheSame(oldItem: People, newItem: People): Boolean =
        oldItem.name === newItem.name

    override fun areContentsTheSame(oldItem: People, newItem: People): Boolean =
        oldItem.height == newItem.height && oldItem.birth_year == newItem.birth_year
}