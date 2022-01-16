package dougles.project.galleryapp.activitiesandFragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import dougles.project.galleryapp.R
import dougles.project.galleryapp.adapter.ImagesAdapter
import dougles.project.galleryapp.model.ImageModel
import kotlinx.android.synthetic.main.fragment_images.*

class ImagesFragment : Fragment() {

    private var allPictures: ArrayList<ImageModel>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_images, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()

        //Storage Permissions
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                101
            )
        }

        allPictures = ArrayList()

        if (allPictures!!.isEmpty()) {
            allPictures = getAllImages()
            imageRecyclerView?.adapter = ImagesAdapter(requireContext(), allPictures)
        }
    }

    //setting the Images RecyclerView
    private fun setRecyclerView() {
        imageRecyclerView.layoutManager = GridLayoutManager(context, 3)
        imageRecyclerView.setHasFixedSize(true)
        imageRecyclerView.adapter = ImagesAdapter(requireContext(), allPictures)
    }

    // Fetching all the images of the Device
    private fun getAllImages(): ArrayList<ImageModel>? {
        val images = ArrayList<ImageModel>()

        val allImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        val projection =
            arrayOf(MediaStore.Images.ImageColumns.DATA, MediaStore.Images.Media.DISPLAY_NAME)

        var cursor =
            requireActivity().contentResolver.query(allImageUri, projection, null, null, null)

        try {

            cursor!!.moveToFirst()

            do {
                val image = ImageModel()
                image.apply {
                    imagePath =
                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                    imageName =
                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))
                    images.add(image)
                }
            } while (cursor.moveToNext())
            cursor.close()
        } catch (e: Exception) {
            Log.d("ImagesFragment", e.printStackTrace().toString())
        }
        return images

    }
}