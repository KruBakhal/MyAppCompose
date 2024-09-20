package com.example.myappcompose.resolve_parking.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PaintFlagsDrawFilter
import android.graphics.Rect
import android.graphics.Typeface
import android.text.TextPaint
import com.example.myappcompose.resolve_parking.utils.enums.FontType
import com.example.myappcompose.resolve_parking.utils.enums.TextAlign
import java.io.ByteArrayOutputStream

class ReceiptGenerator(
    private val width: Int,
    maxExpectedHeight: Int,
    private val typefaceMain: Typeface?
) {
    private var baseWidth = 0
    var currentHeight = 0
    private var padding = 20

    private var emptyLineHeight = 0

    private var fontVerySmall: Font
    private var fontSmall: Font
    private var font: Font
    private var fontNormalBold: Font
    private var fontMed: Font
    private var fontLarge: Font
    private var fontHeader: Font
    private var brush: Paint
    var receipt: Bitmap

    private var graphics: Canvas
    val canvas: Canvas
        get() = graphics

    private var stringFormatCentered: Paint.Align
    private var stringFormatLeft: Paint.Align

    init {
        baseWidth = width
        receipt = Bitmap.createBitmap(baseWidth, maxExpectedHeight, Bitmap.Config.ARGB_8888)
        graphics = Canvas(receipt)

        val fontName = "Arial"// added
        fontVerySmall = Font(fontName, 15f, Typeface.NORMAL)
        fontSmall = Font(fontName, 16f, Typeface.NORMAL)
        font = Font(fontName, 20f, Typeface.NORMAL)
        fontNormalBold = Font(fontName, 26f, Typeface.BOLD)
        fontMed = Font(fontName, 35f, Typeface.NORMAL)
        fontLarge = Font(fontName, 64f, Typeface.NORMAL)
        fontHeader = Font(fontName, 40f, Typeface.NORMAL)// added
        brush = Paint().apply { color = Color.BLACK }
        stringFormatCentered = Paint.Align.CENTER
        stringFormatLeft = Paint.Align.LEFT

        setHighQualityRendering(graphics)
        graphics.drawColor(Color.WHITE)
    }

    private fun setHighQualityRendering(graphics: Canvas) {
        graphics.drawFilter = PaintFlagsDrawFilter(
            0, Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG
        )
    }

    fun addEmptyLine(emptyLineHeight: Int = this.emptyLineHeight) = apply {
        val paint = Paint().apply {
            textSize = font.size
            typeface = font.typeface
        }
        val bounds = Rect()
        paint.getTextBounds("A", 0, 1, bounds) // Using 'A' to calculate height
        currentHeight += bounds.height() + emptyLineHeight
    }

    fun addText(
        text: String,
        fontType: FontType = FontType.NORMAL,
        textAlign: TextAlign = TextAlign.CENTRE,
        typeFaceBold: Typeface? = null
    ) = apply {
        val fontToUse = getFont(fontType)
        val paint = TextPaint().apply {// added
            textSize = fontToUse.size
            typeface = typeFaceBold ?: typefaceMain
            color = Color.BLACK
            hinting = Paint.HINTING_OFF // added
            isFakeBoldText = true// added
        }

        val bounds = Rect()
        paint.getTextBounds(text, 0, text.length, bounds)
        val y = currentHeight + bounds.height()
        val xPosition =
            if (textAlign == TextAlign.CENTRE) (width - bounds.width()) / 2f else padding.toFloat()

        graphics.drawText(text, xPosition, y.toFloat(), paint)
        currentHeight = y
    }

    fun addImage(bitmap: Bitmap) = apply {
        val centerOFCanvas = width / 2
        val leftStart = centerOFCanvas - (bitmap.width / 2)
        graphics.drawBitmap(bitmap, leftStart.toFloat(), currentHeight.toFloat(), brush)
        currentHeight += bitmap.height
    }

    fun generateReceipt(quality: Int = 100): ByteArray {
        val outputStream = ByteArrayOutputStream()
        receipt.compress(Bitmap.CompressFormat.PNG, quality, outputStream)
        return outputStream.toByteArray()
    }

    private fun getFont(fontType: FontType): Font {
        return when (fontType) {
            FontType.VERY_SMALL -> fontVerySmall
            FontType.SMALL -> fontSmall
            FontType.NORMAL -> font
            FontType.NORMAL_BOLD -> fontNormalBold
            FontType.MEDIUM -> fontMed
            FontType.LARGE -> fontLarge
            FontType.HEADER -> fontHeader
        }
    }

    data class Font(val name: String, val size: Float, val style: Int) {
        val typeface: Typeface? = Typeface.create(name, style)
    }
}

private fun Paint.getTextWidths(toString: String, i1: Int, i2: Int, widths: FloatArray, i: Int) {
    getTextWidths(toString, i1, i2, widths, i)
}
