package com.example.android.pictureinpicture

import android.app.PictureInPictureParams

abstract class PictureInPictureHandler {
    open val isInPictureInPictureMode: Boolean
        get() {
            return false
        }

    open fun initialize() {}
    open fun updatePictureInPictureParams(): PictureInPictureParams? {
        return null
    }

    open fun startPictureInPicture() {}
}