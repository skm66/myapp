package com.example.myapplication.ui.dashboard.addProduct

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityAddProductBinding
import com.example.myapplication.ui.dashboard.DashboardItem
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.io.File

class AddProductActivity : AppCompatActivity() {

    lateinit var imageUri : Uri

    lateinit var arrList : ArrayList<AddImageItem>

    private val binding: ActivityAddProductBinding by lazy{
        ActivityAddProductBinding.inflate(layoutInflater)
    }

    val contractCamera = registerForActivityResult(ActivityResultContracts.TakePicture()){
//        binding.imgVwAddImage.visibility = View.VISIBLE
//        binding.imgVwAddImage.setImageURI(null)
//        binding.imgVwAddImage.setImageURI(imageUri)
        arrList= ArrayList()

        binding.rlMedia.visibility=View.GONE
        binding.rlShowImg.visibility=View.VISIBLE

        arrList.add(AddImageItem(imageUri))

        showImgRecyclerView(arrList)

    }

//    val contractGallery = registerForActivityResult(ActivityResultContracts.GetContent()){
//        binding.imgVwAddImage.visibility = View.VISIBLE
//        //binding.imgVwAddImage.setImageURI(null)
//        binding.imgVwAddImage.setImageURI(it)
//    }

    val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == Activity.RESULT_OK) { //if images are selected
            val intent = it.data
            arrList= ArrayList()
            Log.e("aaa", "success")
            // Handle the Intent

            if(it.data?.clipData!=null){
                var cnt = it.data?.clipData?.itemCount

                for(i in 0..cnt!!-1){
                    arrList.add(AddImageItem(it.data?.clipData?.getItemAt(i)!!.uri))
                }
            }

            else if(it.data?.data!=null){
                arrList.add(AddImageItem(it.data?.data!!))
            }

            binding.rlMedia.visibility=View.GONE
            binding.rlShowImg.visibility=View.VISIBLE

            //addImgData()
            showImgRecyclerView(arrList)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        imageUri = createImageUri()!!

        binding.rlMedia.setOnClickListener {

            CreateCustomDialog()
            //contract.launch(imageUri)
        }

        binding.rlEditProd.setOnClickListener {
            CreateCustomDialog()
            Toast.makeText(this,"clicked!!",Toast.LENGTH_LONG).show()
        }

    }

    private fun createImageUri(): Uri? {
        val image = File(applicationContext.filesDir, "camera_image.png")
        return FileProvider.getUriForFile(applicationContext,
        "com.example.myapplication.fileProvider", image)
    }

    fun CreateCustomDialog(){
        // on below line we are creating a new bottom sheet dialog.
        val dialog = BottomSheetDialog(this)

        // on below line we are inflating a layout file which we have created.
        val view = layoutInflater.inflate(R.layout.bottomsheet_layout, null)

        val camera = view.findViewById<RelativeLayout>(R.id.rl_camera)

        val gallery =view.findViewById<RelativeLayout>(R.id.rl_gallery)

        camera.setOnClickListener {
            contractCamera.launch(imageUri)
            dialog.dismiss()
        }

        gallery.setOnClickListener {
            //st.launch("image/*")

            val intent= Intent()
            intent.type= "image/*"
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.action = Intent.ACTION_GET_CONTENT
            startForResult.launch(intent)
            dialog.dismiss()
        }

        // on below line we are setting
        // content view to our view.
        dialog.setContentView(view)

        // on below line we are calling
        // a show method to display a dialog.
        dialog.show()

    }

    private fun showImgRecyclerView(arrayImg : ArrayList<AddImageItem>){
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerVwImgShow.layoutManager=layoutManager
        binding.recyclerVwImgShow.adapter=ShowImgAdapter(this, arrayImg)
    }

    private fun addImgData() {

    }

}