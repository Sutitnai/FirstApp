package com.titus.meineersteapp
import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(private val context: Context) {

    private val Context.dataStore by preferencesDataStore("shopping_prefs")
    private val SHOPPING_LIST_KEY = stringPreferencesKey("shopping_list")

    suspend fun saveShoppingList(list: List<String>){
        val jason = Gson().toJson(list)
        context.dataStore.edit { prefs ->
            prefs[SHOPPING_LIST_KEY] = jason
        }

    }

    val shoppingListFlow: Flow<List<String>> = context.dataStore.data
        .map { prefs ->
            val json = prefs[SHOPPING_LIST_KEY] ?: "[]"
            val type = object : TypeToken<List<String>>() {}.type
            Gson().fromJson(json, type)
        }
}