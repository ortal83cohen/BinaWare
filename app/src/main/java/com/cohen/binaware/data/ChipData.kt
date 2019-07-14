package com.cohen.binaware.data

data class ChipData(val name:String? , val subListName:String ,val subList: ArrayList<ChipData>?,val tierNumber: TierNumber){

    enum class TierNumber {
        FIRST, SECOND, THIRD,FOURTH
    }

}