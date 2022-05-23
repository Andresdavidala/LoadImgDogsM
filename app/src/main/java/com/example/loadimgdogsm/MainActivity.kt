package com.example.loadimgdogsm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loadimgdogsm.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener{
    private lateinit var binding : ActivityMainBinding
    lateinit var adapter: CustomAdapter
    private  var listofImgDogs = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.svImgDogs.setOnQueryTextListener(this)
        recyclerDogView()
    }
    private fun getRetrofit():Retrofit{
        return Retrofit.Builder().baseUrl("https://dog.ceo/api/breed/").
        addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun recyclerDogView(){
        adapter = CustomAdapter(listofImgDogs)
        binding.rvDogsImgs.layoutManager = LinearLayoutManager(this)

        binding.rvDogsImgs.adapter = adapter

    }

    private fun CorrutinImgDosg(query: String){
        CoroutineScope(Dispatchers.IO).launch {
            //declaramos una variable call que contendra la ruta base y ademas el fragmento de ruta que cambiara
            val call = getRetrofit().create(ImgDogsInterface::class.java).getDogCamposImg("$query/images")
            val callBody = call.body()
            runOnUiThread {
                if(call.isSuccessful){
                    //declaramos una variable de imagenes
                    val images = callBody?.imgsDos ?: emptyList()
                    //agregamos las imagenes a la lista mutable creada en la linea 17
                    listofImgDogs.clear()
                    listofImgDogs.addAll(images)
                    adapter.notifyDataSetChanged()
                }else{
                    showError()
                }

                hidekeyboard()
            }

        }
    }

    private fun hidekeyboard() {
        val imn = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imn.hideSoftInputFromWindow(binding.viewRoot.windowToken, 0)
    }

    private fun showError() {
        Toast.makeText(this, "Error en coincidencias", Toast.LENGTH_SHORT).show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(!query.isNullOrEmpty()){
            CorrutinImgDosg(query.toLowerCase())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}