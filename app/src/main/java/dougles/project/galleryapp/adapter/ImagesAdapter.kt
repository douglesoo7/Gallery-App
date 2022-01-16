package dougles.project.galleryapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dougles.project.galleryapp.activitiesandFragments.ImageFullActivity
import dougles.project.galleryapp.R
import dougles.project.galleryapp.model.ImageModel
import kotlinx.android.synthetic.main.row_image_item_layout.view.*

class ImagesAdapter(
    private val context: Context,
    private val imagesList: ArrayList<ImageModel>?
) : RecyclerView.Adapter<ImagesAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.row_image_item_layout, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val currentImage = imagesList?.get(position)

        val intent = Intent(context, ImageFullActivity::class.java)

        holder.setData(currentImage)

        holder.itemView.setOnClickListener {
            intent.putExtra("imagePath", currentImage?.imagePath)
            intent.putExtra("imageName", currentImage?.imageName)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return imagesList?.size!!
    }


    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(currentImage: ImageModel?) {
            Glide.with(itemView.context)
                .load(currentImage?.imagePath)
                .apply(RequestOptions().centerCrop())
                .into(itemView.ivRowImage)
        }
    }

}


