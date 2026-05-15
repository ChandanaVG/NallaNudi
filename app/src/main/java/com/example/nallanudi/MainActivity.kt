package com.example.nallanudi

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.*

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NallaNudiApp()
        }
    }
}

data class KannadaWord(
    val english: String,
    val kannada: String,
    val pronunciation: String,
    val category: String,
    val meaning: String
)

@Composable
fun NallaNudiApp() {

    var currentScreen by remember {
        mutableStateOf("login")
    }

    var registeredUsername by remember {
        mutableStateOf("")
    }

    var registeredPassword by remember {
        mutableStateOf("")
    }

    var currentUser by remember {
        mutableStateOf("")
    }

    val savedWords = remember {
        mutableStateListOf<KannadaWord>()
    }

    when (currentScreen) {

        "login" -> {

            LoginScreen(

                onLogin = { username, password ->

                    if (
                        username == registeredUsername &&
                        password == registeredPassword &&
                        username.isNotEmpty()
                    ) {

                        currentUser = username
                        currentScreen = "home"

                    } else {

                        currentScreen = "home"
                    }
                },

                onRegisterClick = {
                    currentScreen = "register"
                }
            )
        }

        "register" -> {

            RegisterScreen(

                onRegister = { username, password ->

                    registeredUsername = username
                    registeredPassword = password

                    currentScreen = "login"
                }
            )
        }

        "home" -> {

            HomeScreen(
                username = currentUser,
                savedWords = savedWords
            )
        }
    }
}

@Composable
fun LoginScreen(
    onLogin: (String, String) -> Unit,
    onRegisterClick: () -> Unit
) {

    val context = LocalContext.current

    var username by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFF4A148C),
                        Color(0xFF7B1FA2)
                    )
                )
            )
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .align(Alignment.Center),

            shape = RoundedCornerShape(24.dp)
        ) {

            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    "Nalla Nudi",
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    "Kannada Educational Learning App",
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(30.dp))

                OutlinedTextField(
                    value = username,
                    onValueChange = {
                        username = it
                    },

                    modifier = Modifier.fillMaxWidth(),

                    label = {
                        Text("Username")
                    },

                    leadingIcon = {
                        Icon(Icons.Default.Person, null)
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                    },

                    modifier = Modifier.fillMaxWidth(),

                    label = {
                        Text("Password")
                    },

                    leadingIcon = {
                        Icon(Icons.Default.Lock, null)
                    }
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {

                        if (
                            username.isEmpty() ||
                            password.isEmpty()
                        ) {

                            Toast.makeText(
                                context,
                                "Enter all fields",
                                Toast.LENGTH_SHORT
                            ).show()

                        } else {

                            onLogin(username, password)

                            Toast.makeText(
                                context,
                                "Login Successful",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp),

                    shape = RoundedCornerShape(16.dp)
                ) {

                    Text(
                        "Login",
                        fontSize = 18.sp
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    "Don't have an account? Register",
                    color = Color.Blue,

                    modifier = Modifier.clickable {
                        onRegisterClick()
                    }
                )
            }
        }
    }
}

@Composable
fun RegisterScreen(
    onRegister: (String, String) -> Unit
) {

    val context = LocalContext.current

    var username by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFF1A237E),
                        Color(0xFF3949AB)
                    )
                )
            )
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .align(Alignment.Center),

            shape = RoundedCornerShape(24.dp)
        ) {

            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    "Create Account",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(30.dp))

                OutlinedTextField(
                    value = username,
                    onValueChange = {
                        username = it
                    },

                    modifier = Modifier.fillMaxWidth(),

                    label = {
                        Text("Username")
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                    },

                    modifier = Modifier.fillMaxWidth(),

                    label = {
                        Text("Password")
                    }
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {

                        if (
                            username.isEmpty() ||
                            password.isEmpty()
                        ) {

                            Toast.makeText(
                                context,
                                "Fill all fields",
                                Toast.LENGTH_SHORT
                            ).show()

                        } else {

                            onRegister(username, password)

                            Toast.makeText(
                                context,
                                "Registration Successful",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp),

                    shape = RoundedCornerShape(16.dp)
                ) {

                    Text(
                        "Register",
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    username: String,
    savedWords: MutableList<KannadaWord>
) {

    val context = LocalContext.current

    var selectedTab by remember {
        mutableStateOf(0)
    }

    var searchText by remember {
        mutableStateOf("")
    }

    var selectedCategory by remember {
        mutableStateOf("All")
    }

    val categories = listOf(
        "All",
        "Maths",
        "Science",
        "Social",
        "Technology",
        "Education"
    )

    val tts = remember {
        TextToSpeech(context) {}
    }

    val words = listOf(

        // ---------------- MATHS ----------------

        KannadaWord(
            "Mathematics",
            "ಗಣಿತ",
            "Ganita",
            "Maths",
            "Study of numbers and calculations."
        ),

        KannadaWord(
            "Algebra",
            "ಬೀಜಗಣಿತ",
            "Beejaganita",
            "Maths",
            "Branch of mathematics using symbols and equations."
        ),

        KannadaWord(
            "Geometry",
            "ರೇಖಾಗಣಿತ",
            "Rekha Ganita",
            "Maths",
            "Study of shapes and dimensions."
        ),

        KannadaWord(
            "Trigonometry",
            "ತ್ರಿಕೋಣಮಿತಿ",
            "Trikonamiti",
            "Maths",
            "Study of triangles and angles."
        ),

        KannadaWord(
            "Statistics",
            "ಸಾಂಖ್ಯಿಕಶಾಸ್ತ್ರ",
            "Sankhyika Shastra",
            "Maths",
            "Study of data and analysis."
        ),

        KannadaWord(
            "Equation",
            "ಸಮೀಕರಣ",
            "Sameekarana",
            "Maths",
            "Mathematical statement showing equality."
        ),

        KannadaWord(
            "Fraction",
            "ಭಿನ್ನರಾಶಿ",
            "Bhinnarashi",
            "Maths",
            "Represents part of a whole."
        ),

        KannadaWord(
            "Percentage",
            "ಶೇಕಡಾವಾರು",
            "Shekadavaru",
            "Maths",
            "Value expressed out of hundred."
        ),

        KannadaWord(
            "Average",
            "ಸರಾಸರಿ",
            "Sarasari",
            "Maths",
            "Central value of a group of numbers."
        ),

        KannadaWord(
            "Probability",
            "ಸಂಭಾವ್ಯತೆ",
            "Sambhavyate",
            "Maths",
            "Chance of an event occurring."
        ),

        KannadaWord(
            "Calculus",
            "ಕಲನಶಾಸ್ತ್ರ",
            "Kalanashastra",
            "Maths",
            "Study of continuous change and motion."
        ),

        KannadaWord(
            "Equation",
            "ಸಮೀಕರಣ",
            "Sameekarana",
            "Maths",
            "A mathematical statement showing equality."
        ),

        KannadaWord(
            "Fraction",
            "ಭಿನ್ನರಾಶಿ",
            "Bhinnarashi",
            "Maths",
            "Represents a part of a whole."
        ),

        KannadaWord(
            "Percentage",
            "ಶೇಕಡಾವಾರು",
            "Shekadavaru",
            "Maths",
            "A value expressed out of one hundred."
        ),

        KannadaWord(
            "Probability",
            "ಸಂಭಾವ್ಯತೆ",
            "Sambhavyate",
            "Maths",
            "Chance of an event occurring."
        ),

        KannadaWord(
            "Matrix",
            "ಮ್ಯಾಟ್ರಿಕ್ಸ್",
            "Matrix",
            "Maths",
            "Arrangement of numbers in rows and columns."
        ),

        KannadaWord(
            "Vector",
            "ವೇಕ್ಟರ್",
            "Vector",
            "Maths",
            "Quantity having magnitude and direction."
        ),

        KannadaWord(
            "Coordinate",
            "ನಿರ್ದೇಶಾಂಕ",
            "Nirdeshanka",
            "Maths",
            "Position of a point on a graph."
        ),

        // ---------------- SCIENCE ----------------

        KannadaWord(
            "Science",
            "ವಿಜ್ಞಾನ",
            "Vijnana",
            "Science",
            "Study of the natural world."
        ),

        KannadaWord(
            "Physics",
            "ಭೌತಶಾಸ್ತ್ರ",
            "Bhoutha Shastra",
            "Science",
            "Study of matter and energy."
        ),

        KannadaWord(
            "Chemistry",
            "ರಸಾಯನಶಾಸ್ತ್ರ",
            "Rasayana Shastra",
            "Science",
            "Study of substances and reactions."
        ),

        KannadaWord(
            "Biology",
            "ಜೀವಶಾಸ್ತ್ರ",
            "Jeeva Shastra",
            "Science",
            "Study of living organisms."
        ),

        KannadaWord(
            "Atom",
            "ಅಣು",
            "Anu",
            "Science",
            "Smallest unit of matter."
        ),

        KannadaWord(
            "Electricity",
            "ವಿದ್ಯುತ್",
            "Vidyut",
            "Science",
            "Form of energy from electric charge."
        ),

        KannadaWord(
            "Gravity",
            "ಗುರುತ್ವಾಕರ್ಷಣೆ",
            "Gurutvakarshane",
            "Science",
            "Force pulling objects toward Earth."
        ),

        KannadaWord(
            "Photosynthesis",
            "ಪ್ರಕಾಶಸಂಶ್ಲೇಷಣೆ",
            "Prakasha Sanshleshane",
            "Science",
            "Process by which plants prepare food."
        ),

        KannadaWord(
            "Ecosystem",
            "ಪರಿಸರ ವ್ಯವಸ್ಥೆ",
            "Parisara Vyavasthe",
            "Science",
            "Interaction of living and nonliving things."
        ),

        KannadaWord(
            "Molecule",
            "ಅಣುಗುಚ್ಛ",
            "Anuguchcha",
            "Science",
            "Group of atoms bonded together."
        ),

        KannadaWord(
            "Respiration",
            "ಉಸಿರಾಟ",
            "Usirata",
            "Science",
            "Process of breathing and energy release."
        ),

        KannadaWord(
            "Ecosystem",
            "ಪರಿಸರ ವ್ಯವಸ್ಥೆ",
            "Parisara Vyavasthe",
            "Science",
            "Interaction between organisms and environment."
        ),

        KannadaWord(
            "Force",
            "ಬಲ",
            "Bala",
            "Science",
            "Push or pull acting on an object."
        ),

        KannadaWord(
            "Energy",
            "ಶಕ್ತಿ",
            "Shakti",
            "Science",
            "Capacity to perform work."
        ),

        KannadaWord(
            "Magnetism",
            "ಚುಂಭಕತ್ವ",
            "Chumbakatva",
            "Science",
            "Force related to magnets and magnetic fields."
        ),

        KannadaWord(
            "Solar System",
            "ಸೌರಮಂಡಲ",
            "Soura Mandala",
            "Science",
            "Collection of planets around the Sun."
        ),

        // ---------------- SOCIAL ----------------

        KannadaWord(
            "History",
            "ಇತಿಹಾಸ",
            "Ithihasa",
            "Social",
            "Study of past events."
        ),

        KannadaWord(
            "Geography",
            "ಭೂಗೋಳಶಾಸ್ತ್ರ",
            "Bhoogola Shastra",
            "Social",
            "Study of Earth and environment."
        ),

        KannadaWord(
            "Democracy",
            "ಪ್ರಜಾಪ್ರಭುತ್ವ",
            "Prajaprabhutva",
            "Social",
            "Government elected by people."
        ),

        KannadaWord(
            "Constitution",
            "ಸಂವಿಧಾನ",
            "Samvidhana",
            "Social",
            "Supreme law of a country."
        ),

        KannadaWord(
            "Economics",
            "ಅರ್ಥಶಾಸ್ತ್ರ",
            "Artha Shastra",
            "Social",
            "Study of money and resources."
        ),

        KannadaWord(
            "Parliament",
            "ಸಂಸತ್ತು",
            "Samsattu",
            "Social",
            "Law-making body of government."
        ),

        KannadaWord(
            "Citizenship",
            "ಪೌರತ್ವ",
            "Pouratva",
            "Social",
            "Status of being a citizen."
        ),

        KannadaWord(
            "Election",
            "ಚುನಾವಣೆ",
            "Chunavane",
            "Social",
            "Process of choosing leaders."
        ),

        KannadaWord(
            "Culture",
            "ಸಂಸ್ಕೃತಿ",
            "Samskruti",
            "Social",
            "Traditions and lifestyle of people."
        ),

        KannadaWord(
            "Environment",
            "ಪರಿಸರ",
            "Parisara",
            "Social",
            "Natural world around us."
        ),

        KannadaWord(
            "Citizenship",
            "ಪೌರತ್ವ",
            "Pouratva",
            "Social",
            "Legal status of belonging to a country."
        ),

        KannadaWord(
            "Parliament",
            "ಸಂಸತ್ತು",
            "Samsatthu",
            "Social",
            "Legislative body of a nation."
        ),

        KannadaWord(
            "Election",
            "ಚುನಾವಣೆ",
            "Chunavane",
            "Social",
            "Process of choosing leaders by voting."
        ),

        KannadaWord(
            "Population",
            "ಜನಸಂಖ್ಯೆ",
            "Janasankhye",
            "Social",
            "Number of people living in an area."
        ),

        KannadaWord(
            "Agriculture",
            "ಕೃಷಿ",
            "Krushi",
            "Social",
            "Practice of farming and cultivation."
        ),

        KannadaWord(
            "Freedom",
            "ಸ್ವಾತಂತ್ರ್ಯ",
            "Swatantrya",
            "Social",
            "State of being independent."
        ),

        KannadaWord(
            "Human Rights",
            "ಮಾನವ ಹಕ್ಕುಗಳು",
            "Manava Hakkugalu",
            "Social",
            "Basic rights belonging to every person."
        ),

        // ---------------- TECHNOLOGY ----------------

        KannadaWord(
            "Computer",
            "ಗಣಕಯಂತ್ರ",
            "Ganaka Yantra",
            "Technology",
            "Electronic machine for processing data."
        ),

        KannadaWord(
            "Programming",
            "ಕಾರ್ಯಕ್ರಮ ಬರವಣಿಗೆ",
            "Karyakrama Baravanige",
            "Technology",
            "Writing instructions for computers."
        ),

        KannadaWord(
            "Artificial Intelligence",
            "ಕೃತಕ ಬುದ್ಧಿಮತ್ತೆ",
            "Krutaka Buddhimatte",
            "Technology",
            "Machines simulating human intelligence."
        ),

        KannadaWord(
            "Internet",
            "ಅಂತರಜಾಲ",
            "Antarajala",
            "Technology",
            "Global network connecting computers."
        ),

        KannadaWord(
            "Software",
            "ತಂತ್ರಾಂಶ",
            "Tantransha",
            "Technology",
            "Programs running on a computer."
        ),

        KannadaWord(
            "Hardware",
            "ಯಂತ್ರಾಂಶ",
            "Yantransha",
            "Technology",
            "Physical parts of a computer."
        ),

        KannadaWord(
            "Algorithm",
            "ಅಲ್ಗೋರಿದಮ್",
            "Algorithm",
            "Technology",
            "Step-by-step problem-solving process."
        ),

        KannadaWord(
            "Cloud Computing",
            "ಮೇಘ ಗಣನೆ",
            "Megha Ganane",
            "Technology",
            "Using internet-based computing services."
        ),

        KannadaWord(
            "Database",
            "ದತ್ತಸಂಗ್ರಹ",
            "Dattasangraha",
            "Technology",
            "Organized collection of information."
        ),

        KannadaWord(
            "Network",
            "ಜಾಲತಂತ್ರ",
            "Jaalatantra",
            "Technology",
            "Connected system of computers."
        ),

        KannadaWord(
            "Cyber Security",
            "ಸೈಬರ್ ಭದ್ರತೆ",
            "Cyber Bhadrate",
            "Technology",
            "Protection of digital systems and data."
        ),

        KannadaWord(
            "Algorithm",
            "ಅಲ್ಗಾರಿಥಮ್",
            "Algorithm",
            "Technology",
            "Step-by-step procedure to solve problems."
        ),


        KannadaWord(
            "Cloud Computing",
            "ಮೇಘ ಗಣಕವಿದ್ಯೆ",
            "Megha Ganaka Vidye",
            "Technology",
            "Using internet-based computing services."
        ),

        KannadaWord(
            "Machine Learning",
            "ಯಂತ್ರ ಅಧ್ಯಯನ",
            "Yantra Adhyayana",
            "Technology",
            "AI system learning from data."
        ),
        // ---------------- EDUCATION ----------------

        KannadaWord(
            "Teacher",
            "ಶಿಕ್ಷಕ",
            "Shikshaka",
            "Education",
            "Person who teaches students."
        ),

        KannadaWord(
            "Student",
            "ವಿದ್ಯಾರ್ಥಿ",
            "Vidyarthi",
            "Education",
            "Person who learns subjects."
        ),

        KannadaWord(
            "Examination",
            "ಪರೀಕ್ಷೆ",
            "Pareekshe",
            "Education",
            "Test of knowledge and skills."
        ),

        KannadaWord(
            "Assignment",
            "ಕಾರ್ಯ",
            "Karya",
            "Education",
            "Task given to students."
        ),

        KannadaWord(
            "Scholarship",
            "ವಿದ್ಯಾರ್ಥಿವೇತನ",
            "Vidyarthi Vethana",
            "Education",
            "Financial support for education."
        ),

        KannadaWord(
            "Knowledge",
            "ಜ್ಞಾನ",
            "Jnana",
            "Education",
            "Information and understanding gained."
        ),

        KannadaWord(
            "Library",
            "ಗ್ರಂಥಾಲಯ",
            "Granthalaya",
            "Education",
            "Place containing books and resources."
        ),

        KannadaWord(
            "Research",
            "ಸಂಶೋಧನೆ",
            "Samshodhane",
            "Education",
            "Detailed study to discover facts."
        ),

        KannadaWord(
            "University",
            "ವಿಶ್ವವಿದ್ಯಾಲಯ",
            "Vishwavidyalaya",
            "Education",
            "Higher education institution."
        ),

        KannadaWord(
            "Curriculum",
            "ಪಠ್ಯಕ್ರಮ",
            "Pathyakrama",
            "Education",
            "Subjects included in a course."
        ),

        KannadaWord(
            "Examination",
            "ಪರೀಕ್ಷೆ",
            "Pareekshe",
            "Education",
            "Test conducted to measure knowledge."
        ),

        KannadaWord(
            "Assignment",
            "ಕಾರ್ಯಭಾರ",
            "Karyabhara",
            "Education",
            "Task given to students for practice."
        ),

        KannadaWord(
            "Scholarship",
            "ವಿದ್ಯಾರ್ಥಿವೇತನ",
            "Vidyarthi Vethana",
            "Education",
            "Financial support for students."
        ),

        KannadaWord(
            "Curriculum",
            "ಪಾಠ್ಯಕ್ರಮ",
            "Paathyakrama",
            "Education",
            "Planned course structure for learning."
        ),

        KannadaWord(
            "Knowledge",
            "ಜ್ಞಾನ",
            "Jnana",
            "Education",
            "Information and understanding gained through learning."
        ),

        KannadaWord(
            "Research",
            "ಸಂಶೋಧನೆ",
            "Samshodhane",
            "Education",
            "Detailed study to discover new facts."
        ),

        KannadaWord(
            "Library",
            "ಗ್ರಂಥಾಲಯ",
            "Granthalaya",
            "Education",
            "Place containing books and learning resources."
        ),

        KannadaWord(
            "Laboratory",
            "ಪ್ರಯೋಗಾಲಯ",
            "Prayogalaya",
            "Education",
            "Room used for scientific experiments."
        )
    )

    val filteredWords = words.filter {

        (
                it.english.contains(searchText, true) ||
                        it.kannada.contains(searchText, true)
                )

                &&

                (
                        selectedCategory == "All" ||
                                it.category == selectedCategory
                        )
    }

    Scaffold(

        bottomBar = {

            NavigationBar {

                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = {
                        selectedTab = 0
                    },
                    icon = {
                        Icon(Icons.Default.Home, null)
                    },
                    label = {
                        Text("Home")
                    }
                )

                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = {
                        selectedTab = 1
                    },
                    icon = {
                        Icon(Icons.Default.Search, null)
                    },
                    label = {
                        Text("Search")
                    }
                )

                NavigationBarItem(
                    selected = selectedTab == 2,
                    onClick = {
                        selectedTab = 2
                    },
                    icon = {
                        Icon(Icons.Default.Favorite, null)
                    },
                    label = {
                        Text("Saved")
                    }
                )

                NavigationBarItem(
                    selected = selectedTab == 3,
                    onClick = {
                        selectedTab = 3
                    },
                    icon = {
                        Icon(Icons.Default.Person, null)
                    },
                    label = {
                        Text("Profile")
                    }
                )
            }
        }

    ) { padding ->

        when (selectedTab) {

            0 -> {

                LazyColumn(
                    modifier = Modifier
                        .padding(padding)
                        .padding(16.dp)
                ) {

                    item {

                        Text(
                            "Welcome $username 👋",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(20.dp))
                    }

                    items(words) { word ->

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 14.dp),

                            shape = RoundedCornerShape(20.dp)
                        ) {

                            Column(
                                modifier = Modifier.padding(18.dp)
                            ) {

                                Text(
                                    word.english,
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    word.kannada,
                                    fontSize = 30.sp,
                                    color = Color(0xFF6A1B9A)
                                )

                                Spacer(modifier = Modifier.height(6.dp))

                                Text("Pronunciation: ${word.pronunciation}")

                                Spacer(modifier = Modifier.height(6.dp))

                                Text(word.meaning)

                                Spacer(modifier = Modifier.height(10.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End
                                ) {

                                    IconButton(
                                        onClick = {

                                            tts.speak(
                                                word.pronunciation,
                                                TextToSpeech.QUEUE_FLUSH,
                                                null,
                                                null
                                            )
                                        }
                                    ) {

                                        Icon(
                                            Icons.Default.VolumeUp,
                                            null
                                        )
                                    }

                                    IconButton(
                                        onClick = {

                                            if (!savedWords.contains(word)) {

                                                savedWords.add(word)

                                                Toast.makeText(
                                                    context,
                                                    "Saved Successfully",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        }
                                    ) {

                                        Icon(
                                            Icons.Default.Favorite,
                                            null,
                                            tint = Color.Red
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            1 -> {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(16.dp)
                ) {

                    OutlinedTextField(
                        value = searchText,
                        onValueChange = {
                            searchText = it
                        },

                        modifier = Modifier.fillMaxWidth(),

                        label = {
                            Text("Search Words")
                        },

                        leadingIcon = {
                            Icon(Icons.Default.Search, null)
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    LazyRow {

                        items(categories) { category ->

                            FilterChip(
                                selected = selectedCategory == category,

                                onClick = {
                                    selectedCategory = category
                                },

                                label = {
                                    Text(category)
                                },

                                modifier = Modifier.padding(end = 8.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    LazyColumn {

                        items(filteredWords) { word ->

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 12.dp)
                            ) {

                                Column(
                                    modifier = Modifier.padding(18.dp)
                                ) {

                                    Text(
                                        word.english,
                                        fontSize = 22.sp,
                                        fontWeight = FontWeight.Bold
                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text(
                                        word.kannada,
                                        fontSize = 28.sp,
                                        color = Color(0xFF6A1B9A)
                                    )

                                    Spacer(modifier = Modifier.height(6.dp))

                                    Text(
                                        "Category: ${word.category}"
                                    )

                                    Spacer(modifier = Modifier.height(6.dp))

                                    Text(word.meaning)
                                }
                            }
                        }
                    }
                }
            }

            2 -> {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {

                    item {

                        Text(
                            "Saved Words ❤️",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(20.dp))
                    }

                    items(savedWords) { word ->

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 12.dp)
                        ) {

                            Column(
                                modifier = Modifier.padding(18.dp)
                            ) {

                                Text(
                                    word.english,
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(modifier = Modifier.height(6.dp))

                                Text(
                                    word.kannada,
                                    fontSize = 28.sp,
                                    color = Color(0xFF6A1B9A)
                                )

                                Spacer(modifier = Modifier.height(6.dp))

                                Text(word.meaning)
                            }
                        }
                    }
                }
            }

            3 -> {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(30.dp),

                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Spacer(modifier = Modifier.height(50.dp))

                    Icon(
                        Icons.Default.Person,
                        contentDescription = null,
                        modifier = Modifier.size(100.dp)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        username,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        "Kannada Educational Learning User 🇮🇳",
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    Text(
                        "Saved Words: ${savedWords.size}",
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}