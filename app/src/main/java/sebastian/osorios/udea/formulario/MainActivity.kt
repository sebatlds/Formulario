package sebastian.osorios.udea.formulario

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import sebastian.osorios.udea.formulario.models.FormValues
import java.util.*

class MainActivity : AppCompatActivity() {
    var HOBBIES = 4
    var name : String = ""
    var email : String= ""
    var pass : String = ""
    var telephone : String = ""
    var sex : String = ""
    var date : String = ""
    var reed : String = ""
    var walk : String = ""
    var dev : String = ""
    var play : String = ""



    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var dateText : EditText = findViewById(R.id.EditTextdate)

        dateText.setOnClickListener(){
            var calendar : Calendar = Calendar.getInstance()
            val datePicker = DatePickerDialog(this,DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                Toast.makeText(this,"Year : "+year + "\nMonth :"+month+"\nDate :"+ dayOfMonth,Toast.LENGTH_SHORT ).show()
                dateText.setText(year.toString() + "/" + (month+1).toString() + "/" + dayOfMonth.toString())
            }, calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH))
            datePicker.show()
        }
        checkMen.setOnClickListener {
            checkMen.isChecked = true
            checkWomen.isChecked = false
        }
        checkWomen.setOnClickListener {
            checkMen.isChecked = false
            checkWomen.isChecked = true
        }
        save.setOnClickListener {
            var check = true
            if(findViewById<EditText>(R.id.name).text.toString().equals("")){
                check = false
            }else if(findViewById<EditText>(R.id.email).text.toString().equals("")){
                check = false
            }else if(findViewById<EditText>(R.id.password).text.toString().equals("")){
                check = false
            }else if(findViewById<EditText>(R.id.pass2).text.toString().equals("")){
                check = false
            }else if(!findViewById<CheckBox>(R.id.hobbie0).isChecked &&
                !findViewById<CheckBox>(R.id.hobbie1).isChecked && !findViewById<CheckBox>(R.id.hobbie2).isChecked &&
                !findViewById<CheckBox>(R.id.hobbie3).isChecked){
                check = false
            }else if(findViewById<EditText>(R.id.EditTextdate).text.toString().equals("")){
                check = false
            }

            if(findViewById<EditText>(R.id.password).text.toString()
                    .equals(findViewById<EditText>(R.id.pass2).text.toString())){
                if(check){
                    setDataForm()
                    findViewById<EditText>(R.id.email).text = null
                    findViewById<EditText>(R.id.password).text = null
                    findViewById<EditText>(R.id.pass2).text = null
                    findViewById<EditText>(R.id.telephone).text = null
                    findViewById<EditText>(R.id.name).text  = null
                    findViewById<EditText>(R.id.EditTextdate).text = null
                    hobbie0.isChecked = false
                    hobbie1.isChecked = false
                    hobbie2.isChecked = false
                    hobbie3.isChecked = false
                    checkMen.isChecked = true
                    checkWomen.isChecked = false
                }else{
                    val alert = AlertDialog.Builder(this)
                    alert.setTitle("Error")
                    alert.setMessage("Faltan campos por llenar")
                    alert.setPositiveButton(
                        "Confirmar",null)
                    alert.show()

                }
            }else{
                val alert = AlertDialog.Builder(this)
                alert.setTitle("Error")
                alert.setMessage("Las contraseñas deben de coincidir!!!")
                alert.setPositiveButton(
                    "Confirmar",null)
                alert.show()
            }


        }

    }

    private fun setDataForm() {
        name = findViewById<EditText>(R.id.name).text.toString()
        email = findViewById<EditText>(R.id.email).text.toString()
        pass = findViewById<EditText>(R.id.password).text.toString()
        telephone = findViewById<EditText>(R.id.telephone).text.toString()
        date = findViewById<EditText>(R.id.EditTextdate).text.toString()
        if(hobbie0.isChecked) {
            play = findViewById<CheckBox>(R.id.hobbie0).hint.toString()
        }else{
            play = ""
        }
        if(hobbie1.isChecked){
            reed = findViewById<CheckBox>(R.id.hobbie1).hint.toString()
        }else{
            reed=""
        }
        if(hobbie2.isChecked){
            walk = findViewById<CheckBox>(R.id.hobbie2).hint.toString()
        }else{
            walk=""
        }
        if(hobbie3.isChecked){
            dev = findViewById<CheckBox>(R.id.hobbie3).hint.toString()
        }else{
            dev=""
        }

        if(checkMen.isChecked){
            sex = checkMen.text.toString()
        }else{
            sex = checkWomen.text.toString()
        }
        var hobbies = arrayOf(play,reed,walk,dev)
        var values = arrayOf(name,email,telephone,pass,sex,date)
        val intento = Intent(this, ViewValues ::class.java)
        intento.putExtra("values",values)
        intento.putExtra("hobbies",hobbies)
        startActivity(intento)
    }


}




