package com.chemasmas.fakestoreapi.presentation.features.countries.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.chemasmas.fakestoreapi.R
import com.chemasmas.fakestoreapi.core.data.repository.models.SimpleUser
import com.chemasmas.fakestoreapi.core.designSystem.previewsConfig.PhonePreview
import com.chemasmas.fakestoreapi.presentation.features.users_list.simpleUserMock

@Composable
fun UserItem(
    user: SimpleUser,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(user.profileIcon)
                    .crossfade(true)
                    .error(R.drawable.user_placeholder)
                    .build(),
                placeholder = painterResource(R.drawable.user_placeholder),
                contentDescription = "User Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .height(50.dp)
                    .width(50.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = user.username ?: "",
                    fontSize = 24.sp
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = user.email ?: "")
            }
        }
        Divider(
            color = Color.DarkGray, thickness = 0.5.dp,
            modifier = Modifier.padding(horizontal = 6.dp)
        )
    }
}

@PhonePreview
@Composable
fun UserItemPreview() {
    UserItem(
        user = simpleUserMock, modifier = Modifier.background(color = Color.White)
    )
}