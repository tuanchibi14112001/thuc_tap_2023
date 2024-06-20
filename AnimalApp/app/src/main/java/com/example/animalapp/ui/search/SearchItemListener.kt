package com.example.animalapp.ui.search

import com.example.animalapp.model.SearchDetailItem

interface SearchItemListener {
    fun itemOnclick(searchDetailItem: SearchDetailItem)
}