package com.example.steppermemo.ui.home

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.one_result.view.*

class HomeFragmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var dateText: TextView? = null
    var countText: TextView? = null
    var timeText: TextView? = null
    var kcalText: TextView? = null
    var memoText: TextView? = null

    init {
        // viewholderとlayoutのviewの紐づけ
        dateText = itemView.dateText
        countText = itemView.countView
        timeText = itemView.timeView
        kcalText = itemView.kcalView
        memoText = itemView.memoView
    }
}