package com.develop.zykov.backapp_2v.presentation.start

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.develop.zykov.backapp_2v.R
import com.develop.zykov.backapp_2v.data.loan.remote.dto.LoanResponse
import kotlinx.android.synthetic.main.item_start.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat

class LoanViewHolder(parent: ViewGroup, private val onClick: (LoanResponse) -> Unit) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_start, parent, false)
    ) {

    private val pattern = "MM.dd.yyyy HH:mm"
    private val df: DateFormat = SimpleDateFormat(pattern)

    fun bind(item: LoanResponse) {
        itemView.loan_amount_text_view.text = item.amount
        itemView.loan_date_text_view.text = df.format(item.date)
        itemView.loan_state_text_view.text = item.state
        itemView.item_card.setOnClickListener { onClick(item) }
    }

}