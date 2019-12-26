package ko.gunwook.translateapp.ui.adapter

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import ko.gunwook.translateapp.db.entity.WordEntity

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("bind:recent_item")
    fun bindItem(recyclerView : RecyclerView , data : LiveData<List<WordEntity>>?) {

        data?.value?.let {
            if (it.isNotEmpty()){
                recyclerView.adapter?.notifyDataSetChanged()
            }
        }
    }

}