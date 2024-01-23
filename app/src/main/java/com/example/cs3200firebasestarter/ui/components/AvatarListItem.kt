package com.example.cs3200firebasestarter.ui.components


import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cs3200firebasestarter.ui.models.Avatar
import com.example.cs3200firebasestarter.ui.theme.CS3200FirebaseStarterTheme

@Composable
fun AvatarListItem(
    avatar: Avatar,
    onEditPressed: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = avatar.name ?: "",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                )
                IconButton(onClick = onEditPressed) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Button")
                }
            }


            Spacer(modifier = Modifier.height(8.dp))

            AvatarAttribute(label = "Description", value = avatar.description ?: "")
            AvatarAttribute(label = "Age", value = "${avatar.age} years" ?: "")
            AvatarAttribute(label = "Height", value = "${avatar.height} feet")
            AvatarAttribute(label = "Race", value = avatar.race ?: "")
            AvatarAttribute(label = "Class", value = avatar.occupation ?: "")
            AvatarAttribute(label = "Gender", value = avatar.gender ?: "")



            Spacer(modifier = Modifier.height(8.dp))

            Divider()
        }
    }
}

@Composable
fun AvatarAttribute(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp)
    ) {
        Text(
            text = "$label:",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.width(100.dp)
        )
        Text(text = value)
    }
}

//@Preview
//@Composable
//fun AvatarListItemPreview() {
//    CS3200FirebaseStarterTheme {
//        AvatarListItem(
//            avatar = Avatar(
//                name = "Gorlock",
//                description = "Big Troll who can kill with one blow",
//                age = "5 years",
//                id = "fcs3k3lkm2309mgfs",
//                race = "Troll",
//                occupation = "Pest Controller",
//                height = "8",
//                gender = "Male",
//                userId = "23oiknmf09sj0982jlkjsdf"
//            )
//        )
//    }
//}
