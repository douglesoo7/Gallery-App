package dougles.project.galleryapp.activitiesandFragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import dougles.project.galleryapp.R
import kotlinx.android.synthetic.main.activity_video_player.*

class VideoPlayerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        val videoPath = intent.getStringExtra("videoPath")
        val videoName = intent.getStringExtra("videoName")

        playVideo(videoPath, videoName)


    }

    //playing the video
    private fun playVideo(videoPath: String?, videoName: String?) {
        supportActionBar?.title = videoName

        val mediaController = MediaController(this)

        videoView.setMediaController(mediaController)
        videoView.setVideoPath(videoPath)
        videoView.start()
    }
}