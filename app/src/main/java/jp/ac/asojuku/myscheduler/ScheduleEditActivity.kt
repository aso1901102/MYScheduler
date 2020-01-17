package jp.ac.asojuku.myscheduler

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_schedule_edit.*
import java.lang.IllegalArgumentException
import java.text.SimpleDateFormat
import java.util.*

class ScheduleEditActivity : AppCompatActivity() {
    //Reaml用のインスタンス変数（後で初期化:lateinit)
    private lateinit var realm: Realm;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_edit)

        //Realmに接続してインスタンス変数に代入(初期化)
        this.realm = Realm.getDefaultInstance();

        //保存ボタンが押されたときに入力項目をrealmに保存
        save.setOnClickListener {view ->
            //Realmのトランザクション開始
            realm.executeTransaction{db ->
                //現在のIDを取得
                val maxId = db.where<Schedule>().max("id")
                //登録用の新規IDを発行
                val nextId = (maxId?.toLong()?:0L) + 1

                //入力項目を新規登録
                //新しいIDを持ったRealmのScheduleレコード
                val schedule =db.createObject<Schedule>(nextId)
                //日付の値を画面から取得
                val date = dateEdit.text.toString().toDate("yyyy/MM/dd")
                if(date != null){
                    //変換した値を設定
                    schedule.date == date
                }

                //タイトル・詳細の文字列も設定
                schedule.title = title_edit.text.toString();
                schedule.detail = detail_edit.text.toString();
                //スナックバーの表示
                Snackbar.make(view,"追加しました",Snackbar.LENGTH_SHORT)
                    .setAction("戻る"){finish()}
                    .setActionTextColor(Color.YELLOW)
                    .show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.realm.close()
    }

    //文字列を日付オブジェクトに変換する拡張関数
    private fun String.toDate(pattern:String = "yyyy/MM/dd HH:mm"): Date? {
        //引数を指定してリターン
        return try {
            SimpleDateFormat(pattern).parse(this)
        }catch (e:IllegalArgumentException){
            return null
        }
    }
}
