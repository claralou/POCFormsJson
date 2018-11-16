package com.everis.owl.formsjson.dataModel

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONException
import org.json.JSONObject

class FormData() : Parcelable {
	var idstep: String? = null
	var question: String? = null
	var text: String? = null
	var kindscreen : String? = null
	var isconditional : Boolean? = null

	constructor(parcel: Parcel) : this() {
		idstep = parcel.readString()
		question = parcel.readString()
		text = parcel.readString()
		kindscreen = parcel.readString()
		isconditional = parcel.readBoolean()
	}


	@Throws(JSONException::class)
	constructor(json: JSONObject) : this() {
		this.idstep = json.getString("idstep").trim { it <= ' ' }
		this.question = json.getString("question").trim { it <= ' ' }
		this.text = json.getString("text").trim { it <= ' ' }
		this.kindscreen = json.getString("kindscreen").trim { it <= ' ' }
		this.isconditional = json.getBoolean("isconditional")
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(idstep)
		parcel.writeString(question)
		parcel.writeString(text)
		parcel.writeString(kindscreen)
		parcel.writeBoolean(isconditional!!)
	}

	override fun describeContents(): Int {
		return 0
	}


	fun Parcel.writeBoolean(value: Boolean) {
		writeByte(if (value) 1 else 0)
	}

	fun Parcel.readBoolean(): Boolean {
		val value = readByte()
		return when (value) {
			0.toByte() -> false
			1.toByte() -> true
			else -> throw IllegalArgumentException("Cannot read byte value $value as Boolean, 0 or 1 expected.")
		}
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
