package dougles.project.galleryapp.model

class VideoModel() {
    var videoPath: String? = null
    var videoName: String? = null

    constructor (videoPath: String?, videoName: String?) : this() {

        this.videoPath = videoPath
        this.videoName = videoName
    }
}