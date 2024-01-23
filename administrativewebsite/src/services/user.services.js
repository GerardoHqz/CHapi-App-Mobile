const BASE_URL = "https://apichapi.me";

const services = {};

//---------------------------------------------------------------------------------------------------------------------
//AUTHENTICATION


// Retorna el token y el rol del usuario si las credenciales son correctas
services.login = async (username, password) => {
    // fetch recibe un init object como segundo parámetro para configuraciones adicionales de la petición
    const response = await fetch(`${BASE_URL}/login`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        // Dentro de body van los datos que enviamos
        body: JSON.stringify({
            username: username,
            password: password
        })
    });

    if(response.ok) {
        const data = await response.json();
        return data;
    }
    return {};
}

// Retorna el usuario y el rol correspondientes a ese token
//Falta función de verificación
services.verifyToken = async (token) => {
    const response = await fetch(`${BASE_URL}/users`, {
        headers: {
            "Authorization": `Bearer ${ token }`
        }
    });

    if (response.ok) {
        const data = await response.json();
        return data;
    }
    return {};
}












            

            
export default services