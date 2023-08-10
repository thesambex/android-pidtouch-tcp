package br.com.samuelives.printidtcp.ui.screens.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.samuelives.printidtcp.R
import br.com.samuelives.printidtcp.ui.screens.settings.HomeScreen
import br.com.samuelives.printidtcp.ui.screens.settings.SettingsActivity
import br.com.samuelives.printidtcp.ui.theme.AppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                Content()
            }
        }

    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
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
        val navController = rememberNavController()
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
            Scaffold(
                topBar = { TopBar(drawerState = drawerState) }
            ) {
                ScreenContent(navController = navController)
            }
        }
    }

    @Composable
    private fun ScreenContent(navController: NavHostController) {

        NavHost(navController = navController, startDestination = "home") {

            composable("home") {
                HomeScreen()
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

}