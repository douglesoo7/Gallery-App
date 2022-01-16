package dougles.project.galleryapp.model

class ImageModel() {

    var imagePath: String? = null
    var imageName: String? = null

    constructor(imagePath: String?, imageName: String?) : this() {

        this.imagePath = imagePath
        this.imageName = imageName
    }
}