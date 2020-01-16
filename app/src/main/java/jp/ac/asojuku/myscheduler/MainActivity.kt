package jp.ac.asojuku.myscheduler

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import io.realm.Realm
import io.realm.kotlin.where

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    //Realmを操作するインスタンス変数
    //後で初期化する(lateinit)
    private lateinit var realm: Realm;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //Realm操作のインスタンス変数の初期化
        //Realmに接続
        realm = Realm.getDefaultInstance()

        //リストの表示を縦表示直列に設定する(layoutManagerの種類を指定する）
        this.list.layoutManager = LinearLayoutManager(this)
        //Realmからスケジュールの全データを取得
        //findAll()はselect*formみたいなメソッド
        val schedules = this.realm.where<Schedule>().findAll()
        //取得したアダプターをリストに設定
        this.list.adapter;

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
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

    //画面のインスタンスが破棄されるときに呼ばれるコールバックメソッド
    override fun onDestroy() {
        super.onDestroy()
        //Realmの接続を解除
        this.realm.close();
    }
}
