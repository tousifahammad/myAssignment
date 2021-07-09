package com.tousif.webskitter.data.models

data class Data(
    var __v: Int,
    var _id: String,
    var address: String,
    var allCategory: List<AllCategory>,
    var balance: Int,
    var company: String,
    var createdAt: String,
    var deleted: Boolean,
    var loc: Loc,
    var location_open: String,
    var name: String,
    var password: String,
    var preparation_time: String,
    var token: List<String>,
    var updatedAt: String,
    var username: String
)