package br.com.samuelives.printidtcp.ui.screens.settings

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.samuelives.printidtcp.R
import br.com.samuelives.printidtcp.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsActivity : ComponentActivity() {

    private val vm: SettingsVM by viewModels()

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

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { TopBar() }
        ) {

            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {

                OutlinedTextField(
                    value = vm.host,
                    onValueChange = { vm.host = it },
                    label = {
                        Text(
                            text = stringResource(id = R.string.host)
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = vm.port.toString(),
                    onValueChange = { vm.port = it.toInt() },
                    label = {
                        Text(
                            text = stringResource(id = R.string.port)
                        )
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                )

                OutlinedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    onClick = {
                        vm.save()
                        finish()
                    }) {
                    Text(text = stringResource(id = R.string.save))
                }

            }

        }

    }

    @Composable
    private fun TopBar() {

    }

}