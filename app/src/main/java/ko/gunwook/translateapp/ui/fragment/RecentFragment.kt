package ko.gunwook.translateapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ko.gunwook.translateapp.BR
import ko.gunwook.translateapp.R
import ko.gunwook.translateapp.ui.viewModel.RecentViewModel
import ko.gunwook.translateapp.databinding.FragmentRecentBinding
import ko.gunwook.translateapp.ui.adapter.RecentAdapter
import java.lang.Exception

class RecentFragment : Fragment() {

    private lateinit var viewModel : RecentViewModel
    private lateinit var fragmentRecentBinding:  FragmentRecentBinding
    private lateinit var adapter : RecentAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        fragmentRecentBinding = DataBindingUtil.inflate<FragmentRecentBinding>(inflater, R.layout.fragment_recent,container,false)
        initView()
        initData()
        return fragmentRecentBinding.root
    }

    private fun initView() {
        try {
            activity?.let {
                viewModel = ViewModelProviders.of(it).get(RecentViewModel::class.java)

                adapter = RecentAdapter(it, viewModel)
                fragmentRecentBinding.recyclerView.layoutManager = LinearLayoutManager(it, RecyclerView.VERTICAL, false)
                fragmentRecentBinding.recyclerView.adapter = adapter
                fragmentRecentBinding.setVariable(BR.viewmodel,viewModel)
                fragmentRecentBinding.lifecycleOwner = this
            }
        }catch (e : Exception){
            e.printStackTrace()
        }
    }

    fun initData(){
        viewModel.initData()
    }

    companion object {
        fun newInstance(): RecentFragment {
            return RecentFragment()
        }
    }
}