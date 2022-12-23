package com.alorma.compose.settings.example.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.SortByAlpha
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alorma.compose.settings.example.demo.AppScaffold
import com.alorma.compose.settings.ui.SettingsMenuLink
import kotlinx.coroutines.launch

@Composable
fun MenuLinksScreen(
  navController: NavController = rememberNavController(),
) {
  val coroutineScope = rememberCoroutineScope()
  val snackbarHostState = remember { SnackbarHostState() }

  AppScaffold(
    navController = navController,
    title = { Text(text = "Menu links") },
    snackbarHostState = snackbarHostState,
  ) {
    SettingsMenuLink(
      title = { Text(text = "Menu 1") },
      subtitle = { Text(text = "Subtitle of menu 1") },
      icon = {
        Icon(
          imageVector = Icons.Default.SortByAlpha,
          contentDescription = "Menu 1"
        )
      }
    ) {
      coroutineScope.launch {
        snackbarHostState.showSnackbar(message = "Click on menu 1")
      }
    }
    Divider()
    SettingsMenuLink(
      title = { Text(text = "Menu 2") },
      subtitle = { Text(text = "Without icon") },
      action = {
        IconButton(
          onClick = {
            coroutineScope.launch {
              snackbarHostState.showSnackbar(message = "Action click")
            }
          }
        ) {
          Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = null,
          )
        }
      },
    ) {
      coroutineScope.launch {
        snackbarHostState.showSnackbar(message = "Click on menu 2")
      }
    }
    Divider()
    SettingsMenuLink(
      title = { Text(text = "Menu 3") }, icon = {
        Icon(
          imageVector = Icons.Default.SortByAlpha,
          contentDescription = "Menu 1"
        )
      }
    ) {
      coroutineScope.launch {
        snackbarHostState.showSnackbar(message = "Click on menu 3")
      }
    }
    Divider()
    var rememberCheckBoxState by remember { mutableStateOf(true) }
    SettingsMenuLink(
      title = { Text(text = "Menu 4") },
      action = {
        Checkbox(checked = rememberCheckBoxState, onCheckedChange = { newState ->
          rememberCheckBoxState = newState
          coroutineScope.launch {
            snackbarHostState.showSnackbar(
              message = "Checkbox update to: $newState"
            )
          }
        })
      },
    ) {
      coroutineScope.launch {
        snackbarHostState.showSnackbar(message = "Click on menu 4")
      }
    }
  }
}
