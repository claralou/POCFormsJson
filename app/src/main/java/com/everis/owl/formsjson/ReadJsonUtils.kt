package com.everis.owl.formsjson

import android.content.Context
import com.everis.owl.formsjson.dataModel.FormConditions
import com.everis.owl.formsjson.dataModel.FormData
import org.json.JSONArray
import java.io.*
import java.util.ArrayList
import kotlin.text.Charsets.UTF_8

class ReadJsonUtils {

    companion object {
        fun readJsonFile(fileName: String, ctx: Context): String {

            var flujo: InputStreamReader? = null
            var lector: BufferedReader? = null
            val sb = StringBuilder()
            val filenameAux: String

            filenameAux = fileName

            try {
                flujo = InputStreamReader(ctx.openFileInput(filenameAux))
                lector = BufferedReader(flujo)
                var texto: String? = lector.readLine()
                while (texto != null) {
                    sb.append(texto)
                    texto = lector.readLine()
                }
                return sb.toString()
            } catch (ex: FileNotFoundException) {
                //El fichero no existe. Buscamos en assets
                try {
                    val inputStream : InputStream = ctx.resources.assets.open(filenameAux)
                    val size = inputStream.available()
                    val data = ByteArray(size)
                    inputStream.read(data)
                    inputStream.close()
                    return String(data, UTF_8)

                } catch (e: IOException) {
                    e.printStackTrace()
                }

            } catch (ex: Exception) {

            } finally {
                try {
                    flujo?.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }

            return ""


        }

        fun readForms(ctx: Context, filename: String): List<FormData> {
            val jsonArray: JSONArray
            val list = ArrayList<FormData>()
            try {
                val json = readJsonFile(filename, ctx)
                jsonArray = JSONArray(json)

                for (i in 0 until jsonArray.length()) {
                    list.add(FormData(jsonArray.getJSONObject(i)))
                }


            } catch (ex: Exception) {
                ex.printStackTrace()
            }

            return list
        }

        fun readFormsConditions(ctx: Context, filename: String): List<FormConditions> {
            val jsonArray: JSONArray
            val list = ArrayList<FormConditions>()
            try {
                val json = readJsonFile(filename, ctx)
                jsonArray = JSONArray(json)

                for (i in 0 until jsonArray.length()) {
                    list.add(FormConditions(jsonArray.getJSONObject(i)))
                }


            } catch (ex: Exception) {
                ex.printStackTrace()
            }

            return list
        }
    }



}