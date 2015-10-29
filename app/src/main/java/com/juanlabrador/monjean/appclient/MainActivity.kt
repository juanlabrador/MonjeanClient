package com.juanlabrador.monjean.appclient

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.parse.*

import kotlinx.android.synthetic.activity_main.pants
import kotlinx.android.synthetic.activity_main.emptyList
import kotlinx.android.synthetic.activity_main.progress

class MainActivity : AppCompatActivity() {

    private var adapter: PantsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = PantsAdapter(this)

        pants.adapter = adapter
        pants.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        pants.setHasFixedSize(true)

        if(isNetworkAvailable()) {
            val query = ParseQuery.getQuery(Pants::class.java)
            query.orderByAscending("updatedAt")
            //query.whereEqualTo("user", ParseUser.getCurrentUser())
            query.findInBackground(object : FindCallback<Pants> {
                override fun done(objects: List<Pants>, e: ParseException?) {
                    progress.visibility = View.GONE
                    if (!objects.isEmpty()) {
                        if (e == null) {
                            //for (Player o : objects) {
                            //    o.pinInBackground();
                            //}

                            (adapter as PantsAdapter).addAll(objects)
                            (adapter as PantsAdapter).notifyDataSetChanged()

                        } else {
                            emptyList.visibility = View.VISIBLE
                            Toast.makeText(applicationContext, e.getMessage(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        } else {
            progress.visibility = View.GONE
            emptyList.visibility = View.VISIBLE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_login, menu)
        return true
    }

    override fun onResume() {
        super.onResume()

        /*var pant1: Pants = Pants(getDrawable(R.drawable.modelo1), "Jean Pretina Ancha", 2, listOf("Verde Turquesa", "Azul Claro", "Azul Medio"), 0f, listOf(6, 7, 8, 9, 10, 11, 12, 13, 14), listOf("Talle medio", "bota tubo"))
        var pant2: Pants = Pants(getDrawable(R.drawable.modelo3), "Pantalón en Dril", 4, listOf("Negro", "Azul Oscuro"), 0f, listOf(8, 9, 10, 11, 12, 13, 14, 15, 16), listOf("Talle alto", "bota tubo"))
        var pant3: Pants = Pants(getDrawable(R.drawable.modelo4), "Jean Pretina Ancha", 1, listOf("Azul Oscuro"), 0f, listOf(8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18), listOf("Talle alto", "bota tubo"))
        var pant4: Pants = Pants(getDrawable(R.drawable.modelo5), "Jean Pretina Ancha", 2, listOf("Azul Oscuro"), 0f, listOf(6, 7, 8, 9, 10, 11, 12, 13, 14), listOf("Talle medio con bolsillos", "bota tubo"))
        var pant5: Pants = Pants(getDrawable(R.drawable.modelo6), "Jean Pretina Ancha", 4, listOf("Azul Oscuro"), 0f, listOf(8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18), listOf("Talle alto", "bota recta"))

        val items: List<Pants> = arrayListOf(pant1, pant2, pant3, pant4, pant5)

        adapter?.addItems(items)
        adapter?.notifyDataSetChanged()*/
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}
