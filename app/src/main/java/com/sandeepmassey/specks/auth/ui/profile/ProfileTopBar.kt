package com.sandeepmassey.specks.auth.ui.profile

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import com.sandeepmassey.specks.R
import com.sandeepmassey.specks.core.ui.DisplayAlertDialog

/**
 * Created by Sandeep Massey on 18-03-2022
 */
@Composable
fun ProfileTopBar(
    onSave: () -> Unit,
    onDeleteAllConfirmed: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.profile_text),
                color = MaterialTheme.colors.primary
            )
        },
        backgroundColor = MaterialTheme.colors.background,
        actions = {
            ProfileTopBarActions(
                onSave = onSave,
                onDeleteAllConfirmed = onDeleteAllConfirmed
            )
        }
    )
}

@Composable
fun ProfileTopBarActions(
    onSave: () -> Unit,
    onDeleteAllConfirmed: () -> Unit,
) {
    var openDialog by remember { mutableStateOf(false) }

    DisplayAlertDialog(
        openDialog = openDialog,
        onYesClicked = { onDeleteAllConfirmed() },
        onDialogClosed = { openDialog = false }
    )

    SaveAction(onSave = onSave)
    DeleteAllAction(onDelete = { openDialog = true})
}

@Composable
fun SaveAction(onSave: () -> Unit) {
    IconButton(onClick = onSave) {
        Icon(
            Icons.Default.Save,
            contentDescription = "Save Icon",
            tint = MaterialTheme.colors.primary
        )
    }
}

@Composable
fun DeleteAllAction(onDelete: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(onClick = { expanded = true }) {
        Icon(
            Icons.Default.Menu,
            contentDescription = "Vertical Menu",
            tint = MaterialTheme.colors.primary
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
                    text = "Delete Account",
                    style = MaterialTheme.typography.subtitle2
                )
            }
        }
    }
}
