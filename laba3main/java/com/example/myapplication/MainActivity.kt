package com.example.myapplication

import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import java.io.File


//INTENT_ACTION_STILL_IMAGE_CAMERA

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var alreadyExecuted = false

    private var ctr: Int = 0

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if(!alreadyExecuted) {
            loadPhotos();
            alreadyExecuted = true;
        }
        binding.cameraButton.setOnClickListener(){
            getPicture()
            if(alreadyExecuted) {
                openPhoto()
                ctr++
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getPicture() {
        val mPhotoUri = contentResolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            ContentValues()
        )
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoUri)
        startActivity(intent)

    }

    private fun openPhoto() {
        val sdcardPath = File(
            Environment.getExternalStorageDirectory()
                .path + "/Pictures"
        )
        var images: Array<File> = sdcardPath.listFiles();
        var last: Int = images.lastIndex - 13

        var bmp: Bitmap = BitmapFactory.decodeFile(sdcardPath.listFiles()[last].absolutePath)

        if(ctr > 3)
            ctr = 0

        if(ctr == 1){
            binding.photo1.setImageBitmap(bmp)
        binding.photo1.rotation = 90F
        val layoutParams: ViewGroup.LayoutParams = binding.photo1.layoutParams
        layoutParams.width = 470
        binding.photo1.layoutParams = layoutParams
        }

        if(ctr == 2){
            binding.photo2.setImageBitmap(bmp)
            binding.photo2.rotation = 90F
            val layoutParams: ViewGroup.LayoutParams = binding.photo2.layoutParams
            layoutParams.width = 470
            binding.photo2.layoutParams = layoutParams
        }
        if(ctr == 3){
            binding.photo3.setImageBitmap(bmp)
            binding.photo3.rotation = 90F
            val layoutParams: ViewGroup.LayoutParams = binding.photo3.layoutParams
            layoutParams.width = 470
            binding.photo3.layoutParams = layoutParams
        }
        if(ctr == 4){
            binding.photo4.setImageBitmap(bmp)
            binding.photo4.rotation = 90F
            val layoutParams: ViewGroup.LayoutParams = binding.photo4.layoutParams
            layoutParams.width = 470
            binding.photo4.layoutParams = layoutParams
        }

    }

    private fun loadPhotos() {
        val sdcardPath = File(
            Environment.getExternalStorageDirectory()
                .path + "/Pictures"
        )
        var images: Array<File> = sdcardPath.listFiles();
        var last: Int = images.lastIndex - 13

        if(last != 0) {
            var bmp1: Bitmap = BitmapFactory.decodeFile(sdcardPath.listFiles()[last].absolutePath)
            binding.photo1.setImageBitmap(bmp1)
            binding.photo1.rotation = 90F
            val layoutParams1: ViewGroup.LayoutParams = binding.photo1.layoutParams
            layoutParams1.width = 470
            binding.photo1.layoutParams = layoutParams1
        }
        if(last>1) {
            var bmp2: Bitmap =
                BitmapFactory.decodeFile(sdcardPath.listFiles()[last - 1].absolutePath)
            binding.photo2.setImageBitmap(bmp2)
            binding.photo2.rotation = 90F
            val layoutParams2: ViewGroup.LayoutParams = binding.photo2.layoutParams
            layoutParams2.width = 470
            binding.photo2.layoutParams = layoutParams2
        }
        if(last>2) {
            var bmp3: Bitmap =
                BitmapFactory.decodeFile(sdcardPath.listFiles()[last - 2].absolutePath)
            binding.photo3.setImageBitmap(bmp3)
            binding.photo3.rotation = 90F
            val layoutParams3: ViewGroup.LayoutParams = binding.photo3.layoutParams
            layoutParams3.width = 470
            binding.photo3.layoutParams = layoutParams3
        }
        if(last>3) {
            var bmp4: Bitmap =
                BitmapFactory.decodeFile(sdcardPath.listFiles()[last - 3].absolutePath)
            binding.photo4.setImageBitmap(bmp4)
            binding.photo4.rotation = 90F
            val layoutParams4: ViewGroup.LayoutParams = binding.photo4.layoutParams
            layoutParams4.width = 470
            binding.photo4.layoutParams = layoutParams4

        }
    }
}