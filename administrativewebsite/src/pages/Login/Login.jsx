import React from "react";
import { useState } from "react";


import { useNavigate } from "react-router-dom";
import style from "../../assets/app.module.css"
import Botton from "../../components/Button";
import Input from "../../components/Input";
const Login = () => {
    const navigate = useNavigate();

    /*const { login, token } = useUserContext();*/   
    
    const [usernameInput, setUsernameInput] = useState("");
    const [passwordInput, setPasswordInput] = useState("");
    const [error, setError] = useState(false);

    const onChangeHandler = (e, save) => {
        console.log(e.target.value);
        save(e.target.value);
    }

    const onSubmitHandler = async (e) => {
        e.preventDefault();

        navigate("/users");
        //const logged = await login(usernameInput, passwordInput);
        //setError(!logged);

        setUsernameInput("");
        setPasswordInput("");
    }

    /*useEffect(()=>{
        console.log(token);
        navigate("/home");
    }, [token, navigate]);*/

    return (
        <div>
            
            <div>
            <div>
            <form onSubmit={onSubmitHandler} className={style.center}>
                <div>
                <h3 className="fs-2 text-center">Inicio de sesión</h3>
                <div className="form-group mt-4">
                    <label>Usuario</label>
                    <Input
                        type="Text" 
                        placeholder="Ingrese su usuario" 
                        name="User" 
                        onChange={e=>onChangeHandler(e,setUsernameInput)} 
                        required/>
                </div>
                <div className="form-group mt-4">
                    <label>Constraseña</label>
                    <Input 
                        type="Password" 
                        placeholder="Ingrese su contraseña" 
                        name="Password" 
                        onChange={e=>onChangeHandler(e,setPasswordInput)} 
                        required/>
                </div>
                <div className="d-grid gap-2 mt-5">
                    <Botton text="Inicio de sesión" type="submit" nameClass={style.btn}/>                    
                </div>
                
                {error && <p className="text-red-500 text-xs italic">Usuario o contraseña incorrectos</p>}

                </div>
            </form>
            </div>
            </div>
        </div>
    );
}

export default Login;