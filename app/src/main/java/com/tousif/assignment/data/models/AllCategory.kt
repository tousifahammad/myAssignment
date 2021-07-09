package com.tousif.assignment.data.models

data class AllCategory(
    var __v: Int,
    var _id: String,
    var createdAt: String,
    var deleted: Boolean,
    var in_stock: Int,
    var item: List<Item>,
    var location: String,
    var name: String,
    var status: Int,
    var subcategory: List<Any>,
    var subcategory_exists: Boolean,
    var updatedAt: String
)