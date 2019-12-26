package ko.gunwook.translateapp.modules.network

import android.content.Context
import ko.gunwook.translateapp.R
import ko.gunwook.translateapp.utils.CodeUtils
import ko.gunwook.translateapp.utils.ParseUtils
import java.io.IOException

class GoogleHelper(var googleApi : GoogleApi) {

    @Throws(IOException::class)
    suspend fun requestTransWord(context : Context, q : String) : String? {
        return ParseUtils.parseResponse(googleApi.getTransWord(q,CodeUtils.Network.Params.EN, context.getString(R.string.api_key),CodeUtils.Network.Params.KO))
            ?.getJSONObject("data")
            ?.getJSONArray("translations")
            ?.getJSONObject(0)
            ?.getString("translatedText")
    }
}