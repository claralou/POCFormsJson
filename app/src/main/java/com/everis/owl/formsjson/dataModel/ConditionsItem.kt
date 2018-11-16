package com.everis.owl.formsjson.dataModel

import android.os.Parcel
import android.os.Parcelable
import java.util.*

class ConditionsItem() : Parcelable{

	var idnextstep: String? = null
	var value: Int? = null

	constructor(parcel: Parcel) : this() {
		idnextstep = parcel.readString()
		value = parcel.readInt()
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(idnextstep)
		parcel.writeInt(value!!)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<ConditionsItem> {
		override fun createFromParcel(parcel: Parcel): ConditionsItem {
			return ConditionsItem(parcel)
		}

		override fun newArray(size: Int): Array<ConditionsItem?> {
			return arrayOfNulls(size)
		}
	}
}


