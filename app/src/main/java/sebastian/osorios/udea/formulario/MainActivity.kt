package sebastian.osorios.udea.formulario

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.BoringLayout
import android.widget.*
import androidx.annotation.RequiresApi
import com.tooltip.Tooltip
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

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
    var city : String = ""
    lateinit var spinner : Spinner

    //todo falta una especie de tolltip que mencione la cantidad y no permitir dias futuros en el date...

    @SuppressLint("SetTextI18n", "WrongViewCast")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var dateText : EditText = findViewById(R.id.EditTextdate)
        spinner = findViewById<Spinner>(R.id.spinnerCity)



        dateText.setOnClickListener(){
            var calendar : Calendar = Calendar.getInstance()
            val datePicker = DatePickerDialog(this,DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                Toast.makeText(this,"Year : "+year + "\nMonth :"+(month+1)+"\nDate :"+ dayOfMonth,Toast.LENGTH_SHORT ).show()
                dateText.setText(year.toString() + "/" + (month+1).toString() + "/" + dayOfMonth.toString())
            }, calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH))
            datePicker.datePicker.maxDate
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


        var cities = arrayOf("Medellín","Itagüi","Bello","Donmatias","Envigado")

        var arrayAdapter : ArrayAdapter<String> = ArrayAdapter<String>(this,R.layout.spinner_item_cities,cities)
        spinner.setAdapter(arrayAdapter)


        save.setOnClickListener {
            var checkInputs = true
            var checkEmail = true
            var checkPasswords = true
            var checkPassSize = true
            checkInputs = checkInputs()
            checkEmail = validateEmail(findViewById<EditText>(R.id.email).text.toString())
            checkPasswords = validatePassword()
            checkPassSize = validateSizePassword()

            if(checkEmail && checkInputs && checkPasswords && checkPassSize ){
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
            }else if(!checkInputs){
                val alert = AlertDialog.Builder(this)
                alert.setTitle("Error")
                alert.setMessage("Faltan campos por completar")
                alert.setPositiveButton(
                    "Confirmar",null)
                alert.show()
            }else if(!checkPassSize && !checkPasswords){
                val alert = AlertDialog.Builder(this)
                alert.setTitle("Error")
                alert.setMessage("Las contraseñas no coinciden. \n Y tampoco cumplen con la cantidad de caracteres!!")
                alert.setPositiveButton(
                    "Confirmar",null)
                alert.show()
            } else if(!checkPasswords){
                val alert = AlertDialog.Builder(this)
                alert.setTitle("Error")
                alert.setMessage("Las contraseñas deben de coincidir!!!")
                alert.setPositiveButton(
                    "Confirmar",null)
                alert.show()
            } else if(!checkEmail){
                val alert = AlertDialog.Builder(this)
                alert.setTitle("Error")
                alert.setMessage("Verifique el correo electrónico")
                alert.setPositiveButton(
                    "Confirmar",null)
                alert.show()
            }else if(checkPassSize){
                val alert = AlertDialog.Builder(this)
                alert.setTitle("Error")
                alert.setMessage("La contraseña no cumple con la cantidad de caracteres!!!")
                alert.setPositiveButton(
                    "Confirmar",null)
                alert.show()
            }


        }

    }

    private fun validateSizePassword(): Boolean {
        if (findViewById<EditText>(R.id.password).text.toString().length >= 8){
            return true
        }else{
            return false
        }

    }

    private fun validatePassword(): Boolean {
        if(findViewById<EditText>(R.id.password).text.toString()
                .equals(findViewById<EditText>(R.id.pass2).text.toString())){
            return true
        }else{
            return false
        }

    }

    private fun validateEmail(email: String): Boolean {
        var chekArroba : Boolean = false
        var chekPunto : Boolean = false
        var position : Int = 0
        var dominioEmail : String
        for(item in 0 until email.length){
            dominioEmail = email[item].toString()
            if(email[item].toString().equals("@")){
                position = item.toInt()
                chekArroba = true
            }
        }
        dominioEmail = email.substring(position).toString()
        if(chekArroba){
            for(i in 0 until dominioEmail.length){
                if(dominioEmail[i].toString().equals(".")){
                    position = i.toInt()
                    chekPunto = true
                }
            }
        }
        dominioEmail = dominioEmail.substring(position)
        if(chekPunto && chekArroba){
            return true
        }else{
            return false
        }

    }

    private fun setDataForm() {
        name = findViewById<EditText>(R.id.name).text.toString()
        email = findViewById<EditText>(R.id.email).text.toString()
        pass = findViewById<EditText>(R.id.password).text.toString()
        telephone = findViewById<EditText>(R.id.telephone).text.toString()
        date = findViewById<EditText>(R.id.EditTextdate).text.toString()
        city = spinner.getSelectedItem().toString()
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
        var values = arrayOf(name,email,telephone,pass,sex,date,city)
        val intento = Intent(this, ViewValues ::class.java)
        intento.putExtra("values",values)
        intento.putExtra("hobbies",hobbies)
        startActivity(intento)
    }


    private fun checkInputs(): Boolean{
        var check : Boolean = true
        if(findViewById<EditText>(R.id.name).text.toString().equals("")){
            check = false
        }else if(findViewById<EditText>(R.id.email).text.toString().equals("")){
            check = false
        }else if(findViewById<EditText>(R.id.telephone).text.toString().equals("")){
            check = false
        } else if(findViewById<EditText>(R.id.password).text.toString().equals("")){
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
        if(spinner.getSelectedItem().toString().equals("")){
            check = false
        }
        return check
    }

}




