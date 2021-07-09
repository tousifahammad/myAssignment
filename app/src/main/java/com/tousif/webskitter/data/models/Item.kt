package com.tousif.webskitter.data.models

data class Item(
    var __v: Int,
    var _id: String,
    var addon_groups: List<Any>,
    var category: String,
    var createdAt: String,
    var deleted: Boolean,
    var description: String,
    var image: String,
    var in_stock: Int,
    var location: String,
    var name: String,
    var packaging_price: Int,
    var price: Int,
    var status: Int,
    var tax: Int,
    var timings: Timings,
    var type: String,
    var updatedAt: String,
    var variants: List<Any>
)