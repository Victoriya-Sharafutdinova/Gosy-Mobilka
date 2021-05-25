
package com.example.gosy_mobilka

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.gosy_mobilka.model.User
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import com.vk.api.sdk.auth.VKScope
import com.vk.api.sdk.requests.VKRequest
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    var tvInfo: TextView? = null
    var sp: SharedPreferences? = null
    private lateinit var buttonUser: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        VK.login(this, arrayListOf(VKScope.WALL, VKScope.PHOTOS, VKScope.GROUPS))
        tvInfo = findViewById(R.id.tvInfo);
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        buttonUser = findViewById(R.id.buttonUser)

        buttonUser.setOnClickListener {
            VK.execute(VKUsersRequest(), object : VKApiCallback<List<User>> {
                override fun success(result: List<User>) {
                    Log.i("vk-api", result.joinToString { "${it.firstName} ${it.lastName}" })
                }

                override fun fail(error: Exception) {
                    Log.e("vk-api", error.message!!)
                }
            })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val callback = object: VKAuthCallback {
            override fun onLogin(token: VKAccessToken) {
                // User passed authorization

            }

            override fun onLoginFailed(errorCode: Int) {
                // User didn't pass authorization
            }
        }
        if (data == null || !VK.onActivityResult(requestCode, resultCode, data, callback)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    fun toPreferences(view: View?) {
        val toPreferences = Intent(this, PreferencesActivity::class.java)
        startActivity(toPreferences)
    }

    fun toExpandable(view: View?) {
        val toExpandable = Intent(this, ExpandableActivity::class.java)
        startActivity(toExpandable)
    }

    fun toHandler(view: View?) {
        val toHandler = Intent(this, HandlerActivity::class.java)
        startActivity(toHandler)
    }

    fun toFragment(view: View?) {
        val toFragment = Intent(this, FragmentActivity::class.java)
        startActivity(toFragment)
    }

    fun toAsyncTask(view: View?) {
        val toAsyncTask = Intent(this, AsyncTaskActivity::class.java)
        startActivity(toAsyncTask)
    }

    override fun onResume() {
        val notif = sp!!.getBoolean("notif", false)
        val address = sp!!.getString("address", "")
        val text = ("Notifications are "
                + if (notif) "enabled, address = $address" else "disabled")
        tvInfo!!.text = text
        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val mi: MenuItem = menu.add(0, 1, 0, "Preferences")
        mi.setIntent(Intent(this, PrefActivity::class.java))
        return super.onCreateOptionsMenu(menu)
    }

    fun toPreferenceActivity(view: View?) {
        val toPreferenceActivity = Intent(this, PrefActivity::class.java)
        startActivity(toPreferenceActivity)
    }

    fun toCursorLoader(view: View?) {
        val toCursorLoader = Intent(this, CursorLoaderActivity::class.java)
        startActivity(toCursorLoader)
    }

    fun toParcelable(view: View?) {
        val toParcelable = Intent(this, ParcelableActivity::class.java)
        startActivity(toParcelable)
    }

    fun toSpinner(view: View?) {
        val toSpinner = Intent(this, SpinnerActivity::class.java)
        startActivity(toSpinner)
    }

    fun toTab(view: View?) {
        val toTab = Intent(this, TabActivity::class.java)
        startActivity(toTab)
    }
}

class VKUsersRequest(uids: IntArray = intArrayOf()) : VKRequest<List<User>>("users.get") {
    init {
        if (uids.isNotEmpty()) {
            addParam("user_ids", uids.joinToString(","))
        }
        addParam("fields", "photo_200")
    }

    override fun parse(r: JSONObject): List<User> {
        val users = r.getJSONArray("response")
        val result = ArrayList<User>()
        for (i in 0 until users.length()) {
            result.add(User.parse(users.getJSONObject(i)))
        }
        return result
    }
}