package jp.ac.asojuku.myscheduler

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*


//Realmのレコードインスタンスオブジェクト用のクラス
open class Schedule:RealmObject(){
    //プロパティ（カラム）
    @PrimaryKey
    var id: Long =0;                //主キー（idとして使う）
    var date: Date = Date();        //日付（java.utilパッケージ）
    var title:String = ""           //予定スケジュールの件名
    var detail:String = ""          //予定スケジュールの詳細
}