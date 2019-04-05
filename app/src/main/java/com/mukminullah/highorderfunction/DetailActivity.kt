package com.mukminullah.highorderfunction

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mukminullah.highorderfunction.MainActivity.ModelBiodata
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val item : ModelBiodata = intent.extras.getSerializable("data") as ModelBiodata

        tvDetailText.text = "Person Name: "+item.nama+"\n" +
                            "Person Email: "+item.email
    }
}
