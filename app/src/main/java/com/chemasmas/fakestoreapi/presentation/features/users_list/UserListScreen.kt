package com.chemasmas.fakestoreapi.presentation.features.users_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chemasmas.fakestoreapi.R
import com.chemasmas.fakestoreapi.core.designSystem.previewsConfig.PhonePreview
import com.chemasmas.fakestoreapi.presentation.features.countries.components.UserItem
import com.chemasmas.fakestoreapi.presentation.theme.FakeStoreAPiTheme

@Composable
fun UserListScreenContainer(
    viewModel: UserListViewModel = hiltViewModel(),
    onSelectUser: (id: Int) -> Unit,
    logout: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    UserListScreen(
        state = state,
        onDismissLogout = viewModel::dismissLogoutDialog,
        onWantToLogout = viewModel::wantToLogout,
        performRetry = viewModel::getUserList,
        onSelectUser = onSelectUser,
        logout = {
            viewModel.clearTokens()
            viewModel.clearState()
            logout.invoke()
        }
    )

}

@Composable
fun UserListScreen(
    state: UserListViewModel.UsersListState,
    onDismissLogout: () -> Unit,
    onWantToLogout: () -> Unit,
    performRetry: () -> Unit,
    onSelectUser: (id: Int) -> Unit,
    logout: () -> Unit
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
                    .align(Alignment.Center),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = context.getString(R.string.user_list),
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.W800,
                    ),
                )

            }
            Row(
                modifier = Modifier
                    .align(Alignment.CenterEnd),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Button(
                    onClick = onWantToLogout,
                    modifier = Modifier
                ) {
                    Text(
                        context.getString(R.string.logout),
                    )
                }

            }

        }

        UserListComponent(
            state = state,
            performRetry = performRetry,
            onSelectUser = onSelectUser
        )

        if (state.wantToLogout) {
            LogoutDialog(onDismiss = onDismissLogout, logout = logout)
        }
    }
}

@Composable
fun UserListComponent(
    state: UserListViewModel.UsersListState,
    performRetry: () -> Unit,
    onSelectUser: (id: Int) -> Unit,
) {

    Box(modifier = Modifier.fillMaxSize()) {
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
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.userList) { user ->
                    UserItem(
                        user = user,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { user.id?.let { onSelectUser(it) } }//TODO handled nulls
                            .padding(16.dp)
                    )
                }
            }

        }
    }

}

@Composable
fun ShowErrorAndRetry(
    errorMessage: String,
    performRetry: () -> Unit
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(errorMessage, color = Color.Black)
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { performRetry.invoke() }, modifier = Modifier
                .width(width = 100.dp)
        ) {
            Text(
                context.getString(R.string.retry),
            )
        }
    }

}

@PhonePreview
@Composable
fun ShowErrorAndRetryPreview() {
    FakeStoreAPiTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
        ) {
            ShowErrorAndRetry(errorMessage = "An error occurred", performRetry = {})
        }
    }
}

@PhonePreview
@Composable
fun UserListScreenPreview() {
    FakeStoreAPiTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
        ) {
            UserListScreen(
                state = UserListViewModel.UsersListState(
                    userList = simpleUserListMock.subList(0, 5)
                ),
                onDismissLogout = {},
                onWantToLogout = {},
                performRetry = {},
                onSelectUser = {},
                logout = {}
            )
        }
    }
}

@PhonePreview
@Composable
fun UserListComponentPreview() {
    FakeStoreAPiTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
        ) {
            UserListComponent(
                state = UserListViewModel.UsersListState(
                    userList = simpleUserListMock.subList(0, 5)
                ),
                performRetry = {},
                onSelectUser = {},
            )
        }
    }
}
