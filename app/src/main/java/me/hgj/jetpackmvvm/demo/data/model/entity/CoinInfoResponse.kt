package me.hgj.jetpackmvvm.demo.data.model.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * 分享人信息
 */
@Parcelize
data class CoinInfoResponse(
    var coinCount: Int,
    var rank: String,
    var userId: Int,
    var username: String
) : Parcelable
