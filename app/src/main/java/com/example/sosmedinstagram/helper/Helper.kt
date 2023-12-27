package com.example.sosmedinstagram.helper

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.sosmedinstagram.model.PostModel
import com.example.sosmedinstagram.model.UserModel
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun saveUser(user: UserModel, context: Context) {
    val sharedPreferences = context.getSharedPreferences("USER", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putString("userUid", user.uid)
    editor.putString("userEmail", user.email)
    editor.putString("userRole", user.role)
    editor.putString("userNama", user.nama)
    editor.putString("userNoHp", user.nohp)
    editor.putString("userFoto", user.foto)
    editor.putString("userStatus", user.status)
    editor.putString("userToken", user.token)
    editor.putString("userId", user.id)
    editor.putString("userPassword", user.password)
    editor.putBoolean("isLogin", true)
    editor.apply()

}
fun getUser(context: Context): UserModel {
    val sharedPreferences = context.getSharedPreferences("USER", Context.MODE_PRIVATE)
    val user = UserModel()
    user.uid = sharedPreferences.getString("userUid", "")
    user.email = sharedPreferences.getString("userEmail", "")
    user.role = sharedPreferences.getString("userRole", "")
    user.nama = sharedPreferences.getString("userNama", "")
    user.password = sharedPreferences.getString("userPassword", "")
    user.nohp = sharedPreferences.getString("userNoHp", "")
    user.foto = sharedPreferences.getString("userFoto", "")
    user.status = sharedPreferences.getString("userStatus", "")
    user.token = sharedPreferences.getString("userToken", "")
    user.id = sharedPreferences.getString("userId", "")
    user.isLogin = sharedPreferences.getBoolean("isLogin", false)
    return user
}

fun clearUser(context: Context) {
    val sharedPreferences = context.getSharedPreferences("USER", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.clear()
    editor.apply()
}

fun savePost(context: Context,post: PostModel){
    val sharedPreferences = context.getSharedPreferences("POST", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putString("nama", post.nama)
    editor.putString("image", post.image)
    editor.putString("caption", post.caption)
    editor.putString("date", post.date)
    editor.putString("time", post.time)
    editor.putString("like", post.like)
    editor.putString("comment", post.comment)
    editor.putString("share", post.share)
    editor.putString("id", post.id)
    editor.apply()
}
fun getPost(context: Context): PostModel {
    val sharedPreferences = context.getSharedPreferences("POST", Context.MODE_PRIVATE)
    val post = PostModel()
    post.nama = sharedPreferences.getString("nama", "")
    post.image = sharedPreferences.getString("image", "")
    post.caption = sharedPreferences.getString("caption", "")
    post.date = sharedPreferences.getString("date", "")
    post.time = sharedPreferences.getString("time", "")
    post.like = sharedPreferences.getString("like", "")
    post.comment = sharedPreferences.getString("comment", "")
    post.share = sharedPreferences.getString("share", "")
    post.id = sharedPreferences.getString("id", "")
    return post
}

fun showSnackbar(context: Context, message: String) {
    val activity = context as AppCompatActivity
    val view = activity.findViewById<View>(android.R.id.content)
    Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
}
fun showConfirmationDialog(context: Context) {
    val builder = AlertDialog.Builder(context)
    builder.setTitle("Konfirmasi Keluar")
    builder.setMessage("Apakah Anda ingin keluar dari aplikasi?")
    builder.setPositiveButton("Ya") { _: DialogInterface, _: Int ->
        // Menutup aktivitas utama (activity) saat pengguna memilih "Ya"
        (context as AppCompatActivity).finish()
    }
    builder.setNegativeButton("Tidak") { dialog: DialogInterface, _: Int ->
        dialog.dismiss()
    }
    builder.setOnCancelListener {
        // Aksi yang diambil jika pengguna menekan tombol kembali perangkat
        // Misalnya, jika Anda ingin tetap di dalam aplikasi
    }
    builder.show()
}
fun getCurrentDate(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return dateFormat.format(Date())
}

fun getCurrentTime(): String {
    val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    return timeFormat.format(Date())
}