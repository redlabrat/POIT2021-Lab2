package by.brstu.poit.redlabrat.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PlanetListAdapter(
    val items: List<String>,
    val onItemClickListener: OnPlanetItemClick?
) : RecyclerView.Adapter<PlanetListAdapter.PlanetViewHolder>() {

    interface OnPlanetItemClick {
        fun onPlanetClick(planet: String)
    }

    class PlanetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.itemText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_planet, parent, false)
        return PlanetViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlanetViewHolder, position: Int) {
        val item = items[position]
        holder.textView.text = item
        holder.itemView.setOnClickListener { onItemClickListener?.onPlanetClick(item) }
    }

    override fun getItemCount() = items.size
}