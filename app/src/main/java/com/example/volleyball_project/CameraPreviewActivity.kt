package com.example.volleyball_project

import android.content.Context
import android.graphics.ImageFormat
import android.hardware.Camera
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import java.io.IOException

class CameraPreview(context: Context, private val surfaceView: SurfaceView) : SurfaceHolder.Callback {

    private var mHolder: SurfaceHolder = surfaceView.holder
    private var mCamera: Camera? = getCameraInstance()

    init {
        mHolder.addCallback(this)
    }

    companion object {
        fun getCameraInstance(): Camera? {
            return try {
                Camera.open()
            } catch (e: Exception) {
                Log.e("CameraPreview", "Camera is not available: ${e.message}")
                null
            }
        }
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        try {
            mCamera?.apply {
                setPreviewDisplay(holder)
                setDisplayOrientation(90) // 確保相機預覽畫面是正確方向
                setCameraParameters(this) // 設置相機參數
                startPreview()
            }
        } catch (e: IOException) {
            Log.d("CameraPreview", "Error setting camera preview: ${e.message}")
        }
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        mCamera?.stopPreview()
        mCamera?.release()
        mCamera = null
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        if (mHolder.surface == null) {
            return
        }

        try {
            mCamera?.stopPreview()
        } catch (e: Exception) {
            // 忽略此錯誤
        }

        try {
            mCamera?.apply {
                setPreviewDisplay(mHolder)
                setDisplayOrientation(90) // 確保相機預覽畫面是正確方向
                setCameraParameters(this) // 設置相機參數
                startPreview()
            }
        } catch (e: Exception) {
            Log.d("CameraPreview", "Error starting camera preview: ${e.message}")
        }
    }

    private fun setCameraParameters(camera: Camera) {
        val params = camera.parameters
        params.focusMode = Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE
        params.pictureFormat = ImageFormat.JPEG
        params.jpegQuality = 100 // 設置最高畫質
        camera.parameters = params
    }
}
