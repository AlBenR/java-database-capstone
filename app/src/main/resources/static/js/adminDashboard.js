import { openModal } from '../components/modals.js';
import { getDoctors, filterDoctors, saveDoctor } from './services/doctorServices.js';
import { createDoctorCard } from './components/doctorCard.js';

document.getElementById('addDocBtn').addEventListener('click', () => {
  openModal('addDoctor');
});

document.addEventListener('DOMContentLoaded', () => {
  loadDoctorCards();
});

async function loadDoctorCards() {
  try {
    const doctors = await getDoctors();
    const contentDiv = document.getElementById("content");
    contentDiv.innerHTML = "";
    doctors.forEach(doctor => {
      const card = createDoctorCard(doctor);
      contentDiv.appendChild(card);
    });
  } catch (error) {
    console.error(error);
  }
}

document.getElementById("searchBar").addEventListener("input", filterDoctorsOnChange);
document.getElementById("filterTime").addEventListener("change", filterDoctorsOnChange);
document.getElementById("filterSpecialty").addEventListener("change", filterDoctorsOnChange);

async function filterDoctorsOnChange() {
  try {
    const name = document.getElementById("searchBar").value;
    const time = document.getElementById("filterTime").value;
    const specialty = document.getElementById("filterSpecialty").value;
    
    const normalizedName = name || null;
    const normalizedTime = time || null;
    const normalizedSpecialty = specialty || null;
    
    const doctors = await filterDoctors(normalizedName, normalizedTime, normalizedSpecialty);
    
    if (doctors.length > 0) {
      renderDoctorCards(doctors);
    } else {
      document.getElementById("content").innerHTML = "<p>No doctors found with the given filters.</p>";
    }
  } catch (error) {
    alert("Error filtering doctors");
  }
}

function renderDoctorCards(doctors) {
  const contentDiv = document.getElementById("content");
  contentDiv.innerHTML = "";
  doctors.forEach(doctor => {
    const card = createDoctorCard(doctor);
    contentDiv.appendChild(card);
  });
}

async function adminAddDoctor() {
  const name = document.getElementById('doctorName').value;
  const email = document.getElementById('doctorEmail').value;
  const phone = document.getElementById('doctorPhone').value;
  const password = document.getElementById('doctorPassword').value;
  const specialty = document.getElementById('doctorSpecialty').value;
  
  const availableTimes = [];
  const timeCheckboxes = document.querySelectorAll('input[name="availableTimes"]:checked');
  timeCheckboxes.forEach(checkbox => {
    availableTimes.push(checkbox.value);
  });
  
  const token = localStorage.getItem('token');
  if (!token) {
    alert('No authentication token found');
    return;
  }
  
  const doctor = {
    name,
    email,
    phone,
    password,
    specialty,
    availableTimes
  };
  
  const result = await saveDoctor(doctor, token);
  
  if (result.success) {
    alert('Doctor added successfully');
    closeModal('addDoctor');
    window.location.reload();
  } else {
    alert('Error adding doctor: ' + result.message);
  }
}