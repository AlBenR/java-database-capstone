/*
  Step-by-Step Explanation of Header Section Rendering
  This code dynamically renders the header section based on user role and session status.
*/

// Define the renderHeader function
function renderHeader() {
    // Select the header div
    const headerDiv = document.getElementById("header");

    // Check if current page is the root page
    if (window.location.pathname.endsWith("/")) {
        localStorage.removeItem("userRole");
        localStorage.removeItem("token");
        headerDiv.innerHTML = `
            <header class="header">
                <div class="logo-section">
                    <img src="./assets/images/logo/logo.png" alt="Hospital CRM Logo" class="logo-img">
                    <span class="logo-title">Hospital CMS</span>
                </div>
            </header>`;
        return;
    }

    // Retrieve user's role and token from localStorage
    const role = localStorage.getItem("userRole");
    const token = localStorage.getItem("token");

    // Handle session expiry or invalid login
    if ((role === "loggedPatient" || role === "admin" || role === "doctor") && !token) {
        localStorage.removeItem("userRole");
        alert("Sesión expirada o inicio de sesión inválido. Por favor, inicie sesión nuevamente.");
        window.location.href = "/";
        return;
    }

    // Initialize header content
    let headerContent = `<header class="header">
        <div class="logo-section">
            <img src="./assets/images/logo/logo.png" alt="Hospital CRM Logo" class="logo-img">
            <span class="logo-title">Hospital CMS</span>
        </div>
        <nav>`;

    // Add role-specific header content
    if (role === "admin") {
        headerContent += `
            <button id="addDocBtn" class="adminBtn" onclick="openModal('addDoctor')">Agregar Doctor</button>
            <a href="#" onclick="logout()">Cerrar sesión</a>`;
    } else if (role === "doctor") {
        headerContent += `
            <button class="adminBtn" onclick="selectRole('doctor')">Inicio</button>
            <a href="#" onclick="logout()">Cerrar sesión</a>`;
    } else if (role === "patient") {
        headerContent += `
            <button id="patientLogin" class="adminBtn">Iniciar sesión</button>
            <button id="patientSignup" class="adminBtn">Registrarse</button>`;
    } else if (role === "loggedPatient") {
        headerContent += `
            <button id="home" class="adminBtn" onclick="window.location.href='/pages/loggedPatientDashboard.html'">Inicio</button>
            <button id="patientAppointments" class="adminBtn" onclick="window.location.href='/pages/patientAppointments.html'">Citas</button>
            <a href="#" onclick="logoutPatient()">Cerrar sesión</a>`;
    }

    // Close the header section
    headerContent += `</nav></header>`;

    // Render the header content
    headerDiv.innerHTML = headerContent;

    // Attach event listeners to header buttons
    attachHeaderButtonListeners();
}

// Helper function to attach event listeners
function attachHeaderButtonListeners() {
    // Add event listeners for patient login buttons
    const patientLoginBtn = document.getElementById("patientLogin");
    if (patientLoginBtn) {
        patientLoginBtn.addEventListener("click", function() {
            openModal('patientLogin');
        });
    }

    const patientSignupBtn = document.getElementById("patientSignup");
    if (patientSignupBtn) {
        patientSignupBtn.addEventListener("click", function() {
            openModal('patientSignup');
        });
    }
}

// Logout function for admin and doctor
function logout() {
    localStorage.removeItem("token");
    localStorage.removeItem("userRole");
    window.location.href = "/";
}

// Logout function for patient
function logoutPatient() {
    localStorage.removeItem("token");
    localStorage.setItem("userRole", "patient");
    window.location.href = "/pages/patientDashboard.html";
}

// Initialize header when script loads
renderHeader();