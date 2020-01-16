package jp.ac.asojuku.myscheduler

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter


//RealmRecyclerViewAdapterを継承したクラス
class ScheduleAdapter(data:OrderedRealmCollection<Schedule>):
    RealmRecyclerViewAdapter<Schedule,ScheduleAdapter.ViewHolder>(data,true) {
    //初期処理
    init {this.setHasStableIds(true)}

    //ViewHolder(セルの中のView部品を定義する内部クラス）
    class ViewHolder(cell: View):RecyclerView.ViewHolder(cell){
        //予定の日付
        val date:TextView = cell.findViewById(android.R.id.text1)

        //予定の件名(タイトル)
        val title:TextView = cell.findViewById(android.R.id.text2)
    }

    //ViewHolderのインスタンスが生成されたときに呼ばれるメソッド
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //View部品のレイアウトXML定義を読み込んでViewHolderのセル用View部品を作成する
        //inflaterを取得
        val inflater = LayoutInflater.from(parent.context)
        //inflaterを使ってセルのView部品をを生成
        val view = inflater.inflate(android.R.layout.simple_list_item_2,parent,false)
        return ViewHolder(view);
    }

    //セルを表示するタイミングで呼ばれるメソッド
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //セルに表示する予定データ（Scheduleクラス)のインスタンスを取得
        //getItemでScheduleクラスが返されるのは<Schedule>をジェネリクスで指定したから
        val schedule:Schedule? = this.getItem(position)
        //取得したScheduleの情報をViewHolderのレイアウトに設定
        holder.date.text = DateFormat.format("yyyy/MM/dd",schedule?.date)
        holder.title.text = schedule?.title

    }

    //id管理をするために指定した位置の項目のIDを返す処理
    override fun getItemId(position: Int): Long {
        return this.getItem(position)?.id ?: 0
    }
}