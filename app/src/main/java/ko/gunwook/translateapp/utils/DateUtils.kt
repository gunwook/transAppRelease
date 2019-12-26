package ko.gunwook.translateapp.utils

import android.text.format.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


object DateUtils {

    private val TAG = "DateUtils"

    val DATE_FORMAT_1 = "hh:mm a"
    val DATE_FORMAT_2 = "h:mm a"
    val DATE_FORMAT_3 = "yyyy-MM-dd"
    val DATE_FORMAT_4 = "dd-MMMM-yyyy"
    val DATE_FORMAT_5 = "dd MMMM yyyy"
    val DATE_FORMAT_6 = "dd MMMM yyyy zzzz"
    val DATE_FORMAT_7 = "EEE, MMM d, ''yy"
    val DATE_FORMAT_8 = "yyyy-MM-dd HH:mm:ss"
    val DATE_FORMAT_9 = "h:mm a dd MMMM yyyy"
    val DATE_FORMAT_10 = "K:mm a, z"
    val DATE_FORMAT_11 = "hh 'o''clock' a, zzzz"
    val DATE_FORMAT_12 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    val DATE_FORMAT_13 = "E, dd MMM yyyy HH:mm:ss z"
    val DATE_FORMAT_14 = "yyyy.MM.dd G 'at' HH:mm:ss z"
    val DATE_FORMAT_15 = "yyyyy.MMMMM.dd GGG hh:mm aaa"
    val DATE_FORMAT_16 = "EEE, d MMM yyyy HH:mm:ss Z"
    val DATE_FORMAT_17 = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
    val DATE_FORMAT_18 = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"


    val currentDate: String
        get() {
            val dateFormat = SimpleDateFormat(DATE_FORMAT_1,Locale.KOREA)
            dateFormat.timeZone = TimeZone.getTimeZone("UTC")
            val today = Calendar.getInstance().getTime()
            return dateFormat.format(today)
        }

    val currentTime: String
        get() {
            val dateFormat = SimpleDateFormat(DATE_FORMAT_1,Locale.KOREA)
            dateFormat.timeZone = TimeZone.getTimeZone("UTC")
            val today = Calendar.getInstance().getTime()
            return dateFormat.format(today)
        }

    /**
     * @param time        in milliseconds (Timestamp)
     * @param mDateFormat SimpleDateFormat
     * @return
     */
    fun getDateTimeFromTimeStamp(time: Long, mDateFormat: String): String {
        val dateFormat = SimpleDateFormat(mDateFormat,Locale.KOREA)
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val dateTime = Date(time)
        return dateFormat.format(dateTime)
    }

    /**
     * Get Timestamp from date and time
     *
     * @param mDateTime   datetime String
     * @param mDateFormat Date Format
     * @return
     * @throws ParseException
     */
    @Throws(ParseException::class)
    fun getTimeStampFromDateTime(mDateTime: String, mDateFormat: String): Long {
        val dateFormat = SimpleDateFormat(mDateFormat,Locale.KOREA)
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val date = dateFormat.parse(mDateTime)
        return date.getTime()
    }

    /**
     * Return  datetime String from date object
     *
     * @param mDateFormat format of date
     * @param date        date object that you want to parse
     * @return
     */
    fun formatDateTimeFromDate(mDateFormat: String, date: Date?): String? {
        return if (date == null) {
            null
        } else DateFormat.format(mDateFormat, date).toString()
    }

    /**
     * Convert one date format string  to another date format string in android
     * @param inputDateFormat Input SimpleDateFormat
     * @param outputDateFormat Output SimpleDateFormat
     * @param inputDate  input Date String
     * @return
     * @throws ParseException
     */
    @Throws(ParseException::class)
    fun formatDateFromDateString(inputDateFormat: String, outputDateFormat: String, inputDate: String): String {
        val mParsedDate: Date?
        val mOutputDateString: String?
        val mInputDateFormat = SimpleDateFormat(inputDateFormat, java.util.Locale.getDefault())
        val mOutputDateFormat = SimpleDateFormat(outputDateFormat, java.util.Locale.getDefault())
        mParsedDate = mInputDateFormat.parse(inputDate)
        mOutputDateString = mOutputDateFormat.format(mParsedDate!!)
        return mOutputDateString
    }
}