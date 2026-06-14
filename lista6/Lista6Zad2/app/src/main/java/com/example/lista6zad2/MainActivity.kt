package com.example.lista6zad2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.IconButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

data class Task(
    val id: Int,
    val description: String,
    val maxPoints: Int
)

data class AssignmentList(
    val id: String,
    val subject: String,
    val listNumber: Int,
    val grade: Double,
    val tasks: List<Task>
)

val sampleAssignmentLists = listOf(
    AssignmentList(
        "PUM1_L1",
        "Programowanie Urządzeń Mobilnych 1",
        1,
        4.5,
        listOf(
            Task(1, "Macierze", 3),
            Task(2, "Sprawdzenie palindromu", 3),
            Task(3, "Trójkąt Pascala", 4)
        )
    ),
    AssignmentList(
        "PUM1_L2",
        "Programowanie Urządzeń Mobilnych 1",
        2,
        5.0,
        listOf(
            Task(1, "Android Studio", 4),
            Task(2, "Meowmeow", 6)
        )
    ),
    AssignmentList(
        "SO_L1",
        "Systemy Operacyjne",
        1,
        3.5,
        listOf(
            Task(1, "Meowwwww", 5),
            Task(2, "mEOW", 5)
        )
    ),
    AssignmentList(
        "SO_L2",
        "Systemy Operacyjne",
        2,
        4.0,
        listOf(
            Task(1, "meowwyyy", 6),
            Task(2, "Meow meow meow", 4)
        )
    )
)

sealed class Screen(val route: String, val label: String) {
    object AssignmentLists : Screen("assignment_lists", "Listy")
    object GradesSummary : Screen("grades_summary", "Oceny")
    object ListDetail : Screen("list_detail/{listId}", "Szczegóły") {
        fun createRoute(listId: String) = "list_detail/$listId"
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                AssignmentApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssignmentApp() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista 6 - Zadanie 2") }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = currentRoute == Screen.AssignmentLists.route,
                    onClick = {
                        navController.navigate(Screen.AssignmentLists.route) {
                            launchSingleTop = true
                            popUpTo(Screen.AssignmentLists.route) { inclusive = false }
                        }
                    },
                    icon = { Icon(Icons.Default.List, contentDescription = "Listy") },
                    label = { Text("Listy") }
                )
                NavigationBarItem(
                    selected = currentRoute == Screen.GradesSummary.route,
                    onClick = {
                        navController.navigate(Screen.GradesSummary.route) {
                            launchSingleTop = true
                        }
                    },
                    icon = { Icon(Icons.Default.Star, contentDescription = "Oceny") },
                    label = { Text("Oceny") }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.AssignmentLists.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.AssignmentLists.route) {
                AssignmentListsScreen(
                    assignmentLists = sampleAssignmentLists,
                    onListClick = { selectedId ->
                        navController.navigate(Screen.ListDetail.createRoute(selectedId))
                    }
                )
            }

            composable(Screen.GradesSummary.route) {
                GradesSummaryScreen(assignmentLists = sampleAssignmentLists)
            }

            composable(
                route = Screen.ListDetail.route,
                arguments = listOf(
                    navArgument("listId") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val listId = backStackEntry.arguments?.getString("listId")
                val selectedList = sampleAssignmentLists.find { it.id == listId }

                if (selectedList != null) {
                    ListDetailScreen(
                        assignmentList = selectedList,
                        onBack = { navController.popBackStack() }
                    )
                } else {
                    Text(
                        text = "Nie znaleziono listy",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun AssignmentListsScreen(
    assignmentLists: List<AssignmentList>,
    onListClick: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(assignmentLists) { assignmentList ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onListClick(assignmentList.id) }
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = assignmentList.subject,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Lista ${assignmentList.listNumber}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "Ocena: ${assignmentList.grade}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Liczba zadań: ${assignmentList.tasks.size}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Composable
fun GradesSummaryScreen(assignmentLists: List<AssignmentList>) {
    val grouped = assignmentLists.groupBy { it.subject }
    val averages = grouped.mapValues { entry ->
        entry.value.map { it.grade }.average()
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(averages.toList()) { (subject, avg) ->
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = subject,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Średnia ocen: %.2f".format(avg),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListDetailScreen(
    assignmentList: AssignmentList,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("${assignmentList.subject} - Lista ${assignmentList.listNumber}") }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(assignmentList.tasks) { task ->
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Zadanie ${task.id}",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = task.description,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = "Maks. punktów: ${task.maxPoints}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}