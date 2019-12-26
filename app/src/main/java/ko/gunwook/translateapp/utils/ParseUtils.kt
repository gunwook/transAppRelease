package ko.gunwook.translateapp.utils

import okhttp3.ResponseBody
import org.json.JSONObject

object ParseUtils {
    fun parseResponse(body: ResponseBody?): JSONObject? {
        try {
            return JSONObject(body?.string() ?: "")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}