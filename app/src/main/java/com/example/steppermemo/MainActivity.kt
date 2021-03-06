package com.example.steppermemo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.steppermemo.ui.home.HomeFragment
import com.example.steppermemo.ui.profile.ProfileFragment
import io.realm.Realm

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), HomeFragment.OnFragmentInteractionListener, ProfileFragment.OnFragmentInteractionListener {
    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        // 追加
//        val fragment = HomeFragment()
//        val fragmentManager = supportFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.fragment, fragment)
//        fragmentTransaction.addToBackStack(null)
//        fragmentTransaction.commit()

        // pager(横スライド画面変更)の設定
        // fragmentの呼び出しはTabAdapter内で行っている
        pager.adapter = TabAdapter(supportFragmentManager)
        // pagerとタブレイアウトの紐づけ
        tab_layout.setupWithViewPager(pager)
        // タブレイアウトのアイコン設定
        tab_layout.getTabAt(0)?.setIcon(R.drawable.ic_home_black_24dp)
        tab_layout.getTabAt(1)?.setIcon(R.drawable.ic_profile_black_24dp)
        // realm(db)のインスタンス取得
        realm = Realm.getDefaultInstance()

        // FABボタン押下時の処理
        fab.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onFragmentInteraction(uri: Uri) {
        // Do Nothing
    }

}
