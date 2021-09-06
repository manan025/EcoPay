package co.mananpro.ecopay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        val signUp = findViewById<Button>(R.id.sign_up)
        signUp.setOnClickListener {
            val email = findViewById<EditText>(R.id.email).text.toString()
            val password = findViewById<EditText>(R.id.password).text.toString()
            val conf = findViewById<EditText>(R.id.password_cnf).text.toString()
            if ("@" in email && "." in email) {
                if (password == conf) {
                    createAccount(email, password)
                }
                else {
                    Toast.makeText(this, "Password does not match", Toast.LENGTH_LONG)
                        .show()
                }
            } else {
                Toast.makeText(this, "Please enter email in correct format.", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

}