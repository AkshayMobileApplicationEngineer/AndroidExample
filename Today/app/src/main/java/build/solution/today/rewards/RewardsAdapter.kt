package build.solution.today
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import build.solution.retrofit.R
import build.solution.today.rewards.RewardsItem


class RewardsAdapter(private val leaderboardItems: ArrayList<RewardsItem>) : RecyclerView.Adapter<LeaderboardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderboardViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_rank, parent, false)
        return LeaderboardViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LeaderboardViewHolder, position: Int) {
        val item = leaderboardItems[position]
        holder.rankTextView.text = "${item.Rank}."
        holder.unameTextView.text = "Akshay"
        holder.scoreTextView.text = "Score: ${item.Score}"
        holder.prizeTextView.text = if (item.Prize != null) item.Prize.toString() else "N/A"
    }

    override fun getItemCount(): Int {
        return leaderboardItems.size
    }
}

class LeaderboardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val rankTextView: TextView = itemView.findViewById(R.id.rankTextView)
    val photoImageView: ImageView = itemView.findViewById(R.id.photoImageView)
    val unameTextView: TextView = itemView.findViewById(R.id.unameTextView)
    val scoreTextView: TextView = itemView.findViewById(R.id.scoreTextView)
    val prizeTextView: TextView = itemView.findViewById(R.id.prizeTextView)
}

data class LeaderboardItem(val rank: Int, val userName: String, val score: Int, val prize: Int?)