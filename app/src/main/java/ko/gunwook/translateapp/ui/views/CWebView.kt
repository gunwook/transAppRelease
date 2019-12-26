package ko.gunwook.translateapp.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView


class CWebView : WebView {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        settings.setSupportMultipleWindows(false) // 새창 띄우기 허용 여부
        settings.javaScriptCanOpenWindowsAutomatically = false // 자바스크립트 새창 띄우기(멀티뷰) 허용 여부
        settings.loadWithOverviewMode = true  // 메타태그 허용 여부
        settings.javaScriptEnabled = true
        settings.useWideViewPort = true // 화면 사이즈 맞추기 허용 여부
        settings.setSupportZoom(false) // 화면 줌 허용 여부
        settings.builtInZoomControls = false // 화면 확대 축소 허용 여부
        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING// 컨텐츠 사이즈 맞추기
        settings.cacheMode = WebSettings.LOAD_NO_CACHE// 브라우저 캐시 허용 여부
        settings.domStorageEnabled = true // 로컬저장소 허용 여부
        isScrollbarFadingEnabled = true
        scrollBarStyle = SCROLLBARS_OUTSIDE_OVERLAY
        setLayerType(View.LAYER_TYPE_HARDWARE, null);
    }
}