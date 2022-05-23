package com.example.loadimgdogsm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.loadimgdogsm.databinding.BaseDogimgBinding
import com.squareup.picasso.Picasso

class CustomAdapter(var listDogs: List<String>):RecyclerView.Adapter<CustomAdapter.VHImgDogs>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHImgDogs {
        var layoutInflater = LayoutInflater.from(parent.context)
        return VHImgDogs(layoutInflater.inflate(R.layout.base_dogimg, parent, false))
    }

    override fun onBindViewHolder(holder: VHImgDogs, position: Int) {
        val imgLoad = listDogs[position]
        //cargamos nuestro imgLoad que contiene las posiciones y Picasso las convierte en imganenes
        holder.renderDogimg(imgLoad)
    }

    override fun getItemCount(): Int {
        return listDogs.size
    }

    inner class VHImgDogs(view: View):RecyclerView.ViewHolder(view){
        private val binding = BaseDogimgBinding.bind(view)
        fun renderDogimg(imgDogToString: String){
            //obtengo la url de tipo String y gracias a piccaso la convierto en img y la guardo en el ImgView
            Picasso.get().load(imgDogToString).into(binding.ivDog)
        }
    }
}