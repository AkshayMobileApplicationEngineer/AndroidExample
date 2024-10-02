package build.solution.retrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide // Assuming you are using Glide for image loading

class NewAdapter(private val context: Context, private val articles: List<Article>) :
    RecyclerView.Adapter<NewAdapter.ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]

        // Bind the data to the views
        holder.newsTitle.text = article.title
        holder.newsDescription.text = article.description

        // Load the image using Glide (or any other image loading library)
        Glide.with(context)
            .load(article.urlToImage) // Assuming `imageUrl` is a property in the `Article` class
            .into(holder.newsImage)

        // Item Click in recycler view
        holder.itemMain.setOnClickListener {
            if (position == 1) {
                Toast.makeText(context, "Item Holder was hit", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Item Holder was hit on other position", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun getItemCount(): Int {
        return articles.size
    }

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val newsImage: ImageView = itemView.findViewById(R.id.newImg)
        val newsTitle: TextView = itemView.findViewById(R.id.newTitle)
        val newsDescription: TextView = itemView.findViewById(R.id.newDiscri)
        val itemMain :LinearLayout=itemView.findViewById(R.id.mian_item)
    }
}
