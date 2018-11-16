package com.everis.owl.formsjson.dataModel

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class FormConditions() : Parcelable {


	var idstep: String? = null
	var kindcondition: String? = null
	var conditions: MutableList<ConditionsItem?>? = ArrayList()

	constructor(parcel: Parcel) : this() {
		idstep = parcel.readString()
		kindcondition = parcel.readString()
		parcel.readList(conditions, ConditionsItem::class.java.classLoader)

	}


	@Throws(JSONException::class)
	constructor(json: JSONObject) : this() {
		this.idstep = json.getString("idstep").trim { it <= ' ' }
		this.kindcondition = json.getString("kindcondition").trim { it <= ' ' }
		val auxlist : JSONArray = json.getJSONArray("conditions")
		for (i in 0..(auxlist.length() - 1)) {
			val item = auxlist.getJSONObject(i)
			var aux : ConditionsItem = ConditionsItem()
			aux.idnextstep = item.getString("idnextstep").trim { it <= ' ' }
			aux.value = item.getInt("value")
			conditions!!.add(aux)

			// Your code here
		}


	}
	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(idstep)
		parcel.writeString(kindcondition)
		parcel.writeList(conditions)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<FormConditions> {
		override fun createFromParcel(parcel: Parcel): FormConditions {
			return FormConditions(parcel)
		}

		override fun newArray(size: Int): Array<FormConditions?> {
			return arrayOfNulls(size)
		}
	}

}
