package com.juanlabrador.monjean.appclient

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.text.TextUtils
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.parse.*
import kotlinx.android.synthetic.activity_detail.toolbar
import kotlinx.android.synthetic.activity_detail.collapsingToolbar
import kotlinx.android.synthetic.activity_detail.data
import kotlinx.android.synthetic.activity_detail.price
import kotlinx.android.synthetic.activity_detail.buy
import kotlinx.android.synthetic.activity_detail.photo
import java.util.*

/**
 * Created by juanlabrador on 29/10/15.
 */
class DetailActivity : AppCompatActivity() {

    var view: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        view = findViewById(android.R.id.content)
        setSupportActionBar(toolbar as Toolbar)
        supportActionBar.setDisplayHomeAsUpEnabled(true)

        var pants: Pants

        var id: String? = intent.getStringExtra("id")

        var query: ParseQuery<Pants> = ParseQuery.getQuery(Pants::class.java)

        query.getInBackground(id, object: GetCallback<Pants> {
            override fun done(obj: Pants, e: ParseException?) {
                if (e == null) {
                    pants = obj

                    collapsingToolbar.title = pants.getTitle()

                    val amount = ArrayList<String>()
                    for(int in 1..pants.getExistence()) {
                        amount.add(int.toString())
                    }

                    buy.addPopupLayout(R.string.buy_amount, amount)
                    price.addTextLayout(R.string.detail_price, pants.getPrice().toString() + " Bs.")
                    price.addTextLayout(R.string.detail_existence, pants.getExistence().toString())

                    var description = data.addExtendTextLayout(R.mipmap.ic_pants, R.string.detail_accessories, pants.getSubTitle())
                    description.setMultiLine()

                    var colors = data.addExtendTextLayout(R.mipmap.ic_colors, R.string.detail_colors, TextUtils.join(", ", pants.getColors()))
                    colors.setMultiLine()

                    data.addExtendTextLayout(R.mipmap.ic_size, R.string.detail_sizes, TextUtils.join(" - ", pants.getSizes()))

                    val file = pants.getPhoto()
                    if (file != null) run {
                        file.getDataInBackground(object : GetDataCallback {
                            override fun done(data: ByteArray, e: ParseException?) {
                                if (e == null) {
                                    val bmp = BitmapFactory.decodeByteArray(
                                            data, 0,
                                            data.size())

                                    photo.setImageBitmap(bmp)
                                } else {
                                    Toast.makeText(applicationContext, e.getMessage(), Toast.LENGTH_SHORT).show()
                                }
                            }
                        })
                    }

                } else {
                    finish()
                }
            }
        });

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item?.itemId == android.R.id.home)
            finishAfterTransition()

        return true;

    }
}