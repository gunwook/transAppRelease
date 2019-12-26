package ko.gunwook.translateapp.modules.network

import ko.gunwook.translateapp.utils.CodeUtils
import okhttp3.ResponseBody
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface GoogleApi {

    @FormUrlEncoded
    @POST(CodeUtils.Network.Api.LANGUAGE_TRANSLATE_V2)
    suspend fun getTransWord(@Field(CodeUtils.Network.Params.Q) q : String,
                        @Field(CodeUtils.Network.Params.SOURCE) source : String,
                        @Field(CodeUtils.Network.Params.KEY) key : String,
                        @Field(CodeUtils.Network.Params.TARGET) target : String) : ResponseBody
}