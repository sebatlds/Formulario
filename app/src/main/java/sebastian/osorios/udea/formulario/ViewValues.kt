package sebastian.osorios.udea.formulario

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_view_values.*

class ViewValues : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_values)

        val objetoIntent : Intent = intent
        val values : Array<String> =objetoIntent.getStringArrayExtra("values")
        val hobbies = objetoIntent.getStringArrayExtra("hobbies")
        viewValues(values,hobbies,this)

    }

    private fun viewValues(values: Array<String>, hobbies : Array<String>, context: Context) {
        val linearLayout : TableLayout = findViewById(R.id.tableLayout)
        name.text = name.text.toString() +" "+values[0]
        email.text = email.text.toString() +" "+values[1]
        pass.text = pass.text.toString()+" "+values[3]
        genero.text = genero.text.toString()+" "+values[4]
        telefono.text = telefono.text.toString()+" "+values[2]
        fecha.text = fecha.text.toString()+" "+values[5]
        lugar.text = lugar.text.toString()+" "+values[6]

        for(item in 0..3){
            if(!hobbies[item].equals("")){
                val textView = TextView(context)
                val tableRow = TableRow(context)
                tableRow.setPadding(5,5,5,5)
                textView.text=hobbies[item]
                textView.textSize=20f
                tableRow.addView(textView)
                linearLayout.addView(tableRow)
            }
        }
    }


}
