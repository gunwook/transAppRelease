package ko.gunwook.translateapp.base

import android.content.Context
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter(val context:Context,private val viewModel: BaseAdapterViewModel) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun getItemCount(): Int {
        return viewModel.getItemCount()
    }

    override fun getItemViewType(position: Int): Int {
        return viewModel.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? BaseViewHolder<*>)?.onBindViewHolder(viewModel.getItem(position))
    }
}