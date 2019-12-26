package ko.gunwook.translateapp.ui.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import ko.gunwook.translateapp.ui.fragment.RecentFragment
import ko.gunwook.translateapp.ui.fragment.SettingFragment

class MainPagerFragmentAdatper(activity : AppCompatActivity) : FragmentPagerAdapter(activity.supportFragmentManager,
    androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    val recentFragment : RecentFragment by lazy { RecentFragment.newInstance() }
    private val settingFragment : SettingFragment by lazy { SettingFragment.newInstance() }
    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 ->  recentFragment
            1 ->  settingFragment
            else -> recentFragment
        }
    }

    override fun getCount(): Int {
        return 2
    }
}