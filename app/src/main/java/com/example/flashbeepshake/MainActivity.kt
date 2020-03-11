package com.example.flashbeepshake

import android.annotation.TargetApi
import android.content.Context
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.coroutines.Continuation

class MainActivity : AppCompatActivity() {

    private var flashLightStatus:Boolean=false

    @TargetApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        flash.setOnClickListener(){
            var cameraManager=getSystemService(Context.CAMERA_SERVICE) as CameraManager
            var cameraId=cameraManager.cameraIdList[0]
            if(!flashLightStatus) {
                try {
                    cameraManager.setTorchMode(cameraId, true)
                    flashLightStatus=true
                    Toast.makeText(applicationContext,"Flash On",Toast.LENGTH_LONG).show()
                } catch (e:CameraAccessException){}
                } else{
                try{
                    cameraManager.setTorchMode(cameraId,false)
                    flashLightStatus=false
                    Toast.makeText(applicationContext,"Flash Off",Toast.LENGTH_LONG).show()
                }catch(e:CameraAccessException){}
            }
        }

        beep.setOnClickListener(){
            val tone = ToneGenerator(AudioManager.STREAM_MUSIC,100)
            tone.startTone(android.media.ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD,500)
            Toast.makeText(applicationContext,"Beep",Toast.LENGTH_LONG).show()
        }

        shake.setOnClickListener(){
            var v=getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            v.vibrate(500)
            Toast.makeText(applicationContext,"Shake",Toast.LENGTH_LONG).show()
        }
    }
}
