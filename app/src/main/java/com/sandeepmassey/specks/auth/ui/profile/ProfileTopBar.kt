package com.sandeepmassey.specks.auth.ui.profile

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import com.sandeepmassey.specks.R
import com.sandeepmassey.specks.core.ui.components.DisplayAlertDialog

/**
 * Created by Sandeep Massey on 18-03-2022
 */
@Composable
fun ProfileTopBar(
    onDeleteAllConfirmed: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.profile_text),
                color = MaterialTheme.colors.onSurface
            )
        },
        backgroundColor = MaterialTheme.colors.background,
        actions = {
            ProfileTopBarActions(
                onDeleteAllConfirmed = onDeleteAllConfirmed
            )
        }
    )
}

@Composable
fun ProfileTopBarActions(
    onDeleteAllConfirmed: () -> Unit,
) {
    var openDialog by remember { mutableStateOf(false) }

    DisplayAlertDialog(
        openDialog = openDialog,
        onYesClicked = { onDeleteAllConfirmed() },
        onDialogClosed = { openDialog = false }
    )

    DeleteAllAction(onDelete = { openDialog = true})
}

@Composable
fun DeleteAllAction(onDelete: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(onClick = { expanded = true }) {
        Icon(
            Icons.Default.Menu,
            contentDescription = stringResource(id = R.string.vertical_menu_text),
            tint = MaterialTheme.colors.onSurface
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onDelete()
                }
            ) {
                Text(
                    text = stringResource(id = R.string.delete_account_text),
                    style = MaterialTheme.typography.subtitle2
                )
            }
        }
    }
}
