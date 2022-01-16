package dougles.project.galleryapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dougles.project.galleryapp.R
import dougles.project.galleryapp.activitiesandFragments.VideoPlayerActivity
import dougles.project.galleryapp.model.VideoModel
import kotlinx.android.synthetic.main.row_image_item_layout.view.*

class VideoAdapter(
    private val context: Context,
    private val videosList: ArrayList<VideoModel>?
) : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.row_image_item_layout, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val currentVideo = videosList?.get(position)

        val intent = Intent(context, VideoPlayerActivity::class.java)

        holder.setData(currentVideo)

        holder.itemView.setOnClickListener {
            intent.putExtra("videoPath", currentVideo?.videoPath)
            intent.putExtra("videoName", currentVideo?.videoName)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return videosList?.size!!
    }


    class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setData(currentVideo: VideoModel?) {
            Glide.with(itemView.context)
                .load(currentVideo?.videoPath)
                .apply(RequestOptions().centerCrop())
                .into(itemView.ivRowImage)
        }
    }

}


