package org.aviran.cookiebarsample

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_layout.*
import org.aviran.cookiebar2.CookieBar
import org.aviran.cookiebar2.CookieBarDismissListener.DismissType

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MainFragment : Fragment() {

    var topCookieCounter = 0

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnTop.setOnClickListener {
            CookieBar.build(activity)
                    .setTitle(R.string.top_cookie_title)
                    .setTitleColor(R.color.yellow)
                    .setMessage(getString(R.string.top_cookie_message, ++topCookieCounter))
                    .setIcon(R.drawable.ic_android_white_48dp)
                    .setDuration(5000)
                    .setCookieListener { dismissType ->
                        var desc = ""
                        when (dismissType) {
                            DismissType.DURATION_COMPLETE -> desc = "Cookie display duration completed"
                            DismissType.USER_DISMISS -> desc = "Cookie dismissed by user"
                            DismissType.USER_ACTION_CLICK -> desc = "Cookie dismissed by action click"
                            DismissType.PROGRAMMATIC_DISMISS -> desc = "Cookie dismissed programmatically"
                            DismissType.REPLACE_DISMISS -> desc = "Replaced by new cookie"
                        }
                        infoTextView.text = desc
                    }
                    .show()
        }

        btnBottom.setOnClickListener {
            CookieBar.build(activity)
                    .setDuration(5000)
                    .setTitle(R.string.bottom_cookie_title)
                    .setIcon(R.mipmap.ic_launcher)
                    .setMessage(R.string.bottom_cookie_message)
                    .setBackgroundColor(R.color.colorPrimary)
                    .setActionColor(R.color.yellow)
                    .setTitleColor(R.color.yellow)
                    .setCookiePosition(CookieBar.BOTTOM)
                    .setAction(R.string.cookie_action) {
                        infoTextView.text = getString(R.string.action_engaged)
                    }
                    .show()
        }

        btnCustomAnim.setOnClickListener {
            CookieBar.build(activity)
                    .setTitle(R.string.custom_anim_cookie_title)
                    .setMessage(R.string.custom_anim_cookie_message)
                    .setIcon(R.drawable.ic_android_white_48dp)
                    .setMessageColor(R.color.liteblue)
                    .setBackgroundColor(R.color.orange)
                    .setDuration(5000)
                    .setAnimationIn(android.R.anim.slide_in_left, android.R.anim.slide_in_left)
                    .setAnimationOut(android.R.anim.slide_out_right, android.R.anim.slide_out_right)
                    .show()
        }


        btnBottomAnimated.setOnClickListener {
            CookieBar.build(activity)
                    .setTitle(R.string.fancy_cookie_title)
                    .setMessage(R.string.fancy_cookie_message)
                    .setIcon(R.drawable.ic_settings_white_48dp)
                    .setIconAnimation(R.animator.iconspin)
                    .setTitleColor(R.color.fancyTitle)
                    .setActionColor(R.color.fancyAction)
                    .setMessageColor(R.color.fancyMessage)
                    .setBackgroundColor(R.color.fancyBackground)
                    .setDuration(5000)
                    .setCookiePosition(CookieBar.BOTTOM)
                    .setAction("OPEN SETTINGS") {
                        infoTextView.text = getString(R.string.action_engaged)
                    }
                    .show()
        }

        btnCustomView.setOnClickListener {
            CookieBar.build(activity)
                    .setCustomView(R.layout.custom_cookie)
                    .setCustomViewInitializer { view ->
                        val btnNew = view.findViewById<Button>(R.id.custom_cookie_btn_new)
                        val btnOpen = view.findViewById<Button>(R.id.custom_cookie_btn_open)
                        val btnSave = view.findViewById<Button>(R.id.custom_cookie_btn_save)
                        val btnListener = View.OnClickListener { view ->
                            val button = view as Button
                            button.setText(R.string.clicked)
                        }
                        btnNew.setOnClickListener(btnListener)
                        btnOpen.setOnClickListener(btnListener)
                        btnSave.setOnClickListener(btnListener)
                    }
                    .setAction("Close") { CookieBar.dismiss(activity) }
                    .setTitle(R.string.custom_view_cookie_title)
                    .setMessage(R.string.custom_view_cookie_message)
                    .setEnableAutoDismiss(false)
                    .setSwipeToDismiss(false)
                    .setCookiePosition(Gravity.BOTTOM)
                    .show()
        }

        rootView.setOnClickListener {
            CookieBar.dismiss(activity)
        }
    }
}