package br.com.samuelives.printidtcp.ui.screens.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.samuelives.printidtcp.R
import br.com.samuelives.printidtcp.ui.screens.settings.SettingsActivity
import br.com.samuelives.printidtcp.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    private val vm: HomeVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                Content()
            }
        }

    }

    @Composable
    private fun Content() {

        val drawerItems = listOf(
            DrawerItem(
                stringResource(id = R.string.settings),
                Icons.Outlined.Settings,
                "settings"
            )
        )

        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        var selectedItem by remember { mutableStateOf(-1) }

        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    Spacer(modifier = Modifier.height(6.dp))
                    drawerItems.forEachIndexed { index, item ->
                        NavigationDrawerItem(
                            modifier = Modifier.padding(
                                start = 12.dp,
                                end = 12.dp,
                                top = 6.dp
                            ),
                            label = { Text(text = item.name) },
                            icon = { Icon(item.icon, contentDescription = null) },
                            selected = index == selectedItem,
                            onClick = {
                                selectedItem = index

                                if (item.route == "settings") {
                                    startActivity(
                                        Intent(
                                            this@HomeActivity,
                                            SettingsActivity::class.java
                                        )
                                    )
                                }

                            })
                    }
                }
            }
        ) {
            ScreenContent(drawerState = drawerState)
        }
    }

    @Composable
    private fun ScreenContent(drawerState: DrawerState) {

        Scaffold(
            topBar = { TopBar(drawerState = drawerState) }
        ) { contentPadding ->

            if (vm.showDialog)
                InfoDialog(msg = vm.message)

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Button(onClick = { vm.printTestPage() }) {
                    Text(text = stringResource(id = R.string.test_page))
                }

            }
        }

    }

    @Composable
    private fun TopBar(drawerState: DrawerState) {

        val scope = rememberCoroutineScope()

        TopAppBar(
            title = { Text(text = stringResource(id = R.string.app_name)) },
            navigationIcon = {
                IconButton(onClick = {

                    scope.launch {
                        if (drawerState.isClosed)
                            drawerState.open()
                        else
                            drawerState.close()
                    }

                }) {
                    Icon(Icons.Outlined.Menu, contentDescription = "Menu")
                }
            }
        )

    }

    @Composable
    private fun InfoDialog(msg: String) {
        AlertDialog(
            onDismissRequest = { vm.showDialog = false },
            title = { Text(text = stringResource(id = R.string.app_name)) },
            text = { Text(text = msg) },
            confirmButton = {},
            dismissButton = {
                TextButton(onClick = { vm.showDialog = false }) {
                    Text(text = "Ok")
                }
            }
        )
    }

}