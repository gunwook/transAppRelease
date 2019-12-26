package ko.gunwook.translateapp.utils

object CodeUtils {

    const val TRANS_NAVER_PAGE = "https://korean.dict.naver.com/main.nhn?sLn=kr"


    object Network {
        const val MASTER_BASE_URL = "http://localhost:8080/"
        const val GOOGE_API_URL = "https://translation.googleapis.com/"

        object Api {
            const val LANGUAGE_TRANSLATE_V2 = "language/translate/v2"
        }

        object Params {
            const val Q = "q"
            const val TARGET = "target"
            const val SOURCE = "source"
            const val KEY = "key"
            const val KO = "ko"
            const val EN = "en"
        }
    }
}