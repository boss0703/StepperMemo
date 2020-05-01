package com.example.steppermemo.ui.home

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.steppermemo.EditActivity
import com.example.steppermemo.R
import com.example.steppermemo.StepperMemo
import io.realm.RealmResults

class HomeFragmentRecyclerViewAdapter(realmResults: RealmResults<StepperMemo>) :
RecyclerView.Adapter<HomeFragmentViewHolder>(){
    private val rResults: RealmResults<StepperMemo> = realmResults

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): HomeFragmentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.one_result, parent, false)
        val viewholder = HomeFragmentViewHolder(view)
        return viewholder
    }

    override fun getItemCount(): Int {
        return rResults.size
    }

    override fun onBindViewHolder(holder: HomeFragmentViewHolder, position: Int) {
        val stepperMemo = rResults[position]
        holder.dateText?.text = stepperMemo?.date.toString()
        holder.countText?.text = "${stepperMemo?.count.toString()}回"
        holder.timeText?.text = stepperMemo?.time.toString()
        holder.kcalText?.text = "%.1f".format(stepperMemo?.kcal)
        holder.memoText?.text = stepperMemo?.memo.toString()
        // 交互に背景色変更
        holder.itemView.setBackgroundColor(if (position % 2 == 0) Color.LTGRAY else Color.WHITE)
        // itemVIewが押下された時の処理
        holder.itemView.setOnClickListener{
            val intent = Intent(it.context, EditActivity::class.java)
            intent.putExtra("id", stepperMemo?.id)
            it.context.startActivity(intent)
        }
    }
}