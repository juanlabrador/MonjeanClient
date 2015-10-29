package com.juanlabrador.monjean.appclient

import com.parse.ParseClassName
import com.parse.ParseFile
import com.parse.ParseObject
import com.parse.ParseQuery
import java.util.*

/**
 * Created by juanlabrador on 27/10/15.
 */
@ParseClassName("Pants")
class Pants : ParseObject {

    /*var photo: ParseFile
    var model: String
    var existence: Int
    var colors: List<String>?
    var price: Float
    var sizes: List<Int>?
    var accessories: List<String>?*/

    constructor()

    public fun getPhoto() : ParseFile {
        return getParseFile("photo")
    }

    public fun setPhoto(photo : ParseFile) {
        put("photo", photo)
    }

    public fun setTitle(title : String) {
        put("title", title)
    }

    public fun getTitle() : String {
        return getString("title")
    }

    public fun setSubTitle(subtitle : String) {
        put("title", subtitle)
    }

    public fun getSubTitle() : String {
        return getString("subtitle")
    }

    public fun getExistence() : Int {
        return getInt("existence")
    }

    public fun setExistence(existence : Int) {
        put("existence", existence)
    }

    public fun getPrice() : Int {
        return getInt("price")
    }

    public fun setPrice(existence : Int) {
        put("price", existence)
    }

    public fun setColors(colors : List<String>) {
        put("colors", Arrays.asList(colors))
    }

    public fun getColors() : List<String> {
        return getList("colors")
    }

    public fun setSizes(sizes : List<Int>) {
        put("sizes", Arrays.asList(sizes))
    }

    public fun getSizes() : List<Int> {
        return getList("sizes")
    }

    fun getQuery(): ParseQuery<Pants> {
        return ParseQuery.getQuery<Pants>(Pants::class.java)
    }
}