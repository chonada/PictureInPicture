package com.example.android.pictureinpicture

import android.app.PictureInPictureParams
import android.app.RemoteAction
import android.os.Build
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity

/**
 * Handler from the PictureInPicture specific code.
 * This helps in grouping the platform (API version) dependent logic.
 */
abstract class PictureInPictureHandler {
    open fun initialize() {}
    open fun updatePictureInPictureParams(isStarted: Boolean = false): PictureInPictureParams? = null
    open fun startPictureInPicture() {}
    /**
     * Creates a [RemoteAction]. It is used as an action icon on the overlay of the
     * picture-in-picture mode.
     */
    open fun createRemoteAction(
        @DrawableRes iconResId: Int,
        @StringRes titleResId: Int,
        requestCode: Int,
        controlType: Int
    ): RemoteAction? = null
}

val PictureInPictureHandler.isPictureInPictureSupported: Boolean
    get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

val AppCompatActivity.isCurrentlyInPictureInPictureMode: Boolean
    get() =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            this.isInPictureInPictureMode
        } else {
            false
        }