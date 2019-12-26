package ko.gunwook.translateapp.services

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import io.mattcarroll.hover.HoverMenu
import io.mattcarroll.hover.HoverView
import io.mattcarroll.hover.window.HoverMenuService
import ko.gunwook.translateapp.R
import ko.gunwook.translateapp.ui.views.TransContents
import java.util.*

class TransManagerService : HoverMenuService() {

    override fun onHoverMenuLaunched(@NonNull intent: Intent, @NonNull hoverView: HoverView) {
        hoverView.setMenu(createHoverMenu())
        hoverView.collapse()
    }

    @NonNull
    private fun createHoverMenu(): HoverMenu {
        return ReorderingSectionHoverMenu(getApplicationContext())
    }

    private class ReorderingSectionHoverMenu internal constructor(@param:NonNull private val mContext: Context) :
        HoverMenu() {
        private val mSections = ArrayList<HoverMenu.Section>()

        init {
            insertTab(0)
        }

        private fun createTabView(): View {
            val imageView = ImageView(mContext)
            imageView.setImageResource(R.drawable.tab_background)
            imageView.scaleType = ImageView.ScaleType.FIT_CENTER
            return imageView
        }

        override fun getId(): String {
            return "menu"
        }

        override fun getSectionCount(): Int {
            return mSections.size
        }

        @Nullable
        override fun getSection(index: Int): HoverMenu.Section {
            return mSections[index]
        }

        @Nullable
        override fun getSection(@NonNull sectionId: HoverMenu.SectionId): HoverMenu.Section? {
            for (section in mSections) {
                if (section.getId() == sectionId) {
                    return section
                }
            }
            return null
        }

        @NonNull
        override fun getSections(): List<HoverMenu.Section> {
            return ArrayList<Section>(mSections)
        }

        private fun insertTab(position: Int) {
            val id = Integer.toString(mSections.size)
            mSections.add(
                position, HoverMenu.Section(
                    HoverMenu.SectionId(id),
                    createTabView(),
                    TransContents(mContext)
                )
            )
            notifyMenuChanged()
        }
    }
}
