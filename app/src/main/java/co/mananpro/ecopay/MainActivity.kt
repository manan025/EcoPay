package co.mananpro.ecopay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()

        val goto = findViewById<TextView>(R.id.signuppage)
        goto.setOnClickListener {
            val i = Intent(this, SignUp::class.java)
            startActivity(i)
        }

        val forget = findViewById<TextView>(R.id.forget)

        forget.setOnClickListener {
            val i = Intent(this, CameraQRCode::class.java)
            startActivity(i)
        }

        val btn = findViewById<Button>(R.id.sign_in)
        btn.setOnClickListener() {
            val email = findViewById<EditText>(R.id.email).text.toString()
            val password = findViewById<EditText>(R.id.password_login).text.toString()

            if ("@" in email && "." in email) {
                signIn(email, password)
            } else {
                Toast.makeText(this, "Please enter email in correct format.", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }


    private fun signIn(email: String, password: String) {
        // [START sign_in_with_email]
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Login Operation failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

}