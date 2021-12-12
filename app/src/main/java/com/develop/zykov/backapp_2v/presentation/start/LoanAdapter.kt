package com.develop.zykov.backapp_2v.presentation.start

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.develop.zykov.backapp_2v.data.loan.remote.dto.LoanResponse


class LoanAdapter(private val onClick: (LoanResponse) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var dataList: List<LoanResponse> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        LoanViewHolder(parent, onClick)

    override fun getItemCount(): Int =
        dataList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is LoanViewHolder -> holder.bind(dataList[position])
            else -> error("Wrong view holder")
        }
    }

}