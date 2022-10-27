package com.example.task

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.example.task.Utils.NetworkResult
import com.example.task.ViewModel.UserDataViewModel
import com.example.task.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private val userDataViewModel: UserDataViewModel by viewModels()
    var search: CharSequence = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.txtLogin.setOnClickListener(this)
        binding.txtGraph.setOnClickListener(this)



    }

    private fun DataObservable() {
        binding.progressCircular.visibility = View.VISIBLE
        userDataViewModel.GetListResponse.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    binding.progressCircular.visibility = View.GONE
                    response.data?.let {
                        Log.e("Success",it.toString())
                        basicAlert(it.message,it.success)
                    }
                }

                is NetworkResult.Error -> {
                    binding.progressCircular.visibility = View.GONE
                    Toast.makeText(
                        this,
                        response.message,
                        Toast.LENGTH_SHORT
                    ).show()

                    Log.e("Error", response.message.toString())
                }

                is NetworkResult.Loading -> {
                    binding.progressCircular.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onClick(p0: View?) {
        when(p0?.id){

            R.id.txt_Login ->{
                var email =  binding.edEmail.text.toString().trim()
                var password =  binding.edPassword.text.toString().trim()
                var role =  binding.edRole.text.toString().trim()

                if (email.isEmpty() || password.isEmpty() || role.isEmpty()){
                    Toast.makeText(this,"all Filed is required",Toast.LENGTH_SHORT).show()
                }else{
                    userDataViewModel.fetchListDataResponse(email, password, role)
                    DataObservable()
                }

            }

            R.id.txt_graph ->{
                startActivity(Intent(this,MainActivity2::class.java))
            }

        }
    }

    fun basicAlert(message:String,success:String){

        val dialogBuilder = AlertDialog.Builder(this)


        dialogBuilder.setMessage("Message : "+message)
            // if the dialog is cancelable
            .setCancelable(false)
            // positive button text and action
            .setPositiveButton("ok", DialogInterface.OnClickListener {
                    dialog, id -> dialog.dismiss()
            })



        val alert = dialogBuilder.create()
        alert.setTitle("Success : "+success)
        alert.show()



    }
}