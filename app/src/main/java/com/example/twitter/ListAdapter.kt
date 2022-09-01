package example.javatpoint.com.kotlincustomlistview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.twitter.R
import com.example.twitter.data.Post

class PersonRecyclerAdapter:
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<Post> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder {
        return PersonViewHolder(

            LayoutInflater.from(parent.context).inflate(R.layout.custom_list, parent,
                false)
        )
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder,
                                  position: Int) {
        when(holder) {
            is PersonViewHolder -> {
                holder.bind(items[position])
            }
        }
    }
    override fun getItemCount(): Int {
        return items.size
    }
    fun postItemsList(data: ArrayList<Post>) {
        items = data
    }
    class PersonViewHolder constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView) {

        private val personName: TextView =
            itemView.findViewById(R.id.username)
        private val personBirthday: TextView =
            itemView.findViewById(R.id.postText)

        fun bind(post: Post) {

            personName.text = post.Name
            personBirthday.text=post.Text


        }
    }
}