package br.com.samuelives.printidtcp.ui.screens.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.samuelives.printidtcp.R
import br.com.samuelives.printidtcp.ui.screens.settings.SettingsActivity
import br.com.samuelives.printidtcp.ui.theme.AppTheme

class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                Content()
            }
        }

    }

    @Preview
    @Composable
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    private fun Content() {

        Scaffold(modifier = Modifier.fillMaxSize()) {

            Button(onClick = {
                startActivity(Intent(this, SettingsActivity::class.java))
            }) {
                Text(text = stringResource(id = R.string.settings))
            }

        }

    }

}