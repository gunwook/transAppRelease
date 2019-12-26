package ko.gunwook.translateapp.ui.viewholder

import android.content.Context
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import ko.gunwook.translateapp.BR
import ko.gunwook.translateapp.base.BaseViewHolder
import ko.gunwook.translateapp.databinding.CellListRecentBinding
import ko.gunwook.translateapp.db.entity.WordEntity
import ko.gunwook.translateapp.ui.viewModel.RecentViewModel

class RecentViewHolder(context : Context , @LayoutRes layoutRef : Int , parent : ViewGroup? , viewmodel : RecentViewModel) : BaseViewHolder<WordEntity>(context,layoutRef,parent){

    private val binding : CellListRecentBinding = DataBindingUtil.bind(itemView)!!
    init {
        binding.setVariable(BR.viewmodel, viewmodel)
    }
    override fun onViewCreated(item: WordEntity?) {
        binding.setVariable(BR.data, item)
    }
}