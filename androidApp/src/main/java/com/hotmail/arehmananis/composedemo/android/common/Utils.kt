package com.hotmail.arehmananis.composedemo.android.common

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.provider.ContactsContract
import android.provider.MediaStore
import android.provider.Settings
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.FileProvider
import androidx.fragment.app.FragmentActivity
import java.io.File
import java.io.FileOutputStream
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField
import java.util.Date
import java.util.Locale
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.random.Random


object Utils {
    @JvmStatic
    fun dpToPx(resource: Resources, dp: Float): Float = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        resource.displayMetrics
    )

    @JvmStatic
    fun hideSoftKeyboard(activity: FragmentActivity) {
        val inputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        val currentFocusedView = activity.currentFocus
        if (currentFocusedView != null) {
            inputMethodManager.hideSoftInputFromWindow(
                currentFocusedView.windowToken, 0
            )
        }
    }

    fun FragmentActivity.showNumericKeyboard(editText: EditText) {
        editText.requestFocus()
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
        editText.inputType = android.text.InputType.TYPE_CLASS_NUMBER
    }

    fun FragmentActivity.copyToClipboard(textToCopy: String) {
        val clipboardManager =
            getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("Text", textToCopy)
        clipboardManager.setPrimaryClip(clipData)
    }

    @JvmStatic
    fun applyDrawableTint(textView: TextView, color: Int) {
        for (drawable in textView.compoundDrawablesRelative) {
            drawable?.let { it.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN) }
        }
    }


    fun bitmapToDrawable(context: Context, bitmap: Bitmap): Drawable {
        return BitmapDrawable(context.resources, bitmap)
    }

    @JvmStatic
    fun getUserInitials(fullName: String): String {
        return fullName.split(" ")
            .filter { it.isNotEmpty() && !it.equals("-", ignoreCase = true) }
            .map { it[0].uppercaseChar() }
            .take(2)
            .joinToString("")
    }

    @JvmStatic
    fun formatIsoTimestamp(isoTimestamp: String?): String? {
        return isoTimestamp?.let {
            val parsedDateTime = ZonedDateTime.parse(it, DateTimeFormatter.ISO_DATE_TIME)
            val formatter = DateTimeFormatterBuilder()
                .appendPattern("dd-MM-yy hh:mm a")
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                .toFormatter()
            parsedDateTime.format(formatter)
        }
    }

    @JvmStatic
    fun isoTimestampToHMS(isoTimestamp: String?): String? {
        return isoTimestamp?.let {
            val parsedDateTime = ZonedDateTime.parse(it, DateTimeFormatter.ISO_DATE_TIME)
            val currentDateTime = ZonedDateTime.now()
            val duration = Duration.between(currentDateTime, parsedDateTime)
            val hours = duration.toHours()
            val minutes = duration.toMinutes() % 60
            val seconds = duration.seconds % 60
            String.format(Locale.getDefault(), "%03d:%02d:%02d", hours, minutes, seconds)
        }
    }

    @JvmStatic
    fun formatDateTime(date: Date): String {
        val sdf = SimpleDateFormat("hh:mm a dd-MM-yyyy", Locale.ENGLISH)
        return sdf.format(date)

    }
}

fun Double.stripTrailingZeros(): String {
    return BigDecimal(this).stripTrailingZeros().toPlainString()
}

fun View.showKeyboard() {
    this.requestFocus()
    this.postDelayed({
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }, 100)
}

fun FragmentActivity.hideSoftKeyboard() {
    val inputMethodManager =
        this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    val currentFocusedView = this.currentFocus
    if (currentFocusedView != null) {
        inputMethodManager.hideSoftInputFromWindow(
            currentFocusedView.windowToken, 0
        )
    }
}

fun FragmentActivity.showPasswordKeyboard(editText: EditText) {
    editText.requestFocus()
    val inputMethodManager =
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
    editText.inputType =
        android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
}

@SuppressLint("HardwareIds")
fun Context.deviceId(): String {
    return Settings.Secure.getString(this.contentResolver, Settings.Secure.ANDROID_ID)
}

@SuppressLint("HardwareIds")
fun Context.deviceName(): String {
    return Settings.Secure.getString(this.contentResolver, Settings.Secure.NAME)
}

fun Throwable.toException(): Exception {
    return when (this) {
        is Exception -> this
        else -> Exception(this)
    }
}

fun View?.showLoaderInstead(loaderView: View, isLoading: Boolean = false) {
    if (isLoading) {
        this?.visibility = View.GONE
        loaderView.visibility = View.VISIBLE
    } else {
        this?.visibility = View.VISIBLE
        loaderView.visibility = View.GONE
    }
}

fun String.getInitials(): String {
    return this.split(" ")
        .filter { it.isNotEmpty() && !it.equals("-", ignoreCase = true) }
        .map { it[0].uppercaseChar() }
        .take(2)
        .joinToString("")
}

fun String.toDate(format: String): Date? {
    val sdf = SimpleDateFormat(format, Locale.ENGLISH)
    return try {
        sdf.parse(this)
    } catch (e: Exception) {
        null
    }
}

fun String.isValidEmail(): Boolean {
    val regex = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,64}"

    return this.matches(regex.toRegex())
}

fun String.isValidNumber(): Boolean {
    val regex = "^[-+]?[0-9]+([.,][0-9]*)?$"

    return this.matches(regex.toRegex())
}

@SuppressLint("Range")
fun retrieveContactNumber(activity: Activity, contactUri: Uri): List<String>? {
    val cursor = activity.contentResolver.query(contactUri, null, null, null, null)
    cursor?.use {
        it.moveToFirst()
        val number = it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
        return listOf(number)
    }
    return null
}

fun captureView(view: View): Bitmap {
    val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    view.draw(canvas)
    return bitmap
}

fun saveBitmapToGallery(bitmap: Bitmap, context: Context, folderName: String) {
    val values = ContentValues().apply {
        put(MediaStore.Images.Media.MIME_TYPE, "image/png")
        put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis() / 1000)
        put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis())
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/$folderName")
            put(MediaStore.Images.Media.IS_PENDING, true)
        }
    }

    val uri = context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
    uri?.let {
        context.contentResolver.openOutputStream(it)?.use { outputStream ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream.close()
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            values.clear()
            values.put(MediaStore.Images.Media.IS_PENDING, false)
            context.contentResolver.update(it, values, null, null)
        }
    }
}

fun String.formatAccountNumber(): String {
    val cleanAccountNumber = this.revertFormatAccountNumber()
    val bsbOne = cleanAccountNumber.substring(0, 3) // First Three Digits of BSB
    val bsbTwo = cleanAccountNumber.substring(3, 6) // Remaining Three Digits of BSB
    val number = cleanAccountNumber.substring(6) // Account Number
    return "$bsbOne-$bsbTwo $number"
}

fun String.revertFormatAccountNumber(): String =
    this.replace("\\s".toRegex(), "").replace("-", "")

fun String.masked(): String {
    if (this.length < 4) {
        return this
    }

    return this.take(2) +
            "*".repeat(this.length - 4) +
            this.takeLast(2)
}

fun LocalDate.toRelativeDateString(): String {
    val today = LocalDate.now()
    val yesterday = today.minusDays(1)

    return when {
        this.isEqual(today) -> "Today"
        this.isEqual(yesterday) -> "Yesterday"
        else -> this.toFormattedDateString()
    }
}

fun LocalDate.toFormattedDateString(): String {
    val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
    return this.format(formatter)
}

fun Date.formatDateTime(format: String): String {
    val sdf = SimpleDateFormat(format, Locale.ENGLISH)
    return sdf.format(this)
}

fun Context.copyToClipboard(text: CharSequence) {
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("EML", text)
    clipboard.setPrimaryClip(clip)
}

fun Int.opacity(alpha: Float): Int {
    val alphaInt = (alpha * 255).toInt() and 0xff
    return (this and 0x00ffffff) or (alphaInt shl 24)
}

fun Int.shadeGenerator(factor: Float = 0.15f): Int {
    val hsv = FloatArray(3)
    Color.colorToHSV(this, hsv)

    // Increase lightness
    hsv[2] = 1f - (1f - hsv[2]) * factor

    // Decrease saturation
    hsv[1] *= factor

    return Color.HSVToColor(hsv)
}

fun Context.shareBitmap(bitmap: Bitmap, fileName: String) {
    // Step 1: Create a temporary file in the cache directory
    val file = File(this.cacheDir, fileName)
    file.createNewFile()

    // Step 2: Write the bitmap to the file
    val fileOutputStream = FileOutputStream(file)
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
    fileOutputStream.flush()
    fileOutputStream.close()

    // Step 3: Get the content URI for the file with FileProvider
    val uri: Uri = FileProvider.getUriForFile(
        this,
        this.applicationContext.packageName + ".provider",
        file
    )

    // Step 4: Create an Intent to share the bitmap
    val shareIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_STREAM, uri)
        type = "image/png"
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }

    // Start the share intent
    this.startActivity(Intent.createChooser(shareIntent, "Share Image"))
}

fun Context.shareText(label: String, content: String) {
    this.startActivity(
        Intent.createChooser(
            Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, "$label: $content")
            },
            "Share using"
        )
    )
}

fun calculateDistance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
    val earthRadius = 6371e3 // meters
    val dLat = Math.toRadians(lat2 - lat1)
    val dLon = Math.toRadians(lon2 - lon1)
    val a = sin(dLat / 2).pow(2) +
            cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) *
            sin(dLon / 2).pow(2)
    val c = 2 * atan2(sqrt(a), sqrt(1 - a))
    return earthRadius * c
}

fun Double.formatDecimal(): String {
    val formatter = DecimalFormat("#.##")
    return formatter.format(this)
}

fun getRequestGuid(): String {
    val now = System.currentTimeMillis()
    return (now * 1000).toString()
}

fun getCurrentDateTime(): String {
    val currentDateTime = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")
    return currentDateTime.format(formatter)
}


fun <T> List<T>.toArrayList(): ArrayList<T> {
    return ArrayList(this)
}

fun isSamsungPhone(): Boolean {
    if (true) return false // TODO: Disabled until tested on Samsung phone 
    val deviceManufacturer = android.os.Build.MANUFACTURER
    return deviceManufacturer.equals("samsung", ignoreCase = true)
}

fun randomColor(): Int = Random.nextInt(0xFF000000.toInt(), 0xFFFFFFFF.toInt())
