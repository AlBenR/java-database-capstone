import { API_BASE_URL } from "../config/config.js";

const DOCTOR_API = API_BASE_URL + '/doctor';

export async function getDoctors() {
  try {
    const response = await fetch(DOCTOR_API);
    const doctors = await response.json();
    return doctors;
  } catch (error) {
    return [];
  }
}

export async function deleteDoctor(id, token) {
  try {
    const response = await fetch(`${DOCTOR_API}/${id}`, {
      method: 'DELETE',
      headers: {
        'Authorization': `Bearer ${token}`
      }
    });
    const result = await response.json();
    return { success: response.ok, message: result.message };
  } catch (error) {
    return { success: false, message: error.message };
  }
}

export async function saveDoctor(doctor, token) {
  try {
    const response = await fetch(DOCTOR_API, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
      },
      body: JSON.stringify(doctor)
    });
    const result = await response.json();
    return { success: response.ok, message: result.message };
  } catch (error) {
    return { success: false, message: error.message };
  }
}

export async function filterDoctors(name, time, specialty) {
  try {
    const params = new URLSearchParams();
    if (name) params.append('name', name);
    if (time) params.append('time', time);
    if (specialty) params.append('specialty', specialty);
    
    const response = await fetch(`${DOCTOR_API}/filter?${params}`);
    const doctors = await response.json();
    return doctors;
  } catch (error) {
    return [];
  }
}