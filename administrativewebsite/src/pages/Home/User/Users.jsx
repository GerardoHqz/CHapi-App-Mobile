import React from "react";
import { useNavigate } from "react-router-dom";
//components
import SubMenu from "../../../components/SubMenu"
import Menu from "../../../components/Menu"
import UserCells from "../../../components/Celdas/UserCells"
import Header from "../../../components/Celdas/Header"

const Users = () => {

    const navigate = useNavigate();
    
    //TODO: Extraer tabla como componente
    return (

        <div className="d-flex">
            <Menu />
            <div className="w-100">
                <SubMenu linkShow="/users" linkNew="/newUser"/>
                <div className="p-3 bg-white min-vh-100 max-vh-auto">
                    <div className="h-100">
                        <div className="row">
                            <div className="col-md-12 px-4 py-2">
                                <h2>Usuarios</h2>
                                <p>Lista de usuarios</p>
                                <table class="table w-100">
                                    <Header column1="#" column2="Nombre de usuario" column3="Correo electronico" column4="Acción"/>
                                    <tbody>
                                        <UserCells row1="1" row3="gerardo@gmail.com"row2="Gerardo"/>
                                        <UserCells row1="2" row3="Karen@gmail.com" row2="Karen"/>
                                        <UserCells row1="3" row3="Arely@gmail.com" row2="Arely"/>
                                        <UserCells row1="4" row3="Paolo@gmail.com" row2="Paolo"/>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                
                </div>
                
            </div>
        </div>

    );
}

export default Users;