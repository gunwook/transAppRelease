package ko.gunwook.translateapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItemsSingleChoice
import ko.gunwook.translateapp.BR
import ko.gunwook.translateapp.R
import ko.gunwook.translateapp.databinding.FragmentSettingBinding
import ko.gunwook.translateapp.ui.viewModel.SettingViewModel
import ko.gunwook.translateapp.utils.PreferenceHelper

class SettingFragment : Fragment() {

    private lateinit var fragmentSettingBinding:  FragmentSettingBinding
    lateinit var viewModel : SettingViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        fragmentSettingBinding = DataBindingUtil.inflate<FragmentSettingBinding>(inflater, R.layout.fragment_setting,container,false)
        fragmentSettingBinding.setVariable(BR.view , this)
        initView()
        return fragmentSettingBinding.root
    }

    private fun initView() {
        try {
            activity?.let {
                viewModel = ViewModelProviders.of(it).get(SettingViewModel::class.java)
                viewModel.liveData.observe(this, Observer {
                    fragmentSettingBinding.switchBt.isChecked = it
                })
                fragmentSettingBinding.switchBt.setOnCheckedChangeListener { _ , isChecked ->
                    if (isChecked){
                        viewModel.textCopySetting(isChecked)
                    }else {
                        viewModel.textCopySetting(isChecked)
                    }
                }


                viewModel.initData()
            }
        }catch (e : Exception){
            e.printStackTrace()
        }
    }

    fun showDialog(){
        MaterialDialog(activity!!).show {
            listItemsSingleChoice(
                R.array.searchType, initialSelection = PreferenceHelper.getInstance(context).getInt(
                    PreferenceHelper.SHARED_PREFS_KEY_SEARCH_TYPE,0)) { dialog, index, text ->

                    viewModel.sendEvent(index)
            }
        }
    }


    companion object {
        fun newInstance(): SettingFragment {
            return SettingFragment()
        }
    }
}