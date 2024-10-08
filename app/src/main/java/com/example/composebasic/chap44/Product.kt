package com.example.composebasic.chap44

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Products")
data class Product(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="productId")
    var id: Int = 0,
    @ColumnInfo(name="productName")
    var productName: String = "",
    var quantity: Int = 0
)