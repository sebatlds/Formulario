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

    var name : String = ""
    var email : String= ""
    var pass : String = ""
    var telephone : String = ""
    var sex : String = ""

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
        resultName.text = values[0]
        resultEmail.text = values[1]
        resultNumber.text = values[2]
        resultPassword.text = values[3]
        resultSex.text = values[4]
        resultDate.text = values[5]
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
