package com.juanlabrador.monjean.appclient

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.parse.GetDataCallback
import com.parse.ParseException
import java.util.*

/**
 * Created by juanlabrador on 27/10/15.
 */
class PantsAdapter(context: Context) : RecyclerView.Adapter<PantsAdapter.ViewHolder>() {

    var context: Context = context
    var items: List<Pants> = arrayListOf()

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val pants = items[position]

        holder?.photo?.tag = pants.objectId
        val file = pants.getPhoto()
        if (file != null) run {
            file.getDataInBackground(object : GetDataCallback {

                override fun done(data: ByteArray, e: ParseException?) {
                    if (e == null) {
                        val bmp = BitmapFactory.decodeByteArray(
                                data, 0,
                                data.size)

                        holder?.photo?.setImageBitmap(bmp)
                    }
                }
            })
        }

        holder?.title?.text = pants.getTitle()

        //var builder: String? = null
        //for(acces in pants.accessories!!) {
        //    builder = acces + "\n"
        //}
        //TextUtils.join(",", pants.accessories)
        holder?.subtitle?.text = pants.getSubTitle()
        holder?.existence?.text = pants.getExistence().toString()
        holder?.price?.text = pants.getPrice().toString() + " Bs."
    }

    fun addAll(collection: Collection<Pants>) {
        items = collection as List<Pants>
    }

    override fun getItemCount(): Int {
        return items.size;
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup?, viewType: Int): PantsAdapter.ViewHolder? {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.custom_card_pant, viewGroup, false)

        //view.setOnClickListener {
        //    (context as AppCompatActivity).startActivity(Intent(context, DetailActivity::class.java))
        //}

        return ViewHolder(view, context);
    }

    fun addItems(list: List<Pants>) {
        items = list
    }

    class ViewHolder(view: View, context: Context) : RecyclerView.ViewHolder(view) {

        val photo = view.findViewById(R.id.photo) as ImageView
        val title = view.findViewById(R.id.modelPants) as TextView
        val subtitle = view.findViewById(R.id.accessories) as TextView
        val existence = view.findViewById(R.id.existence) as TextView
        val price = view.findViewById(R.id.price) as TextView


        val item = view.setOnClickListener {
            Log.i("TAG", photo.tag as String)
            context as AppCompatActivity
            val options = ActivityOptions.makeSceneTransitionAnimation(context, photo, context.getString(R.string.tag_image))
            context.startActivity(Intent(context, DetailActivity::class.java)
                    .putExtra("id", photo.tag as String), options.toBundle())
        }
    }


}