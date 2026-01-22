package com.android.pregnancyvitalstracker.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.pregnancyvitalstracker.R
import com.android.pregnancyvitalstracker.data.local.entities.LocalItem
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    var showAddDialog by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text("Track My Pregnancy") },
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { showAddDialog = true },
                icon = {
                    Icon(
                        painter = painterResource(R.drawable.add),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                },
                text = { Text("Add Vitals") }
            )
        }
    ) { padding ->
        HomeBody(contentPadding = padding)
    }

    if (showAddDialog) {
        VitalsDialog(onDismiss = { showAddDialog = false })
    }
}


@Composable
fun HomeBody(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues
) {
    ItemList(
        modifier = modifier.fillMaxSize(),
        contentPadding = contentPadding
    )
}


@Composable
fun ItemList(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel(),
    contentPadding: PaddingValues
) {
    val items by homeViewModel.allItems.collectAsState()

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(
            top = contentPadding.calculateTopPadding(),
            bottom = 80.dp
        )
    ) {
        items(items) { item ->
            VitalsItemCard(item = item)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VitalsItemCard(
    item: LocalItem,
    modifier: Modifier = Modifier
) {
    val formattedDate = remember(item.dateTime) {
        item.dateTime.format(
            DateTimeFormatter.ofPattern("dd MMM yyyy • hh:mm a")
        )
    }

    Card(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Row(modifier = Modifier.fillMaxWidth()) {
                VitalIconText(R.drawable.blood_pressure, "${item.bloodPressure} mmHg")
                Spacer(Modifier.weight(1f))
                VitalIconText(R.drawable.medical, "${item.hartRate} bpm")
            }

            Row(modifier = Modifier.fillMaxWidth()) {
                VitalIconText(R.drawable.weight_scale, "${item.weight} kg")
                Spacer(Modifier.weight(1f))
                VitalIconText(R.drawable.newborn, "${item.babyKicksCount} kicks")
            }

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

            Text(
                text = formattedDate,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}


@Composable
fun VitalIconText(
    icon: Int,
    text: String
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier.size(22.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )
    }
}


@Composable
fun VitalsDialog(
    onDismiss: () -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    var heartRate by remember { mutableStateOf("") }
    var bloodPressure by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var babyKicks by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Text(
                    text = "Add Vitals",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                OutlinedTextField(
                    value = heartRate,
                    onValueChange = { heartRate = it },
                    label = { Text("Heart Rate") },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.medical),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    singleLine = true
                )

                OutlinedTextField(
                    value = bloodPressure,
                    onValueChange = { bloodPressure = it },
                    label = { Text("Blood Pressure") },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.blood_pressure),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    singleLine = true
                )

                OutlinedTextField(
                    value = weight,
                    onValueChange = { weight = it },
                    label = { Text("Weight") },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.weight_scale),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    singleLine = true
                )

                OutlinedTextField(
                    value = babyKicks,
                    onValueChange = { babyKicks = it },
                    label = { Text("Baby Kicks") },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.newborn),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    singleLine = true
                )

                Spacer(Modifier.height(8.dp))

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        homeViewModel.insertItem(
                            LocalItem(
                                bloodPressure = bloodPressure,
                                hartRate = heartRate,
                                weight = weight,
                                babyKicksCount = babyKicks,
                                dateTime = LocalDateTime.now()
                            )
                        )
                        onDismiss()
                    }
                ) {
                    Text("Save")
                }
            }
        }
    }
}
