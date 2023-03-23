package com.chemasmas.fakestoreapi.presentation.features.users_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.chemasmas.fakestoreapi.R
import com.chemasmas.fakestoreapi.core.designSystem.previewsConfig.PhonePreview

@Composable
fun LogoutDialog(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    logout: () -> Unit
) {
    val context = LocalContext.current
    Dialog(
        onDismissRequest = onDismiss,
    ) {
        Column(
            modifier = modifier
                .background(color = MaterialTheme.colors.background)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = context.getString(R.string.confirm_logou),
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(16.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {

                Button(
                    onClick = { logout.invoke() }, modifier = Modifier
                        .align(Alignment.CenterStart)
                        .width(width = 100.dp)
                ) {
                    Text(
                        context.getString(R.string.yes),
                    )
                }

                Button(
                    onClick = { onDismiss.invoke() }, modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .width(width = 100.dp)
                ) {
                    Text(
                        context.getString(R.string.cancel),
                    )
                }

            }

        }
    }
}

@PhonePreview
@Composable
fun CountryDialogPreview() {
    LogoutDialog(
        onDismiss = {},
        modifier = Modifier.background(color = Color.White),
        logout = {}
    )
}