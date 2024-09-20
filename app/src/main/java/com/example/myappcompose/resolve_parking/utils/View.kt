package com.example.test_paper.base

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.SystemClock
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

@SuppressLint("ClickableViewAccessibility")
fun View.setupUI() {

    if (this !is EditText) {
        this.setOnTouchListener { v, event ->
            this.hideKeyboard()
            this.requestFocus()
            false
        }
    }

    if (this is ViewGroup) {
        for (i in 0 until this.childCount) {
            val innerView = this.getChildAt(i)
            innerView.setupUI()
        }
    }
}

fun View.setGone() {
    visibility = View.GONE
}

fun View.setVisible() {
    visibility = View.VISIBLE
}

fun View.setInvisible() {
    visibility = View.INVISIBLE
}

fun View.hideKeyboard() {
    val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}

fun View.showKeyboard() {
    val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
}

/*fun View.setOnSingleClickListener(clickListener: View.OnClickListener) {
    clicks()
        .throttleFirst(400, TimeUnit.MILLISECONDS)
        .subscribe(
            { clickListener.onClick(this) },
            { error -> error.printStackTrace() }
        )

}*/
abstract class OnThrottleClickListener @JvmOverloads constructor(
    private val minClickInterval: Long = 600 //default value for min click time
) : View.OnClickListener {

    /**
     * The time the last click was applied
     */
    private var mLastClickTime: Long = 0

    /**
     * @param v The view that was clicked.
     */
    abstract fun onSingleClick(v: View?)

    override fun onClick(v: View?) {
        val currentClickTime: Long = SystemClock.elapsedRealtime()
        val elapsedTime = currentClickTime - mLastClickTime
        if (elapsedTime <= minClickInterval) {
            Log.d("TAG", "Click ignored due to throttling.")
            return
        }
        mLastClickTime = currentClickTime
        onSingleClick(v)
    }
}

fun View.setThrottleClickListener(action: (v: View?) -> Unit) {
    setOnClickListener(object : OnThrottleClickListener() {
        override fun onSingleClick(v: View?) {
            action(v)
        }
    })
}

@SuppressLint("WrongConstant")
fun View.showMessage(
    message: String,
    title: String = "",
    type: DisplayType = DisplayType.LONG_TOAST,
    positive: String = "OK",
    pCallback: () -> Unit = {},
    negative: String = "Cancel",
    nCallback: () -> Unit = {}, color: Int,
    showCallback: (isShown: Boolean) -> Unit = {}
) {
    when (type) {
        DisplayType.TOAST -> Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
        DisplayType.LONG_TOAST -> Toast.makeText(this.context, message, Toast.LENGTH_LONG).show()
        DisplayType.ALERT -> {
            /* val dialog = Dialog(context, R.style.GrowStyle)
             dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
             val view =
                 LayoutInflater.from(context).inflate(R.layout.layout_message_dialog, null, false)
             val binding: LayoutMessageDialogBinding = DataBindingUtil.bind(view)!!

             binding.ivBack.setOnSingleClickListener { dialog.dismiss() }
             binding.tvTitle.text = title
             binding.tvMessage.text = HtmlCompat.fromHtml(message, HtmlCompat.FROM_HTML_MODE_LEGACY)
             binding.tvNegative.text = negative
             binding.tvPositive.text = positive

             binding.tvNegative.setOnSingleClickListener {
                 dialog.dismiss()
                 nCallback.invoke()
             }
             binding.tvPositive.setOnSingleClickListener {
                 dialog.dismiss()
                 pCallback.invoke()
             }

             dialog.setContentView(view)
             dialog.setOnShowListener {
                 dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                 showCallback.invoke(true)
             }

             dialog.setOnDismissListener {
                 showCallback.invoke(false)
             }
             if (view.context is Activity && !(view.context as Activity).isFinishing)
                 dialog.show()*/
        }

        DisplayType.SNACKBAR -> {
            try {
                val snackBar = Snackbar.make(this.rootView, message, Snackbar.LENGTH_LONG)
                val snackBarView = snackBar.view
                val params = snackBarView.layoutParams as FrameLayout.LayoutParams
                snackBarView.setBackgroundColor(color)
                params.gravity = Gravity.TOP
                snackBarView.layoutParams = params
                snackBar.show()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

/*
fun View.showInfoMessage(
    message: String,
    title: String = context.getString(R.string.appname),
    positive: String = "OK",
    pCallback: () -> Unit = {},
    cancelable: Boolean = false,
    showCallback: (isShown: Boolean) -> Unit = {}
) {
    val dialog = Dialog(context, R.style.GrowStyle)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setCancelable(cancelable)
    val view =
        LayoutInflater.from(context).inflate(R.layout.layout_info_message_dialog, null, false)
    val binding: LayoutInfoMessageDialogBinding = DataBindingUtil.bind(view)!!

    if (title.isNotEmpty()) {
        binding.tvTitle.text = title
        binding.rlHeader.visibility = View.VISIBLE
        binding.viewDivider.visibility = View.VISIBLE
    } else {
        binding.rlHeader.visibility = View.GONE
        binding.viewDivider.visibility = View.GONE

    }

    var msg=message
    if (msg.contains("Unable to resolve host",true)){
        msg="Network connection lost"
    }

    binding.tvMessage.text = HtmlCompat.fromHtml(msg, HtmlCompat.FROM_HTML_MODE_LEGACY)
    binding.tvPositive.text = positive

    binding.tvPositive.setOnSingleClickListener {
        dialog.dismiss()
        pCallback.invoke()
    }

    dialog.setContentView(view)
    dialog.setOnShowListener {
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        showCallback.invoke(true)
    }
    dialog.setOnDismissListener {
        showCallback.invoke(false)
    }
//    if (view.context is Activity && !(view.context as Activity).isFinishing)
    dialog.show()

    if (resources.getBoolean(R.bool.is_mobile)) {
        val window = dialog.window
        window?.setLayout(
            resources.getDimension(R.dimen._348pxTab).toInt(),
            resources.getDimension(R.dimen._690pxTab).toInt()
        )
    } else {
        val window = dialog.window
        window?.setLayout(
            resources.getDimension(R.dimen._520pxTab).toInt(),
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }
}

fun View.askForConfirmation(
    title: String = context.getString(R.string.appname),
    subTitle: String = "",
    message: String,
    note: String = "",
    positive: String = "OK",
    pCallback: () -> Unit = {},
    negative: String = "Cancel",
    nCallback: () -> Unit = {},
    secondaryButton: String = "",
    secondaryCallback: () -> Unit = {},
    showCallback: (isShown: Boolean) -> Unit = {},
    cancelable: Boolean = true
) {
    val dialog = Dialog(context, R.style.GrowStyle)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setCancelable(cancelable)
    val view =
        LayoutInflater.from(context).inflate(R.layout.dialog_confirmation, null, false)
    val binding: DialogConfirmationBinding = DataBindingUtil.bind(view)!!

    binding.tvTitle.text = title
    binding.tvSubTitle.text = subTitle
    if (subTitle.isEmpty()) {
        binding.tvSubTitle.visibility = View.GONE
    } else {
        binding.tvSubTitle.visibility = View.VISIBLE
    }

    if (secondaryButton.isEmpty()) {
        binding.tvSecondaryButton.visibility = View.GONE
    } else {
        binding.tvSecondaryButton.text = secondaryButton
        binding.tvSecondaryButton.visibility = View.VISIBLE
    }

    if (note.isEmpty()) {
        binding.tvFooter.visibility = View.GONE
    } else {
        binding.tvFooter.text = note
        binding.tvFooter.visibility = View.VISIBLE
    }

    binding.tvMessage.text = HtmlCompat.fromHtml(message, HtmlCompat.FROM_HTML_MODE_LEGACY)

    binding.tvPositive.text = positive
    binding.tvNegative.text = negative

    binding.tvPositive.setOnSingleClickListener {
        dialog.dismiss()
        pCallback.invoke()
    }
    binding.tvNegative.setOnSingleClickListener {
        dialog.dismiss()
        nCallback.invoke()
    }
    binding.tvSecondaryButton.setOnSingleClickListener {
        dialog.dismiss()
        secondaryCallback.invoke()
    }

    dialog.setContentView(view)
    dialog.setOnShowListener {
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        showCallback.invoke(true)
    }
    dialog.setOnDismissListener {
        showCallback.invoke(false)
    }

//    if (context is Activity && !(context as Activity).isFinishing)
    dialog.show()
}

fun View.showPopUp(
    popupList: ArrayList<PopUpItem>,
    showAbove: Boolean = false,
    gravity: Int = Gravity.END,
    function: (View, Int,PopUpItem) -> Unit
) {

    val popup = PopupWindow(
        LayoutInflater.from(context).inflate(R.layout.layout_item_list, null),
        WindowManager.LayoutParams.WRAP_CONTENT,
        WindowManager.LayoutParams.WRAP_CONTENT,
        true
    )
    popup.contentView?.let {
        val rvView = it.findViewById<RecyclerView>(R.id.rvItems)
        rvView.layoutManager =
            LinearLayoutManager(it.context)


        val adapterP = PopUpAdapter(popupList) { view, position,item ->
            popup.dismiss()
            function.invoke(view, position,item)
        }

        rvView.adapter = adapterP
    }

    popup.width = context.resources.getDimension(R.dimen._230pxTab).toInt()
    popup.height = ViewGroup.LayoutParams.WRAP_CONTENT

    popup.isOutsideTouchable = true
    popup.isFocusable = false

    if (showAbove) {
        popup.showAsDropDown(this, 0, -this.height * 3, gravity)
    } else {
        popup.showAsDropDown(this, 0, 0, gravity)
    }
    popup.update()
}

fun View.showPopUp(
    popupList: ArrayList<PopUpItem>,
    searchStr: String,
    showAbove: Boolean = false,
    gravity: Int = Gravity.END,
    function: (View, Int,PopUpItem) -> Unit
) {

    val popup = PopupWindow(
        LayoutInflater.from(context).inflate(R.layout.layout_item_list, null),
        WindowManager.LayoutParams.WRAP_CONTENT,
        WindowManager.LayoutParams.WRAP_CONTENT,
        true
    )
    popup.contentView?.let {
        val rvView = it.findViewById<RecyclerView>(R.id.rvItems)
        rvView.layoutManager =
            LinearLayoutManager(it.context)


        val adapterP = PopUpAdapter(popupList) { view, position,item ->
                popup.dismiss()
                function.invoke(view, position,item)
            }

        adapterP.setFilter(searchStr.toString())
        rvView.adapter = adapterP
    }

    popup.width = context.resources.getDimension(R.dimen._230pxTab).toInt()
    popup.height = ViewGroup.LayoutParams.WRAP_CONTENT

    popup.isOutsideTouchable = true
    popup.isFocusable = false

    if (showAbove) {
        popup.showAsDropDown(this, 0, -this.height * 3, gravity)
    } else {
        popup.showAsDropDown(this, 0, 0, gravity)
    }
    popup.update()
}

fun View.showPopUp1(
    popupList: ArrayList<PopUpItem>,
    showAbove: Boolean = false,
    gravity: Int = Gravity.END,
    function: (View, Int,PopUpItem) -> Unit
) {

    val popup = PopupWindow(
        LayoutInflater.from(context).inflate(R.layout.layout_item_list, null),
        WindowManager.LayoutParams.WRAP_CONTENT,
        WindowManager.LayoutParams.WRAP_CONTENT,
        true
    )
    popup.contentView?.let {
        val rvView = it.findViewById<RecyclerView>(R.id.rvItems)
        rvView.layoutManager =
            LinearLayoutManager(it.context)

        rvView.adapter =
            PopUpAdapter(popupList) { view, position,item ->
                popup.dismiss()
                function.invoke(view, position,item)
            }
    }

    popup.width = context.resources.getDimension(R.dimen._220pxTab).toInt()
    popup.height = ViewGroup.LayoutParams.WRAP_CONTENT

    popup.isOutsideTouchable = true
    popup.isFocusable = false

    if (showAbove) {
        popup.showAsDropDown(this, 0, -this.height * 1, gravity)
    } else {
        popup.showAsDropDown(this, 0, 0, gravity)
    }
    popup.update()
}
fun View.showUpdateMessage(
    currVersion: String,
    newVersion: String,
    pCallback: () -> Unit = {},
    nCallback: () -> Unit = {},
    cancelable: Boolean = false,
    showCallback: (isShown: Boolean) -> Unit = {}
) {
    val dialog = Dialog(context, R.style.UpdateDialogStyle)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setCancelable(cancelable)
    val view =
        LayoutInflater.from(context).inflate(R.layout.layout_update_message_dialog, null, false)
    val binding: LayoutUpdateMessageDialogBinding = DataBindingUtil.bind(view)!!

    when {
        cancelable -> {
            binding.tvCancel.visibility = View.VISIBLE
        }
        else -> {
            binding.tvCancel.visibility = View.GONE
        }
    }

    binding.tvCurrentVersion.text = "v.$currVersion"
    binding.tvCurrentVersion.visibility = View.GONE
    binding.tvNewVersion.text = "v.$currVersion -> v.$newVersion"

    binding.tvUpdate.setOnSingleClickListener {
        pCallback.invoke()
    }

    binding.tvCancel.setOnSingleClickListener {
        dialog.dismiss()
        nCallback.invoke()
    }

    dialog.setContentView(view)
    dialog.setOnShowListener {
        dialog.window?.setBackgroundDrawableResource(android.R.color.white)
        showCallback.invoke(true)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }
    dialog.setOnDismissListener {
        showCallback.invoke(false)
    }

    if (view.context is Activity && !(view.context as Activity).isFinishing)
        dialog.show()
}

fun Activity.showInfoMessage(
    message: String,
    title: String = getString(R.string.appname),
    positive: String = "OK",
    pCallback: () -> Unit = {},
    cancelable: Boolean = true
) {
    val dialog = Dialog(this, R.style.GrowStyle)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setCancelable(cancelable)
    val view = LayoutInflater.from(this).inflate(R.layout.layout_info_message_dialog, null, false)
    val binding: LayoutInfoMessageDialogBinding = DataBindingUtil.bind(view)!!

    binding.tvTitle.text = title
    binding.tvMessage.text = HtmlCompat.fromHtml(message, HtmlCompat.FROM_HTML_MODE_LEGACY)
    binding.tvPositive.text = positive

    binding.tvPositive.setOnSingleClickListener {
        dialog.dismiss()
        pCallback.invoke()
    }

    dialog.setContentView(view)
    dialog.setOnShowListener { dialog.window?.setBackgroundDrawableResource(android.R.color.transparent) }
    if (!isFinishing)
        dialog.show()
}

fun Activity.showMessage(
    message: String,
    title: String = "",
    positive: String = "OK",
    pCallback: () -> Unit = {},
    negative: String = "Cancel",
    nCallback: () -> Unit = {}
) {
    val dialog = Dialog(this, R.style.GrowStyle)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    val view = LayoutInflater.from(this).inflate(R.layout.layout_message_dialog, null, false)
    val binding: LayoutMessageDialogBinding = DataBindingUtil.bind(view)!!

    binding.ivBack.setOnSingleClickListener { dialog.dismiss() }
    binding.tvTitle.text = title
    binding.tvMessage.text = message
    binding.tvNegative.text = negative
    binding.tvPositive.text = positive

    binding.tvNegative.setOnSingleClickListener {
        dialog.dismiss()
        nCallback.invoke()
    }
    binding.tvPositive.setOnSingleClickListener {
        dialog.dismiss()
        pCallback.invoke()
    }

    dialog.setContentView(view)
    dialog.setOnShowListener { dialog.window?.setBackgroundDrawableResource(android.R.color.transparent) }
    if (!isFinishing)
        dialog.show()
}

fun View.startWithTransition(intent: Intent, requestCode: Int = 0) {
    if (requestCode == 0) {
        context.startActivity(intent)
    } else {
        (context as Activity).startActivityForResult(intent, requestCode)
    }
    (context as Activity).overridePendingTransition(R.anim.grow_fade_in, R.anim.scale_out)
}
*/





fun Context.redirectToPlayStore() {
    val appPackageName = packageName

    try {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("market://details?id=$appPackageName")
            )
        )
    } catch (ante: ActivityNotFoundException) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
            )
        )
    }
}


enum class DisplayType {
    LONG_TOAST,
    TOAST,
    ALERT,
    SNACKBAR
}
