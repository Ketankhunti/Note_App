package com.example.notebook_app.activity.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.notebook_app.R
import com.example.notebook_app.activity.activity.ContainerAcivity
import com.example.notebook_app.activity.activity.DataShowingActivity
import com.example.notebook_app.activity.fragments.MainFragment
import com.example.notebook_app.activity.model.NoteBook
import com.example.notebook_app.activity.viewmodel.MainViewModel

class MyAdapter(private val context: MainFragment) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

     var notelist = ArrayList<NoteBook>()
    lateinit var mainViewModel: MainViewModel
    lateinit var navController: NavController
    @SuppressLint("NotifyDataSetChanged")
    fun setListItems(notelist:List<NoteBook>){
        this.notelist = notelist as ArrayList<NoteBook>
        notifyDataSetChanged()
    }

    fun deleteNote(noteBook: NoteBook){
        MainViewModel().deleteNote(context.requireContext() , noteBook)
    }


    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val title = itemView.findViewById<TextView>(R.id.title_list_item)
        val discription = itemView.findViewById<TextView>(R.id.desc_list_item)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_list,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = notelist[position].title
        holder.discription.text = notelist[position].discription
              holder.itemView.setOnClickListener {
             //     Toast.makeText(context.requireContext(),"${itemCount}",Toast.LENGTH_SHORT).show()
                  val intent = Intent(context.requireActivity(),DataShowingActivity::class.java)
                  intent.putExtra("title",notelist[position].title)
                  intent.putExtra("discription", notelist[position].discription)

                //  onitemclick.startActivity(intent)
                  context.requireActivity().startActivity(intent)
              }
    }

    override fun getItemCount(): Int {
        return this.notelist.size
    }


}