package yzl.swu.recyclerviewtest.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import yzl.swu.recyclerviewtest.R

class MainAdapter: RecyclerView.Adapter<MainAdapter.MyViewHold> {

    lateinit var imageDataSource:ArrayList<Int>

    constructor(images:ArrayList<Int>){
        this.imageDataSource = images
    }

    //确定每个item的具体视图
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHold {
        //获取xml的视图  xml-》View
        //如果知道一个View  就可以通过这个view获取其上下文content
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.main_recycler_item,parent,false)
//        val itemView = layoutInflater.inflate(R.layout.card_item,parent,false)
        itemView.setOnClickListener{
            //点击事件
        }
        return  MyViewHold(itemView)
    }


    //视图解析出来之后 需不需要将数据绑定到上面
    @SuppressLint("WrongConstant")
    override fun onBindViewHolder(holder: MyViewHold, position: Int) {
        holder.iconImageView.setImageResource(imageDataSource[position])
        holder.titleView.text = if (position%2 ==0) "银河以北，吾彦最美" else "银河以南，为彦而燃"
        holder.iconImageView.setOnClickListener{
            Toast.makeText(holder.iconImageView.context,holder.titleView.text,1000).show()
        }
    }


    //确定RecyclerView中有多少个 Item
    override fun getItemCount(): Int {
        return imageDataSource.size
    }

    //item点击事件



    //传递来的view 是RecyclerView显示的每一个item
    class MyViewHold: RecyclerView.ViewHolder{
        lateinit var titleView:TextView
        lateinit var iconImageView: ImageView
        constructor(itemView:View):super(itemView){
            titleView = itemView.findViewById(R.id.titleTextView)
            iconImageView = itemView.findViewById(R.id.iconImageView)
        }
    }

}