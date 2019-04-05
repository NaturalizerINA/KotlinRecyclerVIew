package com.mukminullah.highorderfunction

import android.graphics.Rect
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_list.view.*

class MainActivity : AppCompatActivity() {

    var listBiodata : ArrayList<ModelBiodata> = ArrayList()
    lateinit var adapterBiodata : AdapterRecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
    }

    fun initRecyclerView() {
        val llm = LinearLayoutManager(this)
        rvBiodata.layoutManager = llm
        val itemDecoration = RecyclerViewMargin(20)
        rvBiodata.addItemDecoration(itemDecoration)
        listBiodata = initData()
        adapterBiodata = AdapterRecyclerView(
            listBiodata,
            { item: ModelBiodata -> onBiodataClicked(item) })
        rvBiodata.adapter = adapterBiodata
    }

    class RecyclerViewMargin(private var margin: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            val position = parent.getChildAdapterPosition(view)
            if(position == 0) outRect.top = margin
            outRect.bottom = margin
            outRect.left = margin
            outRect.right = margin
        }
    }

    class AdapterRecyclerView(
        private val listBiodata: ArrayList<ModelBiodata> = ArrayList(),
        private val onClickFunction : (ModelBiodata) -> Unit
    ) : RecyclerView.Adapter<AdapterRecyclerView.MyViewHolder>() {

        init { notifyDataSetChanged() }

        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
            val view : View = LayoutInflater.from(p0.context).inflate(R.layout.item_list, p0, false)
            return MyViewHolder(view)
        }

        override fun getItemCount(): Int {
            return listBiodata?.size
        }

        override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
            p0.bind(listBiodata.get(p1), onClickFunction)
        }


        class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

            fun bind(item: ModelBiodata, clickListener: (ModelBiodata) -> Unit){
                view.itemName.text = item.nama
                view.itemEmail.text = item.email
                view.setOnClickListener {clickListener(item)}
            }
        }
    }

    fun initData() : ArrayList<ModelBiodata> {
        for (i in 1..10) {
            val model =
                ModelBiodata("Rahmad SM", "rahmad.mukminullah@gmail.com")
            listBiodata.add(model)
        }
        return listBiodata
    }

    data class ModelBiodata(var nama:String, var email:String)

    fun onBiodataClicked (item : ModelBiodata) {
        Toast.makeText(this, "You click person with name: ${item.nama} and with email: ${item.email}", Toast.LENGTH_LONG).show()
    }
}
