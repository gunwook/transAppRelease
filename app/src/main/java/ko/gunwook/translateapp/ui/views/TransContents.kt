package ko.gunwook.translateapp.ui.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import io.mattcarroll.hover.Content
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.AsyncSubject
import ko.gunwook.translateapp.databinding.CellTransViewBinding
import ko.gunwook.translateapp.db.DatabaseHelper
import ko.gunwook.translateapp.utils.CodeUtils
import ko.gunwook.translateapp.utils.Logger

class TransContents(@NonNull var context: Context) :
    Content , WebViewClient()  {

    lateinit var rootView : CellTransViewBinding


    private var subject = AsyncSubject.create<String>()

    private var isRedirect = false
    private val disposables by lazy {
        CompositeDisposable()
    }

    // Make sure that this method returns the SAME View.  It should NOT create a new View each time
    // that it is invoked.
    @NonNull
    override fun getView(): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        rootView = DataBindingUtil.inflate<CellTransViewBinding>(inflater,
            ko.gunwook.translateapp.R.layout.cell_trans_view,null,false)

        rootView.webView.let {
           it.webViewClient = this
           it.loadUrl(CodeUtils.TRANS_NAVER_PAGE) // 웹뷰에 표시할 웹사이트 주소, 웹뷰 시작
        }

        return rootView.root
    }

    override fun isFullscreen(): Boolean {
        return true
    }

    override fun onShown() {
        // No-op.
    }

    override fun onHidden() {
    }


    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        if (!isRedirect){
            subject = AsyncSubject.create<String>()
            disposables.add(
                subject.subscribe {
                    DatabaseHelper().saveDBWord(it,false)
                }
            )
            isRedirect = true
        }

        request?.let {
            request.url.getQueryParameter("query")?.let {
                subject.onNext(it)
            }
        }

        return super.shouldOverrideUrlLoading(view, request)
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)

        isRedirect = false
        subject.onComplete()
    }

    override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
        super.onReceivedError(view, request, error)

        Logger.d("WebView onReceivedError")
    }
}