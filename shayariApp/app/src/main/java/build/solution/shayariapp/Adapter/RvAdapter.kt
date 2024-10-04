package build.solution.shayariapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import build.solution.shayariapp.MOdel.DataEntity
import build.solution.shayariapp.R

class RvAdapter(private val context: Context, private var shayriList: List<DataEntity>) :
    RecyclerView.Adapter<RvAdapter.ViewHolder>() {


    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflate the item layout
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_recyler, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataEntity = shayriList[position]

        // Bind data to views
        holder.addTextItem.text = dataEntity.shayari // Assuming `shayari` is a property of DataEntity
    }

    override fun getItemCount(): Int {
        return shayriList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Declare views
        val addTextItem: TextView = itemView.findViewById(R.id.TextItem) // Ensure this ID matches your layout
    }
}
