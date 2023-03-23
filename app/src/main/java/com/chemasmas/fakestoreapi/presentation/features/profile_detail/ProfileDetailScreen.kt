package com.chemasmas.fakestoreapi.presentation.features.profile_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.chemasmas.fakestoreapi.R
import com.chemasmas.fakestoreapi.core.designSystem.previewsConfig.PhonePreview
import com.chemasmas.fakestoreapi.presentation.features.users_list.ShowErrorAndRetry
import com.chemasmas.fakestoreapi.presentation.theme.FakeStoreAPiTheme

@Composable
fun ProfileDetailScreenContainer(
    viewModel: ProfileDetailViewModel = hiltViewModel(),
    performOnBackButton: () -> Unit,
    userId: Int
) {
    val state by viewModel.state.collectAsState()
    viewModel.getUserDetail(userId = userId)

    ProfileDetailScreen(
        state = state,
        performOnBackButton = performOnBackButton,
        performRetry = { viewModel.getUserDetail(userId = userId) }
    )
}


@Composable
fun ProfileDetailScreen(
    state: ProfileDetailViewModel.ProfileDetailState,
    performOnBackButton: () -> Unit,
    performRetry: () -> Unit,
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {

            Row(
                modifier = Modifier
                    .align(Alignment.CenterStart),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Button(
                    onClick = performOnBackButton,
                    modifier = Modifier
                ) {
                    Text(
                        context.getString(R.string.back),
                    )
                }

            }

            Row(
                modifier = Modifier
                    .align(Alignment.Center),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = context.getString(R.string.user_info),
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.W800,
                    ),
                )

            }


        }
        UserInfoComponent(state = state, performRetry = performRetry)
    }
}

@Composable
fun UserInfoComponent(
    state: ProfileDetailViewModel.ProfileDetailState,
    performRetry: () -> Unit
) {
    val userDetail = state.userDetail
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 36.dp)
    ) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else if (state.errorService != null) {
            ShowErrorAndRetry(
                errorMessage = state.errorService,
                performRetry = performRetry
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(userDetail?.profileIcon)
                        .crossfade(true)
                        .error(R.drawable.user_placeholder)
                        .build(),
                    placeholder = painterResource(R.drawable.user_placeholder),
                    contentDescription = "User Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .height(250.dp)
                        .width(250.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))
                val fullName = userDetail?.let { "${it.firstName} ${it.lastName}" }
                Text(
                    text = fullName ?: "",
                    fontSize = 24.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = userDetail?.email ?: "",
                    fontSize = 24.sp
                )

            }
        }
    }

}

@PhonePreview
@Composable
fun ProfileDetailScreenPreview() {
    FakeStoreAPiTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
        ) {
            ProfileDetailScreen(
                state = ProfileDetailViewModel.ProfileDetailState(userDetail = userDetailMock),
                performOnBackButton = {},
                performRetry = {}
            )
        }
    }
}

@PhonePreview
@Composable
fun ProfileDetailScreenErrorServicePreview() {
    FakeStoreAPiTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
        ) {
            ProfileDetailScreen(
                state = ProfileDetailViewModel.ProfileDetailState(errorService = "An error occurred"),
                performOnBackButton = {},
                performRetry = {}
            )
        }
    }
}

@PhonePreview
@Composable
fun ProfileDetailScreenLoadingPreview() {
    FakeStoreAPiTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
        ) {
            ProfileDetailScreen(
                state = ProfileDetailViewModel.ProfileDetailState(isLoading = true),
                performOnBackButton = {},
                performRetry = {}
            )
        }
    }
}
