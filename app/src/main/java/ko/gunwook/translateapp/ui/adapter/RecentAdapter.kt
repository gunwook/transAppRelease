package ko.gunwook.translateapp.ui.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ko.gunwook.translateapp.R
import ko.gunwook.translateapp.base.BaseRecyclerAdapter
import ko.gunwook.translateapp.ui.viewModel.RecentViewModel
import ko.gunwook.translateapp.ui.viewholder.RecentViewHolder

class RecentAdapter(private val mContext : Context ,private val viewModel : RecentViewModel) : BaseRecyclerAdapter(mContext,viewModel){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RecentViewHolder(mContext,R.layout.cell_list_recent,parent,viewModel)
    }
}