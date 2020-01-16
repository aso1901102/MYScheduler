package jp.ac.asojuku.myscheduler

import android.app.Application
import io.realm.Realm

//アプリが起動したときに最初に動くクラス（アプリケーションクラス）
//（Application継承クラス）
class MySchedulerApplication:Application(){
    //アプリの初期処理を上書き
    override fun onCreate(){
        super.onCreate()

        //realmの起動処理
        Realm.init(this);
    }

}