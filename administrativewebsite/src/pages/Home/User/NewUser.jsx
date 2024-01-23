import React from "react";
import { useNavigate } from "react-router-dom";
//components
import SubMenu from "../../../components/SubMenu"
import Menu from "../../../components/Menu"

const Users = () => {

    const navigate = useNavigate();

    const onSubmitHandler = async (e) => {
        e.preventDefault();

        navigate("/users");
    }

    return (

        <div className="d-flex">
            <Menu />
            <div className="w-100">
                <SubMenu linkShow="/users" linkNew="/newuser" />
                <section className="p-3 bg-white min-vh-100 max-vh-auto">
                    <div className="container">
                        <div className="row">
                            <div className="col-md-12 ">
                                <h2>Usuarios</h2>
                                    Agregar un nuevo usuario
                                <form onSubmit={onSubmitHandler} className="my-3 me-4 shadow px-5 pb-5 pt-4 bg-body rounded">
                                    <div class="form-group mb-3">
                                        <label for="formGroupExampleInput">Nombre de usuario</label>
                                        <input type="text" class="form-control" id="formGroupExampleInput" />
                                    </div>
                                    <div class="form-group mb-3">
                                        <label for="exampleInputPassword1">Contraseña</label>
                                        <input type="password" class="form-control" id="exampleInputPassword1" />
                                    </div>
                                    <div class="form-group mb-3">
                                        <label for="exampleFormControlInput1">Correo electronico</label>
                                        <input type="email" class="form-control mt-1" id="exampleFormControlInput1" placeholder="nombre@dominio.com" />
                                    </div>
                                    <div class="form-group mb-3">
                                        <label for="formGroupExampleInput">Número de telefono</label>
                                        <input type="number" class="form-control" id="formGroupExampleInput" />
                                    </div>
                                    <div class="form-group">
                                        
                                        <select class="custom-select p-1 w-100 mb-4">
                                            <option selected>Tipo de usuario</option>
                                            <option value="Admin">Administrador</option>
                                            <option value="Normal">Normal</option>
                                        </select>
                                    </div>
                                    <button type="submit" class="btn btn-primary">Guardar</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </section>
            </div >
        </div >

    );
}

export default Users;