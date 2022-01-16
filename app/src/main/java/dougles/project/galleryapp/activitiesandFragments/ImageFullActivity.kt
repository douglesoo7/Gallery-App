package dougles.project.galleryapp.activitiesandFragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import dougles.project.galleryapp.R
import kotlinx.android.synthetic.main.activity_image_full.*

class ImageFullActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_full)

        val imagePath = intent.getStringExtra("imagePath")
        val imageName = intent.getStringExtra("imageName")

        supportActionBar?.title = imageName
        Glide.with(this)
            .load(imagePath)
            .into(ivImageView)
    }
}