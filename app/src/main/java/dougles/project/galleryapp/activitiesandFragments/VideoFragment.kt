package dougles.project.galleryapp.activitiesandFragments

import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import dougles.project.galleryapp.R
import dougles.project.galleryapp.adapter.VideoAdapter
import dougles.project.galleryapp.model.VideoModel
import kotlinx.android.synthetic.main.fragment_video.*

class VideoFragment : Fragment() {

    private var allVideos: ArrayList<VideoModel>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()

        allVideos = ArrayList()

        if (allVideos!!.isEmpty()) {
            allVideos = getAllVideos()
            videosRecyclerView?.adapter = VideoAdapter(requireContext(), allVideos)
        }
    }

    //setting the Videos RecyclerView
    private fun setRecyclerView() {
        videosRecyclerView.layoutManager = GridLayoutManager(context, 3)
        videosRecyclerView.setHasFixedSize(true)
        videosRecyclerView.adapter = VideoAdapter(requireContext(), allVideos)
    }

    // Fetching all videos of the Device
    private fun getAllVideos(): ArrayList<VideoModel>? {
        val videos = ArrayList<VideoModel>()

        val allVideosUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI

        val projection =
            arrayOf(MediaStore.MediaColumns.DATA, MediaStore.Video.Media.DISPLAY_NAME)

        var cursor =
            requireActivity().contentResolver.query(allVideosUri, projection, null, null, null)

        try {

            cursor!!.moveToFirst()

            do {
                val video = VideoModel()
                video.apply {
                    videoPath =
                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                    videoName =
                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))
                    videos.add(video)
                }
            } while (cursor.moveToNext())
            cursor.close()
        } catch (e: Exception) {
            Log.d("ImagesFragment", e.printStackTrace().toString())
        }

        return videos
    }
}