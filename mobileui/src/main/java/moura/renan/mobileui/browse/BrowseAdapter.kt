package moura.renan.mobileui.browse

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import moura.renan.mobileui.R
import moura.renan.mobileui.model.Project
import javax.inject.Inject

class BrowseAdapter @Inject constructor() : RecyclerView.Adapter<BrowseAdapter.ViewHolder>() {

    var projects: List<Project> = arrayListOf()
    var projectListener : ProjectListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_project, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = projects.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(projects[position])
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var avatarImage: ImageView = view.findViewById(R.id.image_owner_avatar)
        private var ownerNameText: TextView = view.findViewById(R.id.text_owner_name)
        private var projectNameText: TextView = view.findViewById(R.id.text_project_name)
        private var bookmarkedImage: ImageView = view.findViewById(R.id.image_bookmarked)

        fun bind(project: Project) {
            Glide.with(itemView.context).load(project.ownerAvatar).apply(RequestOptions.circleCropTransform()).into(avatarImage)
            ownerNameText.text = project.ownerName
            projectNameText.text = project.fullName
            bookmarkedImage.setImageResource(if(project.isBookmarked) R.drawable.ic_star_black_24dp else R.drawable.ic_star_border_black_24dp)
            itemView.setOnClickListener {
                if (project.isBookmarked) {
                    projectListener?.onBookmarkedProjectClicked(project.id)
                } else {
                    projectListener?.onProjectClicked(project.id)
                }
            }
        }
    }
}