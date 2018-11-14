package com.everis.owl.formsjson

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONException
import org.json.JSONObject

class FormData() : Parcelable {
	var idstep: String? = null
	var question: String? = null
	var text: String? = null
	var kindscreen : String? = null

	constructor(parcel: Parcel) : this() {
		idstep = parcel.readString()
		question = parcel.readString()
		text = parcel.readString()
		kindscreen = parcel.readString()
	}


	@Throws(JSONException::class)
	constructor(json: JSONObject) : this() {
		this.idstep = json.getString("idstep").trim { it <= ' ' }
		this.question = json.getString("question").trim { it <= ' ' }
		this.text = json.getString("text").trim { it <= ' ' }
		this.kindscreen = json.getString("kindscreen").trim { it <= ' ' }
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(idstep)
		parcel.writeString(question)
		parcel.writeString(text)
		parcel.writeString(kindscreen)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<FormData> {
		override fun createFromParcel(parcel: Parcel): FormData {
			return FormData(parcel)
		}

		override fun newArray(size: Int): Array<FormData?> {
			return arrayOfNulls(size)
		}
	}
}
