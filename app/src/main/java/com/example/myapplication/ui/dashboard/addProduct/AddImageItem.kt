package com.example.myapplication.ui.dashboard.addProduct

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class AddImageItem (val image : Uri) :Parcelable
{
}